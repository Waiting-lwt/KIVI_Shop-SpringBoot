package com.waiting.test.componet;

import com.alibaba.fastjson.JSON;
import com.waiting.test.domain.User;
import com.waiting.test.service.UserService;
import com.waiting.test.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = TokenUtil.getRequestToken(request);
        //如果token为空
        if (token==null) {
//            response.setStatus(response.SC_MOVED_TEMPORARILY);
//            response.setStatus(10011);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg","用户未登录，请先登录");
            map.put("code",10011);
            out.write(JSON.toJSONString(map));
            return false;
        }
        //1. 根据token，查询用户信息
//        User user = userService.findByToken(token);
        //2. 若用户不存在,
        if (TokenUtil.getUserId(token) == 0) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg","用户不存在");
            map.put("code",10010);
            out.write(JSON.toJSONString(map));
            return false;
        }
        //3. token失效
        if (TokenUtil.getExpireTime(token).before(new Date())) {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg","用户登录凭证已失效，请重新登录");
            map.put("code",10010);
            out.write(JSON.toJSONString(map));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
    //返回错误信息
    private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //UTF-8编码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status);
        PrintWriter out = response.getWriter();
        out.write(msg);
    }

}