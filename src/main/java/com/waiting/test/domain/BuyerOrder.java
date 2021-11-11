package com.waiting.test.domain;

import java.util.List;

public class BuyerOrder extends User {
    public UserCart userOrder;
    public String orderTime;
    public int sellerId;
    public String sellerName;
    public BuyerOrder(){
        userOrder = null;
    }
    public void setUserId(int userId){
        super.userId = userId;
    }
    public void setSellerId(int sellerId){
        this.sellerId = sellerId;
    }
    public void setSellerName(String sellerName){
        this.sellerName = sellerName;
    }
    public void setOrderTime(String orderTime){
        this.orderTime = orderTime;
    }
    public void setUserCart(UserCart userOrder){
        this.userOrder = userOrder;
    }
}
