package com.waiting.test.domain;

public class UserCart{
    public int goodId;
    public int sellerId;
    public int sellerName;
    public String goodName;
    public double goodPrice;
    public String goodImg;
    public int goodNum;
    public UserCart(){
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
