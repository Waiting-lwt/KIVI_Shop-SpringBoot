package com.waiting.test.controller;

import com.mysql.cj.xdevapi.JsonArray;
import com.waiting.test.domain.*;
import com.waiting.test.service.GoodService;
import com.waiting.test.service.MailService;
import com.waiting.test.service.UserService;
import com.waiting.test.service.SellerService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
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
    public int addUser(@RequestBody Map<String, String> request){
        int userId = userService.addUser(request.get("userName"),
                request.get("userPassword"),request.get("userEmail"));
        return userId;
    }

    @ResponseBody
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public Map<String, Object> getUser(String userName, String userPassword){
        User user = userService.selectUser(userName);
        if(user==null){
            return null;
        } else if (!user.passCheck(userPassword)){
            Map<String, Object> map = new HashMap<>(1);
            map.put("userId", -1);
            return map;
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put("userId", user.userId);
        map.put("userName", user.userName);
        map.put("userType", user.userType);
        map.put("userEmail", user.getUserEmail());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    public List<UserCart> getCart(int userId) {
        return userService.getCart(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/addCart", method = RequestMethod.PUT)
    public int addCart(@RequestBody Map<String, Object> request) {
        return userService.addCart(
                (Integer) request.get("userId"),
                (Integer) request.get("goodId"),
                (Integer) request.get("goodNum"),
                (String) request.get("addTime"));
    }

    @ResponseBody
    @RequestMapping(value = "/subCart", method = RequestMethod.PUT)
    public int subCart(@RequestBody Map<String, Object> request) {
        return userService.subCart(
                (Integer) request.get("userId"),
                (Integer) request.get("goodId"),
                (Integer) request.get("goodNum"),
                (String) request.get("addTime"));
    }

    @ResponseBody
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public int addOrder(int userId,@RequestBody  List<UserCart> selectCarts) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(userId);
        userOrder.setUserCarts(selectCarts);
        userService.addOrder(userOrder);
        mailService.sendSimpleMail(userOrder.getUserEmail(),
                userOrder.getUserName()+"：您在KIVI电商平台上的订单",
                userOrder.toString());
        //addOrderLog
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/getSellerGood", method = RequestMethod.GET)
    public List<Good> getGood(int userId){
        return goodService.getSellerGoods(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/getSellerOrder", method = RequestMethod.GET)
    public List<SellerOrder> getSellerOrder(int sellerId) {
        return sellerService.getSellerOrder(sellerId);
    }
}
