package com.zxl.examples.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxl.examples.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017/8/3.
 */
public class UserJsonTest {

    private JacksonTester<User> json;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Possibly configure the mapper
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void serializeJson() throws IOException {
        User user =
                new User("Honda", "Civic","123456");

//        assertThat(this.json.write(user))
//                .isEqualToJson("User.json");

        assertThat(this.json.write(user))
                .hasJsonPathStringValue("@.username");

        assertThat(this.json.write(user))
                .extractingJsonPathStringValue("@.username")
                .isEqualTo("Honda");
    }

    @Test
    public void deserializeJson() throws IOException {
        String content = "{\"username\":\"Ford\",\"name\":\"Focus\"}";

        assertThat(this.json.parse(content))
                .isEqualTo(new User("Ford", "Focus","123456"));

        assertThat(this.json.parseObject(content).getUsername())
                .isEqualTo("Ford");
    }
}
