package com.zxl.examples.controller;

import com.zxl.examples.entity.User;
import com.zxl.examples.service.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Administrator on 2017/8/3.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsernameShouldReturnUser() {
        //初始化user数据
        this.entityManager.persist(new User("5", "5","5"));
        User user = this.repository.findByUsername("5").get(0);

        assertThat(user.getName()).isEqualTo("5");
        assertThat(user.getUsername()).isEqualTo("5");
    }


}
