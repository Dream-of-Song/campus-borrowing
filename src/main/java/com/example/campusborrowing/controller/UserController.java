package com.example.campusborrowing.controller;

import com.example.campusborrowing.common.Result;
import com.example.campusborrowing.entity.User;
import com.example.campusborrowing.service.UserService;
import com.example.campusborrowing.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("")
    public Result<List<User>>List(){
        return Result.success(userService.list());
    }
    @PostMapping("")
    public Result<?> Create(@RequestBody User user){
// ctrl alt v
        boolean save = userService.save(user);
        if(save){
            return Result.success(save);
        }
        else {
            return  Result.error(500,"添加用户失败");
        }
    }

    @PostMapping("/signup")
    public Result<?> register(@RequestBody User user){
        Long count = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).count();
        if (count > 0){
            return Result.error(500,"用户名已存在");
        }
        // password encrypt
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(pwd);
//        user.setStatus("normal");
        boolean save = userService.save(user);
        if(save){

            return Result.success(null);
        }else{
            return Result.error(500,"注册失败");
        }
    }
    @PostMapping("login")
    public Result<Object> login(@RequestBody User user){
        User userDB = userService.lambdaQuery().eq(User::getUsername, user.getUsername()).one();
        if(userDB != null){
            if (userDB.getPassword().equals(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()))){
                String token = JwtUtil.createToken(userDB.getId());
                Map<String,Object> map = new HashMap<>();
                map.put("token",token);
                map.put("user",userDB);
                return Result.success(map);
            }
            else {
                return Result.error(500,"用户名或密码错误,请重新尝试");
            }
        }else {
            return Result.error(500,"用户名不存在");
        }
    }
}
