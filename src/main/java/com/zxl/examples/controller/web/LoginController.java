package com.zxl.examples.controller.web;

import com.zxl.examples.entity.User;
import com.zxl.examples.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocaleResolver localeResolver;

    @RequestMapping("/login/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login/login")
    public String login(HttpServletRequest request, Model model,HttpServletResponse response,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password){
        List<User> userList = userRepository.findByUsername(username);
        if(userList!=null && userList.size()>0){
            request.getSession().setAttribute("user",userList.get(0));

            //循环集合
            List<User> users = new ArrayList<User>();
            for(int i=1;i<6;i++){
                User user = new User();
                user.setName("name"+i);
                user.setUsername("username"+i);
                user.setPassword("password"+i);
                users.add(user);
            }
            model.addAttribute("users",users);
            //#dates数据
            List<Date> datesList = new ArrayList<Date>();
            for(int i = 0;i<3;i++){
                datesList.add(new Date());
            }
            model.addAttribute("datesList",datesList);
            Date[] datesArray = new Date[]{new Date(),new Date()};
            model.addAttribute("datesArray",datesArray);
            model.addAttribute("date",new Date());
            //#strings数据
            List<String> nameList = new ArrayList<String>();
            for(int i = 0;i<3;i++){
                nameList.add("name-"+i);
            }
            model.addAttribute("nameList",nameList);
            String[] nameArr = new String[]{"123","adb"};
            model.addAttribute("nameArr",nameArr);

//            localeResolver.setLocale(request,response, Locale.ENGLISH);


            return "index";
        }
        return "login";
    }

    @RequestMapping("/login/index")
    public String toLogin(HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("message","welcome to the index.");
        return "index";
    }

    @RequestMapping("/login/getusers")
    @ResponseBody
    public List<User> getusers(){
        List<User> users = new ArrayList<User>();
        for(int i=10;i<16;i++){
            User user = new User();
            user.setName("name"+i);
            user.setUsername("username"+i);
            user.setPassword("password"+i);
            users.add(user);
        }
        return users;
    }
}
