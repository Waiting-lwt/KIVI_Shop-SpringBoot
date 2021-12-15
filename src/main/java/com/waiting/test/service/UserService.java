package com.waiting.test.service;

import com.waiting.test.domain.*;

import java.util.List;

public interface UserService {
    User selectUser(String name);
    User selectUser(int userId);
    int addUser(String name, String password, String email);
    List<UserCart> getCart(int userId);
    int addCart(int userId,int goodId,int goodNum,String addTime);
    int subCart(int userId,int goodId,int goodNum,String addTime);
    int deleteCarts(int userId,UserOrder userOrder);
    int deleteCart(int userId,int goodId);
    int addOrder(int userId,UserOrder userOrder);
    List<BuyerOrder> getBuyerOrder(int buyerId);
    List<UserBrowsed> getUserBrowsed(int userId);
    int addUserBrowsed(UserBrowsed userBrowsed);
    User findByToken(String token);
}
