package com.waiting.test.service;

import com.waiting.test.domain.*;

import java.util.List;

public interface UserService {
    User selectUser(String name);
    int addUser(String name, String password, String email);
    List<UserCart> getCart(int userId);
    int addCart(int userId,int goodId,int goodNum,String addTime);
    int subCart(int userId,int goodId,int goodNum,String addTime);
//    int addOrder(int userId, List<UserCart> selectedCarts);
    int addOrder(UserOrder userOrder);
    List<BuyerOrder> getBuyerOrder(int buyerId);
    List<UserBrowsed> getUserBrowsed(int userId);
}
