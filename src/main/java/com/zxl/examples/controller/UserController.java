package com.zxl.examples.controller;

import com.zxl.examples.controller.common.ResultBean;
import com.zxl.examples.controller.common.SuccessBean;
import com.zxl.examples.entity.User;
import com.zxl.examples.service.UserRepository;
import com.zxl.examples.service.UserSerivceImpl;
import com.zxl.examples.service2.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepository2 userRepository2;

    @Autowired
    UserSerivceImpl userSerivce;

    @GetMapping("/users")
    public List<User> findUserList(){
        return userRepository.findAll();
    }

    @PostMapping("/users/add")
    public User addUser(@RequestParam("username") String username,
                              @RequestParam("name") String name,
                              @RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user);

    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findOne(id);
    }

    @PutMapping("/users/{id}")
    public User updUserById(@PathVariable Long id,@RequestParam("name") String name){
        User user = userRepository.findOne(id);//先查出来，否则修改的时候会将其他request中没有的参数也给覆盖掉
        user.setName(name);
        return userRepository.save(user);//与保存是同一个方法
    }

    @DeleteMapping("/users/{id}")
    public ResultBean delUserById(@PathVariable Long id){
        userRepository.delete(id);
        return new SuccessBean();
    }

    @GetMapping("/users/username/{username}")
    public List<User> findByUsername(@PathVariable ("username") String username){
        return userRepository.findByUsername(username);
    }

    @PostMapping("/users/addMore")
    public void addMore(){
        userSerivce.addMoreUsers();
    }

    @PostMapping("/users/addList")
    public void addMoreList(){
        userSerivce.addMoreList();
    }

    @GetMapping("/users/page/{page}")
    public Page<User> findAllLimit(@PathVariable Integer page){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findAll(new PageRequest(page,6));
    }

    @GetMapping("/users/pagesort/{page}")
    public Page<User> findAllLimitSort(@PathVariable Integer page){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findAll(new PageRequest(page,6,new Sort(Sort.Direction.DESC,"id")));
    }

    @GetMapping("/users/pagesortmore/{page}")
    public Page<User> findAllLimitSortMore(@PathVariable Integer page){
        //页数从0开始算，比如第一页应传page=0
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Order nameAscOrder = new Sort.Order(Sort.Direction.ASC,"name");
        Sort.Order idDescOrder = new Sort.Order(Sort.Direction.DESC,"id");
        orders.add(nameAscOrder);
        orders.add(idDescOrder);
        return  userRepository.findAll(new PageRequest(page,6,new Sort(orders)));
    }

    @GetMapping("/users/page/param/{page}")
    public Slice<User> findByNameOrderByIdDesc(@PathVariable Integer page){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameOrderByIdDesc("123",new PageRequest(page,6,new Sort(Sort.Direction.DESC,"id")));
    }

    @GetMapping("/users/page/parampw/{page}")
    public Page<User> findByNameOrderBypasswordAscOrderByIdDesc(@PathVariable Integer page){
        //页数从0开始算，比如第一页应传page=0
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Order nameAscOrder = new Sort.Order(Sort.Direction.ASC,"password");
        Sort.Order idDescOrder = new Sort.Order(Sort.Direction.DESC,"id");
        orders.add(nameAscOrder);
        orders.add(idDescOrder);
        return  userRepository.findByNameOrderByPasswordAscIdDesc("123",new PageRequest(page,6,new Sort(orders)));
    }

    @GetMapping("/users/order")
    public List<User> findByNameOrderByIdDesc(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameOrderByIdDesc("123",new Sort(Sort.Direction.DESC,"id"));
    }

    @GetMapping("/users/moreparam")
    public List<User> findByNameAndPassword(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameAndPassword("123","123");
    }

    @GetMapping("/users/moreparam2")
    public List<User> findByNameOrPassword(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameOrPassword("123","1");
    }

    @GetMapping("/users/moreparam3")
    public List<User> findByNameAndPasswordLimit(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameOrPassword("123","123",new PageRequest(0,3,new Sort(Sort.Direction.DESC,"id")));
    }

    @GetMapping("/users/moreparam4")
    public List<User> findByPasswordLessThan(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByPasswordLessThan("1");
    }

    @GetMapping("/users/moreparam5")
    public List<User> findByNameAndPasswordLessThan(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameAndPasswordLessThan("123","1");
    }

    @GetMapping("/users/moreparam6")
    public List<User> findByIdLessThanAndPasswordLessThan(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByIdLessThanAndPasswordLessThan(10L,"2");
    }

    @GetMapping("/users/moreparam7")
    public List<User> findByIdLessThanAndPasswordLessThanEquals(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByIdLessThanAndPasswordLessThanEqual(10L,"1");
    }

    @GetMapping("/users/moreparam8")
    public List<User> findByIdLessThanEquals(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByIdLessThanEqual(10L);
    }

    @GetMapping("/users/moreparam9")
    public List<User> findByPasswordBetween(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByPasswordBetween("1","2");
    }

    @GetMapping("/users/moreparam10")
    public List<User> findByUsernameIn(){
        //页数从0开始算，比如第一页应传page=0
        List<String> usernames = new ArrayList<String>();
        usernames.add("1");
        usernames.add("2");
        usernames.add("3");
        return  userRepository.findByUsernameIn(usernames);
    }

    @GetMapping("/users/moreparam11")
    public List<User> findByNameLike(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameLike("1");
    }

    @GetMapping("/users/moreparam12")
    public List<User> findByNameContaining(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByNameContaining("1");
    }

    @GetMapping("/users/moreparam13")
    public List<User> findByUsername(){
        //页数从0开始算，比如第一页应传page=0
        User user = new User();
        user.setUsername("1");
        return  userRepository.findByUsername(user);
    }

    @GetMapping("/users/moreparam14")
    public List<User> findByUsername14(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByUsername("1");
    }

    @GetMapping("/users/moreparam15")
    public List<User> findByIdAndUsername15(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findByIdAndUsername(12L,"345");
    }

    @GetMapping("/users/moreparam16")
    public List<User> findAllLimit16(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findAll2(new PageRequest(0,4));
    }

    @GetMapping("/users/moreparam17")
    public List<User> findAllLimit17(){
        //页数从0开始算，比如第一页应传page=0
        return  userRepository.findAll3();
    }

    @GetMapping("/users/moreparam18/{username}")
    public User getUserByUsername(@PathVariable String username){
        //页数从0开始算，比如第一页应传page=0
        return  userSerivce.getUserByUsername(username);
    }

    @DeleteMapping("/users/del/{id}")
    public void delUserById12(@PathVariable Long id){
        User user = userRepository.findOne(id);
        if(user !=null){
            userSerivce.delUserById(user);
        }
    }

    @GetMapping("/users/moreparam19")
    public String setUserInRedis(){
        //页数从0开始算，比如第一页应传page=0
        return  userSerivce.setUserInRedis();
    }

    @GetMapping("/users/moreparam20")
    public void delUserInRedis(){
        //页数从0开始算，比如第一页应传page=0
        userSerivce.delUserInRedis();
    }

    @PostMapping("/users/moreparam21/{username}")
    public User save(@PathVariable String username){
        //页数从0开始算，比如第一页应传page=0
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        user.setName(username);
        userSerivce.save(user);
        return user;
    }

    @PostMapping("/users/moreparam22/{username}")
    public User addUser(@PathVariable String username){
        //页数从0开始算，比如第一页应传page=0
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        user.setName(username);
        userSerivce.addUser(user);
        return user;
    }

    @PostMapping("/users/moreparam23/{username}")
    public User addUser2(@PathVariable String username){
        //页数从0开始算，比如第一页应传page=0
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        user.setName(username);
        userSerivce.addUser2(user);
        return user;
    }

    @GetMapping("/users/moreparam24/{username}")
    public User getUserByUsername2(@PathVariable String username){
        //页数从0开始算，比如第一页应传page=0
        return  userSerivce.getUserByUsername2(username);
    }

    @GetMapping("/users/moreparam25/{username}")
    public User getUserByUsername3(@PathVariable String username){
        //页数从0开始算，比如第一页应传page=0
        return  userSerivce.getUserByUsername3(username);
    }



    @GetMapping("/users2")
    public List<User> findUserList2(){
        return userRepository2.findAll();
    }

}
