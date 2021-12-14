package com.waiting.test.controller;

import com.waiting.test.domain.*;
import com.waiting.test.service.GoodService;
import com.waiting.test.service.MailService;
import com.waiting.test.service.UserService;
import com.waiting.test.service.SellerService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    private Base64Util base64Util;
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private MailService mailService;

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody Map<String, Object> request,HttpServletResponse response) throws IOException {
        String userName = (String) request.get("userName");
        int userId = userService.addUser(
                (String) request.get("userName"),
                (String) request.get("userPassword"),
                (String) request.get("userEmail"));
        if(userId==-1){
            return -1;
        }
        else {
            Cookie cookie1 = new Cookie("userId",String.valueOf(userId));
            Cookie cookie2 = new Cookie("userName",String.valueOf(userName));
            Cookie cookie3 = new Cookie("userType","1");
            cookie1.setPath("/");   //
            cookie1.setMaxAge(24*60*60*30);       //存活30天
            cookie2.setPath("/");   //
            cookie2.setMaxAge(24*60*60*30);       //存活30天
            cookie3.setPath("/");   //
            cookie3.setMaxAge(24*60*60*30);       //存活30天
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
        }
        return userId;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Map<String, Object> getUser(String userName, String userPassword,HttpServletResponse response){
        //@CookieValue(value = "userId", defaultValue = "0") Integer userId
        User user = userService.selectUser(userName);
        if(user==null){
            return null;
        } else if (!user.passCheck(userPassword)){
            Map<String, Object> map = new HashMap<>(1);
            map.put("userId", -1);
            return map;
        }

        Cookie cookie1 = new Cookie("userId",String.valueOf(user.userId));
        Cookie cookie2 = new Cookie("userName",String.valueOf(userName));
        Cookie cookie3 = new Cookie("userType",String.valueOf(user.userType));
        cookie1.setPath("/");   //
        cookie1.setMaxAge(24*60*60*30);       //存活30天
        cookie1.setHttpOnly(true);
        cookie2.setPath("/");   //
        cookie2.setMaxAge(24*60*60*30);       //存活30天
        cookie3.setPath("/");   //
        cookie3.setMaxAge(24*60*60*30);       //存活30天
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", user.userId);
        map.put("userName", user.userName);
        map.put("userType", user.userType);
        map.put("userEmail", user.getUserEmail());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    public List<UserCart> getCart(@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return userService.getCart(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/addCart", method = RequestMethod.PUT)
    public int addCart(@RequestBody Map<String, Object> request,@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return userService.addCart(
                userId,
                (Integer) request.get("goodId"),
                (Integer) request.get("goodNum"),
                (String) request.get("addTime"));
    }

    @ResponseBody
    @RequestMapping(value = "/subCart", method = RequestMethod.PUT)
    public int subCart(@RequestBody Map<String, Object> request,@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return userService.subCart(
                userId,
                (Integer) request.get("goodId"),
                (Integer) request.get("goodNum"),
                (String) request.get("addTime"));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCart", method = RequestMethod.PUT)
    public int deleteCart(@RequestBody Map<String, Object> request,@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return userService.deleteCart(
                userId,
                (Integer) request.get("goodId"));
    }

    @ResponseBody
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public int addOrder(String orderTime, @RequestBody List<UserCart> selectCarts,@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId);
        userOrder.setUserCarts(selectCarts);
        userOrder.setUser(userService.selectUser(userId));
        userOrder.setOrderTime(orderTime);
        mailService.sendSimpleMail(userOrder.getUserEmail(),
                userOrder.getUserName()+"：您在KIVI电商平台上的订单",
                userOrder.toString());
        userService.addOrder(userId,userOrder);
        userService.deleteCarts(userId,userOrder);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/getSellerGood", method = RequestMethod.GET)
    public List<Good> getGood(@CookieValue(value = "userId", defaultValue = "0") Integer userId){
        return goodService.getSellerGoods(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getSellerOrder", method = RequestMethod.GET)
    public List<SellerOrder> getSellerOrder(@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return sellerService.getSellerOrder(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getBuyerOrder", method = RequestMethod.GET)
    public List<BuyerOrder> getBuyerOrder(@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return userService.getBuyerOrder(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getUserBrowsed", method = RequestMethod.GET)
    public List<UserBrowsed> getUserBrowsed(@CookieValue(value = "userId", defaultValue = "0") Integer userId) {
        return userService.getUserBrowsed(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/addUserBrowsed", method = RequestMethod.POST)
    public int addUserBrowsed(@RequestBody UserBrowsed userBrowsed,@CookieValue(value = "userId", defaultValue = "0") Integer userId){
        userBrowsed.browserId = userId;
        return userService.addUserBrowsed(userBrowsed);
    }
}
