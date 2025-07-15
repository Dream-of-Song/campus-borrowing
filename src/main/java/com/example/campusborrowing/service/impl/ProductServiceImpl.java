package com.example.campusborrowing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.campusborrowing.entity.Product;
import com.example.campusborrowing.mapper.ProductMapper;
import com.example.campusborrowing.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
