package com.waiting.test.domain;

import java.util.List;

public class UserOrder extends User {
    public List<UserCart> userCarts;
    public String orderTime;
    public UserOrder(){
        userCarts = null;
    }
    public void setUserId(int userId){
        super.userId = userId;
    }
    public int setUser(User user){
        super.userId = user.userId;
        super.userName = user.userName;
        super.setUserEmail(user.getUserEmail());
        return 0;
    }
    public void setOrderTime(String orderTime){
        this.orderTime = orderTime;
    }
    public void setUserCarts(List<UserCart> userCarts){
        this.userCarts = userCarts;
    }
    @Override
    public String toString(){
        String str = "";
        str += super.userName +  "：\n" ;
        str += "您好！\n" ;
        str += "您已在KIVI电商平台下订单：\n订单详情如下\n" ;
        for (int i = 0; i < userCarts.size(); i++){
            str += "商品名称：" + userCarts.get(i).goodName + ", ";
            str += "商品单价：" + userCarts.get(i).goodPrice + ", ";
            str += "购买数量：" + userCarts.get(i).goodNum + ", ";
            str += "\n";
        }
        return str;
    }
}
