package com.zxl.examples.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/9.
 */
@RestController
public class HelloApiController {


//    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
//            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    })


    @ApiOperation(value="向世界问好", notes="根据url的name来向世界问好")
//    @ApiImplicitParam(name = "name", value = "用户Name", required = true, dataType = "String")
    @GetMapping("/helloapi/{name}")
    public String helloapi(@PathVariable String name){
        return "helloapi "+name;
    }


}
