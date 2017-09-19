package com.zxl.examples.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/21.
 */
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id",nullable = false)
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String password;

    private String salt;//加密密码的盐

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username,String name,String password){
        super();
        this.username=username;
        this.name=name;
        this.password=password;
    }

    //必须有一个无参构造方法，否则会报错
    public User(){

    }

    @Override
    public String toString() {
        return getId()+","+getUsername()+","+getName();
    }

    public String getSalt() {
        return "123456";
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
    //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
}
