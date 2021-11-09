package com.waiting.test.repository;

import com.waiting.test.domain.SellerOrder;
import com.waiting.test.domain.UserCart;
import com.waiting.test.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SellerDao implements SellerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SellerOrder> getSellerOrder(int sellerId) {
        String sql = "SELECT sellerId,buyerId,userInfo.userName as buyerName," +
                "orderLog.goodId,goodNum,goodImg,goodName,goodPrice,orderTime " +
                "FROM userInfo,goodInfo,orderLog " +
                "where sellerId = ? and goodInfo.goodId = orderLog.goodId and userInfo.userId = buyerId ";
        try{
            //使用的query方法
            return (List<SellerOrder>) jdbcTemplate.query(sql, new RowMapper<SellerOrder>(){
                @Override
                public SellerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SellerOrder order = new SellerOrder();
                    order.setUserId(sellerId);
                    // int userId, int goodId,String goodName,double goodPrice,int goodNum,String goodImg
                    order.setUserCart(new UserCart(
                                    rs.getInt("goodId"),
                                    rs.getString("goodName"),
                                    rs.getDouble("goodPrice"),
                                    rs.getInt("goodNum"),
                                    rs.getString("goodImg")));
                    order.setUserId(rs.getInt("sellerId"));
                    order.setBuyerId(rs.getInt("buyerId"));
                    order.setBuyerName(rs.getString("buyerName"));
                    order.setOrderTime(rs.getString("orderTime"));
                    return order;
                }
            },sellerId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
