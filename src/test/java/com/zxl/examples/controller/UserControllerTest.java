package com.zxl.examples.controller;

import com.zxl.examples.entity.User;
import com.zxl.examples.service.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

/**
 * Created by Administrator on 2017/8/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserRepository userRepository;;

    /**
     * 模拟返回的假数据-->覆盖真实返回的数据
     */
    @Before
    public void setup() {
        given(this.userRepository.
                findOne(5L)
        ).willReturn(
                new User("test", "testName","123456"));
    }



    @Test
    public void test() {
        ResponseEntity<String> response =this.restTemplate.getForEntity("/users/{id}",
                String.class, 5L);
        System.out.println(response.getBody());
    }
}
