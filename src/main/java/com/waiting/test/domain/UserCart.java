package com.waiting.test.domain;

public class UserCart{
    public int goodId;
    public int sellerId;
    public String sellerName;
    public String goodName;
    public double goodPrice;
    public String goodImg;
    public int goodNum;
    public UserCart(){
        this.goodId = 0;
        this.sellerId = 0;
        this.sellerName = null;
        this.goodName = null;
        this.goodPrice = 0;
        this.goodImg = null;
        this.goodNum = 0;
    }
    public UserCart(int goodId,String goodName,double goodPrice,int goodNum,String goodImg){
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodImg = goodImg;
        this.goodNum = goodNum;
    }

    public void setGoodId(int goodId){
        this.goodId = goodId;
    }
    public void setGoodName(String goodName){
        this.goodName = goodName;
    }
    public void setGoodPrice(double goodPrice){
        this.goodPrice = goodPrice;
    }
    public void setGoodImg(String goodImg){
        this.goodImg = goodImg;
    }
    public void setGoodNum(int goodNum){
        this.goodNum = goodNum;
    }
    public void setSellerId(int sellerId){
        this.sellerId = sellerId;
    }
    public void setSellerName(String sellerName){
        this.sellerName = sellerName;
    }

    public String getGoodName(){
        return this.goodName;
    }
    public double getGoodPrice(){
        return this.goodPrice;
    }
    public int getGoodNum(){
        return this.goodNum;
    }
}
