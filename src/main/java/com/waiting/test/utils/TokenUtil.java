package com.waiting.test.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.waiting.test.domain.Base64Util;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@Component
public class TokenUtil{

    /**
     * 生成token
     * @param user
     * @return
     */
    //过期时间1天
    private static final long EXPIRE_TIME = 1*24*60*60*1000;
    //token秘钥,设置的复杂点这里用一串uuid，并用HMAC256加密的
    private static final String TOKEN_SECRET = "JFKDJFKGFGFGIFG8R9589589";

    //生成token
    public static String generatorToken(String userName,String userId) {
        //过期时间
        Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //秘钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String,Object> header = new HashMap<String,Object>(2);
        header.put("type","JWT");
        header.put("alg","HS256");
        //附带用户信息，生成token
        return JWT.create()
                .withHeader(header)
                .withClaim("userName",userName)
                .withClaim("userId",userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     *
     * @param token
     * @param key
     * @return userId
     * 获取制定token中某个属性值
     */
    public static String get(String token, String key) {
        List<String> list= JWT.decode(token).getAudience();
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static Integer getUserId(String token) {
        try {
            String userIdStr = JWT.decode(token).getClaim("userId").asString();
            Integer userId = Integer.valueOf(userIdStr);
            return userId;
        } catch (JWTDecodeException e) {
            return 0;
        }
    }

    public static Date getExpireTime(String token) {
        try {
            return JWT.decode(token).getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }


    /**
     *
     * @param request
     * @return
     * 获取token
     */
    public static String getRequestToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c :
                cookies) {
            if (c.getName().equals("userToken")) {
                return c.getValue();
            }
        }
        return null;
    }
}
