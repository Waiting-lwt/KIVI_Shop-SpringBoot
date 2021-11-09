package com.waiting.test.domain;

import java.util.List;

public class SellerOrder extends Seller {
    public UserCart userOrder;
    public String orderTime;
    public int buyerId;
    public String buyerName;
    public SellerOrder(){
        userOrder = null;
    }
    public void setUserId(int userId){
        super.userId = userId;
    }
    public void setBuyerId(int buyerId){
        this.buyerId = buyerId;
    }
    public void setBuyerName(String buyerName){
        this.buyerName = buyerName;
    }
    public void setOrderTime(String orderTime){
        this.orderTime = orderTime;
    }
    public void setUserCart(UserCart userOrder){
        this.userOrder = userOrder;
    }
}
