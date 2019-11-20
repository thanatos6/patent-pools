package com.suixingpay.common.service;


import com.suixingpay.common.dao.UserDao;
import com.suixingpay.common.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    /*Resource
    LoginTicketDAO loginTicketDAO;
*/
    public User selectByname(String name){
        return userDao.userByname(name);

    }

    public Map<String,String> register(String name,String password,String account ){

        Map<String,String>  map=new HashMap<>();
        if (StringUtils.isEmpty(name)){
        map.put("msg","姓名不能为空");
        return  map;
        }
        if (StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        if (StringUtils.isEmpty(account)){
            map.put("msg","账号不能为空");
            return  map;

        }
        return  map;

//

    }
    public Map<String,String> login(String account,String password,String name){

        Map<String,String> map=new HashMap<>();


        if (StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空！");
            return  map;
        }
        if (StringUtils.isEmpty(account)){
            map.put("msg","账号不能为空！");
            return  map;
        }
        User user=new User();
        user.setName(name);
        user.setAccount(account);
        user.setPassword(password);
        Date date=new Date();
        user.setCreateDate(date);
        userDao.addUser(user);
        return  map;



    }
   /* public String addLoginTicket(int userId){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setId(userId);
        Date date=new Date();
        date.setTime(3600*24*100+ date.getTime());
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketDAO.addTicket(loginTicket);
        return  loginTicket.getTicket();
    }
    public  void  logout(String ticket){
        loginTicketDAO.updateStatus(ticket,1);//直接失效掉就好了
    }
    public User getUser(int id){
        return userDao.userById(id);
    }
*/






}
