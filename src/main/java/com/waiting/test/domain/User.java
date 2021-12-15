package com.waiting.test.domain;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.waiting.test.utils.TokenUtil;
import lombok.Data;
import lombok.Builder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

import java.util.Date;

//@Data
//@Builder
public class User {
    public int userId;
    public String userName;
    public short userType;
    private String userPassword;
    private String userEmail;
    private String userToken;
    public User(){
        this.userId = 0;
        this.userName = "";
        this.userType = 0;
        this.userPassword = "";
        this.userEmail = "";
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
    public String getUserPassword(){
        return this.userPassword;
    }

    public void setUserToken(String userToken){
        this.userToken = userToken;
    }
    public Date getTokenExpireTime() {
        try {
            return JWT.decode(this.userToken).getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public Integer getUserTokenId() {
        try {
            String userIdStr = JWT.decode(this.userToken).getClaim("userId").asString();
            System.out.println(userIdStr);
            return Integer.valueOf(userIdStr);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
