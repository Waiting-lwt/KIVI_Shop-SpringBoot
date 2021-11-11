package com.waiting.test.domain;

import java.util.List;

public class UserBrowsed extends Seller {
    public int goodId;
    public String goodName;
    public String goodImg;
    public String browseTime;
    public int browserId;
    public String browserName;
    public void setUserId(int userId){
        super.userId = userId;
    }
    public void setGoodId(int goodId){
        this.goodId = goodId;
    }
    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg;
    }
    public void setGoodName(String goodName){
        this.goodName = goodName;
    }
    public void setBrowserId(int browserId){
        this.browserId = browserId;
    }
    public void setBrowserName(String browserName){
        this.browserName = browserName;
    }
    public void setBrowseTime(String browseTime){
        this.browseTime = browseTime;
    }

}
