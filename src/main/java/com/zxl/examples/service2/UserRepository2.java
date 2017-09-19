package com.zxl.examples.service2;

import com.zxl.examples.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface UserRepository2 extends JpaRepository<User,Long> {
}
