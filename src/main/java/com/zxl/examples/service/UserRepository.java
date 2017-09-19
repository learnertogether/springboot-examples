package com.zxl.examples.service;

import com.zxl.examples.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    //方法名要规则，sql会根据方法名自动生成sql进行操作
    public List<User> findByUsername(String username);

    public Slice<User> findByNameOrderByIdDesc(String name, Pageable pageable);


    public Page<User> findByNameOrderByPasswordAscIdDesc(String name, Pageable pageable);

    List<User> findByNameOrderByIdDesc(String name,Sort sort);

    List<User> findByNameAndPassword(String name,String password);

    List<User> findByNameOrPassword(String name,String password);

    List<User> findByNameOrPassword(String name,String password, Pageable pageable);

    List<User> findByPasswordLessThan(String password);

    List<User> findByNameAndPasswordLessThan(String name,String password);

    List<User> findByIdLessThanAndPasswordLessThan(Long id,String password);

    List<User> findByIdLessThanAndPasswordLessThanEqual(Long id,String password);

    List<User> findByIdLessThanEqual(Long id);

    List<User> findByPasswordBetween(String password1,String password2);

    List<User> findByUsernameIn(List<String> usernames);

    List<User> findByNameLike(String name);

    List<User> findByNameContaining(String name);

    //SpEL (Spring Expression Language)
    @Query("select u from User u where u.username = :#{#user.username}")
    List<User> findByUsername(@Param("user") User user);

    @Query("select u from User u where u.username = :username")
    List<User> findByUsername2(@Param("username") String username);

    @Query("select u from User u where u.username=?2 AND u.id=?1")
    List<User> findByIdAndUsername(Long id,String username);

    @Query("select u from User u")
    List<User> findAll2(Pageable pageable);

    @Query(value="select * from user limit 0,6",nativeQuery = true)//使用原生的sql，此处连的是mysql
    List<User> findAll3();


}
