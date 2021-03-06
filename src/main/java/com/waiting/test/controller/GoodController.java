package com.waiting.test.controller;

import com.waiting.test.domain.*;
import com.waiting.test.service.GoodService;
import com.waiting.test.repository.GoodDao;

import java.util.List;

import com.waiting.test.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/good")
public class GoodController {
    @Autowired
    private GoodService goodService;

    @ResponseBody
    @RequestMapping(value = "/getGood", method = RequestMethod.GET)
    public List<Good> getGood(){ //int pageNum, int pageSize
        return goodService.getGoods();
    }

    @ResponseBody
    @RequestMapping(value = "/selectGood", method = RequestMethod.GET)
    public Good selectGood(int goodId){
        return goodService.selectGood(goodId);
    }

    @ResponseBody
    @RequestMapping(value = "/searchGood", method = RequestMethod.GET)
    public List<Good> searchGood(String content){
        return goodService.searchGoods(content);
    }

    @ResponseBody
    @RequestMapping(value = "/updateGood", method = RequestMethod.PUT)
    public int updateGood(@RequestBody Good good,@CookieValue(value = "userToken", defaultValue = "0") String userToken){
        good.goodSeller = TokenUtil.getUserId(userToken);
        return goodService.updateGood(good);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteGood", method = RequestMethod.POST)
    public int deleteGood(@RequestBody Map<String,Integer> request) {
        return goodService.deleteGood((Integer) request.get("goodId"));
    }

    @ResponseBody
    @RequestMapping(value = "/addGood", method = RequestMethod.POST)
    public int addGood(@RequestBody Good good,@CookieValue(value = "userToken", defaultValue = "0") String userToken){
        good.goodSeller = TokenUtil.getUserId(userToken);
        return goodService.addGood(good);
    }
}
