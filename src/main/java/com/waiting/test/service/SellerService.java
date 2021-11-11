package com.waiting.test.service;

import com.waiting.test.domain.UserBrowsed;
import com.waiting.test.domain.SellerOrder;

import java.util.List;

public interface SellerService {
    List<SellerOrder> getSellerOrder(int sellerId);
}
