package com.example.campusborrowing.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.campusborrowing.common.Result;
import com.example.campusborrowing.entity.BorrowRules;
import com.example.campusborrowing.entity.Category;
import com.example.campusborrowing.entity.Product;
import com.example.campusborrowing.service.CategoryService;
import com.example.campusborrowing.service.ProductService;
import com.example.campusborrowing.util.JwtUtil;
import com.example.campusborrowing.vo.ProductDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/add")
    public Result<?> add(@RequestBody Map<String, Object> requestData, HttpServletRequest request) {
        try {
            // 1. 从token中获取用户ID（确保发布人身份）
            String token = request.getHeader("token");
            Integer userId = JwtUtil.getUserId(token);
            if (userId == null) {
                return Result.error(401,"未登录，无法发布物品");
            }

            // 2. 构建Product对象，解析基本字段
            Product product = new Product();
            product.setName((String) requestData.get("name"));
            product.setDescription((String) requestData.get("description"));

            // 处理价格（如果前端传递了价格）
            if (requestData.get("price") != null) {
                product.setPrice(new BigDecimal(requestData.get("price").toString()));
            }
            product.setLocation((String) requestData.get("location"));
            product.setImageUrl((String) requestData.get("imageUrl"));
            product.setCategoryId((Integer) requestData.get("categoryId"));
            product.setProductNo(generateProductId());
            product.setDepartment((String) requestData.get("department"));
            product.setUserId(userId); // 设置发布人ID

            // 3. 解析前端传递的rules对象，转换为JSON字符串存储
            Map<String, Object> rulesMap = (Map<String, Object>) requestData.get("rules");
            if (rulesMap != null) {
                // 将Map转换为BorrowRules对象（便于校验字段）
                BorrowRules rules = new BorrowRules();
                rules.setMaxBorrowDays((Integer) rulesMap.get("maxBorrowDays"));
                rules.setOverduePolicy((String) rulesMap.get("overduePolicy"));
                rules.setDamagePolicy((String) rulesMap.get("damagePolicy"));
                rules.setIdentificationRequired((String) rulesMap.get("identificationRequired"));

                // 转换为JSON字符串存入product
                ObjectMapper objectMapper = new ObjectMapper();
                product.setRules(objectMapper.writeValueAsString(rules));
            }

            // 4. 直接调用MyBatis-Plus的save方法存储
            productService.save(product);
            return Result.success("物品发布成功");

        } catch (JsonProcessingException e) {
            // JSON转换失败（如规则格式错误）
            return Result.error(401,"规则格式错误，发布失败");
        } catch (Exception e) {
            // 其他异常（如数据库错误）
            System.out.println(e.getMessage());
            return Result.error(500,"服务器错误，发布失败");
        }
    }
    @GetMapping("/page")
    public Result<IPage<Product>> page(@RequestParam Integer pageNum,
                                       @RequestParam Integer size ,
                                       @RequestParam(required = false) Integer categoryId,
                                       @RequestParam(required = false) String keyword,
                                       @RequestParam(required = false) String status
    ) {

        //1. 新建查询条件对象
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        //2. 如果分类id 不等于null ，添加查询条件
        if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId);
        }
        // 3. 如果关键词 不等于null ,添加模糊查询
        if (keyword != null) {
            queryWrapper.like("name", keyword);
        }
        if (status != null) {
            queryWrapper.eq("status", status);
        }
        //  4. 新建分页对象
        IPage<Product> data = productService.page(new Page<>(pageNum, size), queryWrapper);
        return Result.success(data);
    }
    @GetMapping("/detail/{id}")
    public Result<ProductDetail> getDetail(@PathVariable Integer id) {
        // 1. 查询物品基本信息
        Product product = productService.getById(id);
        if (product == null) {
            return Result.error(404, "物品不存在");
        }

        // 2. 转换为ProductDetail对象
        ProductDetail detail = new ProductDetail();
        // 复制基本字段
        BeanUtils.copyProperties(product, detail); // 借助Spring的工具类复制属性

        // 3. 解析rules JSON字符串为BorrowRules对象
        if (product.getRules() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                BorrowRules rules = objectMapper.readValue(product.getRules(), BorrowRules.class);
                detail.setRules(rules); // 设置解析后的规则对象
            } catch (JsonProcessingException e) {
                // 若解析失败，可设置默认规则或忽略
                detail.setRules(new BorrowRules());
            }
        }

        // 4. 查询分类名称（前端展示用，无需前端再根据categoryId查询）
        if (product.getCategoryId() != null) {
            Category category = categoryService.getById(product.getCategoryId());
            if (category != null) {
                detail.setCategoryName(category.getName());
            }
        }

        // 5. 返回封装好的详情数据
        return Result.success(detail);
    }
    public String generateProductId() {
        return "PRODUCT"+ System.currentTimeMillis()+ RandomStringUtils.randomNumeric(4);
    }
}
