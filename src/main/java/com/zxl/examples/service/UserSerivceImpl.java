package com.zxl.examples.service;

import com.zxl.examples.catche.customannotation.UserSaveCache;
import com.zxl.examples.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/7/24.
 */
@Service("userSerivce")
public class UserSerivceImpl {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    public void addMoreUsers(){
        User user1 = new User();
        user1.setUsername("123");
        user1.setName("123");
        user1.setPassword("123");
        userRepository.save(user1);
        User user2 = new User();
        user2.setUsername("234");
        user2.setName("123");
        user2.setPassword("123");
        userRepository.save(user2);
    }

    public void addMoreList(){
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUsername("345");
        user1.setName("123");
        user1.setPassword("123");
        userList.add(user1);

        User user2 = new User();
        user2.setUsername("456");
        user2.setName("123");
        user2.setPassword("123");
        userList.add(user2);
        userRepository.save(userList);
    }

    //unless-->用于否决缓存更新的，不像condition，该表达只在方法执行之后判断，此时可以拿到返回值result进行判断了
    @Cacheable(value="userCache",key="'user:'+#username",unless = "#result==null")
    public User getUserByUsername(String username){
        User user = null;
        List<User> userlist = userRepository.findByUsername(username);
        if(userlist!=null && userlist.size()>0){
            user = userlist.get(0);
        }
        return user;
    }
//    @Cacheable(value="userCache#120",key="'user:'+#username",unless = "#result==null")
//    public User getUserByUsername(String username){
//        User user = null;
//        List<User> userlist = userRepository.findByUsername(username);
//        if(userlist!=null && userlist.size()>0){
//            user = userlist.get(0);
//        }
//        return user;
//    }

    //allEntries-->是否移除所有数据
    //beforeInvocation-->是调用方法之前移除/还是调用之后移除
    @CacheEvict(value = "userCache",key="'user:'+#user.username")
    public void delUserById(User user){
        userRepository.delete(user);
    }

    public String setUserInRedis(){
        stringRedisTemplate.opsForValue().set("abc","123",60L, TimeUnit.SECONDS);
        return stringRedisTemplate.opsForValue().get("abc");
//        redisTemplate.opsForList();//可直接放入实现序列化的pojo
    }

    public void delUserInRedis(){
        stringRedisTemplate.delete("abc");
    }

    //condition-->满足缓存条件的数据才会放入缓存，condition在调用方法之前和之后都会判断
    @CachePut(value="userCache",key = "#user.username",condition = "#user.username<='100'")
    public User save(User user){
        userRepository.save(user);
        return user;
    }

    @Caching(
            put={
                    @CachePut(value = "userCache", key = "'user:'+#user.id"),
                    @CachePut(value = "userCache", key = "'user:'+#user.username")
            }
    )
    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    @UserSaveCache
    public User addUser2(User user){
        userRepository.save(user);
        return user;
    }

    @Cacheable(value="userCache",key="'user:'+#username",condition = "#root.target.canCache()",unless = "#result==null")
    public User getUserByUsername2(String username){
        User user = null;
        List<User> userlist = userRepository.findByUsername(username);
        if(userlist!=null && userlist.size()>0){
            user = userlist.get(0);
        }
        return user;
    }

    @Cacheable(value="userCache",key="'user:'+#username",condition = "#root.target.notCache()")
    public User getUserByUsername3(String username){
        User user = null;
        List<User> userlist = userRepository.findByUsername(username);
        if(userlist!=null && userlist.size()>0){
            user = userlist.get(0);
        }
        return user;
    }

    public boolean canCache(){
        return true;
    }

    public boolean notCache(){
        return false;
    }
}
