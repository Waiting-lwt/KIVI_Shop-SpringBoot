package com.waiting.test.domain;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class User {
    public int userId;
    public String userName;
    public short userType;
    private String userPassword;
    private String userEmail;
    public User(){
    }
    public User(int userId,String userName,short userType,String userPassword,String userEmail){
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    public boolean passCheck(String password){
        return password.equals(this.userPassword);
    }
    public String getUserName(){
        return this.userName;
    }
    public String getUserEmail(){
        return this.userEmail;
    }
}
