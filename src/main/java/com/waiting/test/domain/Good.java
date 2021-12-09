package com.waiting.test.domain;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Good {
    public int goodId;
    public String goodName;
    public double goodPrice;
    public int goodInventory;
    public String goodIntro;
    public String goodImg;
    public int goodSeller;
    public String goodSellerName;

    public Good() {
    }
    public Good(int goodId,String goodName,double goodPrice,int goodInventory,
                String goodIntro,String goodImg,int goodSeller,String goodSellerName){
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.goodInventory = goodInventory;
        this.goodIntro = goodIntro;
        this.goodImg = goodImg;
        this.goodSeller = goodSeller;
        this.goodSellerName = goodSellerName;
    }

    public void setGoodId(int goodId){
        this.goodId = goodId;
    }
    public void setGoodName(String goodName){
        this.goodName = goodName;
    }

    public void setGoodImg(String goodImg){
        this.goodImg = goodImg;
    }
    public String getGoodName(){
        return this.goodName;
    }
    public double getGoodPrice(){
        return this.goodPrice;
    }
}