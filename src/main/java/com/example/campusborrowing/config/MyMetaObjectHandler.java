package com.example.campusborrowing.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
  myBaits-plus 自定填充处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(
                metaObject,
                "createTime",
                LocalDateTime.class,
                LocalDateTime.now()
        );
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        this.strictInsertFill(
        metaObject,
                "updateTime",
                LocalDateTime.class,
                LocalDateTime.now()
        );
    }
}
