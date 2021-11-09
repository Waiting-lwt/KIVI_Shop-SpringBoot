package com.waiting.test.domain;

import java.util.List;

public class SellerOrder extends Seller {
    public UserCart userCart;
    public String orderTime;
    private String buyerId;
    public String buyerName;
    public String buyerImg;
    public SellerOrder(){
        userCart = null;
    }
    public void setUserId(int userId){
        super.userId = userId;
    }
    public void setOrderTime(String orderTime){
        this.orderTime = orderTime;
    }
    public void setUserCart(UserCart userCart){
        this.userCart = userCart;
    }
    public void addCart(UserCart userCart){
        this.userCarts.add(userCart);
    }
}
