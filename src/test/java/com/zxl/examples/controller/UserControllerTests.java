package com.zxl.examples.controller;

import com.zxl.examples.entity.User;
import com.zxl.examples.service.UserRepository;
import com.zxl.examples.service.UserSerivceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2017/8/3.
 * WebMvcTest中引入控制类中（此处为UserController.class）的Autowired依赖有几个这里也要相应的配置与之一一对应的@MockBean，否则会报缺失依赖的注入错误
 *
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserSerivceImpl userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void getVehicleShouldReturnMakeAndModel() throws Exception {
        User returnUser = new User("5", "5","5");
        returnUser.setId(5L);
        given(this.userRepository.findOne(5L))
                .willReturn(returnUser);

        byte[] result = this.mvc.perform(get("/users/{id}",5L)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsByteArray();
        System.out.print(new String(result)+"----------");
    }


}
