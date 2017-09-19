package com.zxl.examples.catche.customannotation;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/7/26.
 */
@Caching(
        put={
                @CachePut(value = "userCache", key = "'user:'+#user.id"),
                @CachePut(value = "userCache", key = "'user:'+#user.username")
        }
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UserSaveCache {
}
