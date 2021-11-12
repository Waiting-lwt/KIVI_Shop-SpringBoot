package com.waiting.test.service;
import com.waiting.test.domain.Good;

import java.util.List;

public interface GoodService {
    public List<Good> getGoods();
    public Good selectGood(int goodId);
    public int addGood(Good good);
    public int updateGood(Good good);
    public int deleteGood(int goodId);
    public List<Good> searchGoods(String content);
    public List<Good> getSellerGoods(int userId);
}
