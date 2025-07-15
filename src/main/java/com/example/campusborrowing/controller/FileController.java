package com.example.campusborrowing.controller;

import com.example.campusborrowing.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("file")
public class FileController {
    @Value("${file.update_dir.file}")
    private String uploadDir;
    @PostMapping("/upload")
    public Result<?> uploadFile(@RequestParam("file") MultipartFile file ) throws IOException {
        //1. generate file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        //2. create file path of service
        File dest = new File(uploadDir+fileName);
        // 3. save data
        file.transferTo(dest);
        // 4. response the success,and return dest path
        return Result.success("/upload/"+fileName);
    }
}
