package com.waiting.test.controller;

import com.waiting.test.domain.*;
import com.waiting.test.service.GoodService;
import com.waiting.test.repository.GoodDao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public int updateGood(@RequestBody Good good){
        return goodService.updateGood(good);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteGood", method = RequestMethod.PUT)
    public int deleteGood(@RequestBody Good good){
        return goodService.deleteGood(good);
    }

    @ResponseBody
    @RequestMapping(value = "/addGood", method = RequestMethod.POST)
    public int addGood(@RequestBody Good good){
        return goodService.addGood(good);
    }
}
