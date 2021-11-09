package com.waiting.test.repository;
import com.waiting.test.domain.Good;
import com.waiting.test.service.GoodService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class GoodDao implements GoodService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Good> getGoods(){
        String sql = "select * from goodInfo";
        try{
            //使用的queryForObject方法
            return (List<Good>) jdbcTemplate.query(sql, new RowMapper<Good>(){
                @Override
                public Good mapRow(ResultSet rs, int rowNum) throws SQLException{
                    Good good = new Good(
                            rs.getInt("goodId"),
                            rs.getString("goodName"),
                            rs.getDouble("goodPrice"),
                            rs.getInt("goodInventory"),
                            rs.getString("goodIntro"),
                            rs.getString("goodImg"),
                            rs.getInt("goodSeller"));
                    return good;
                }
            });
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public Good selectGood(int id){
        String sql = "select * from goodInfo where goodId = ?";
        try{
            //使用的queryForObject方法
            return jdbcTemplate.queryForObject(sql, new RowMapper<Good>(){
                @Override
                public Good mapRow(ResultSet rs, int rowNum) throws SQLException{
                    Good good = new Good(
                            rs.getInt("goodId"),
                            rs.getString("goodName"),
                            rs.getDouble("goodPrice"),
                            rs.getInt("goodInventory"),
                            rs.getString("goodIntro"),
                            rs.getString("goodImg"),
                            rs.getInt("goodSeller"));
                    return good;
                }
            },id);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Good> searchGoods(String content){
        String sql = "select * from goodInfo " +
                "where goodName LIKE concat('%',?,'%')";
        try{
            //使用的queryForObject方法
            return (List<Good>) jdbcTemplate.query(sql, new RowMapper<Good>(){
                @Override
                public Good mapRow(ResultSet rs, int rowNum) throws SQLException{
                    Good good = new Good(
                            rs.getInt("goodId"),
                            rs.getString("goodName"),
                            rs.getDouble("goodPrice"),
                            rs.getInt("goodInventory"),
                            rs.getString("goodIntro"),
                            rs.getString("goodImg"),
                            rs.getInt("goodSeller"));
                    return good;
                }
            },content);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Good> getSellerGoods(int userId) {
        String sql = "select * from goodInfo where goodSeller = ?";
        try{
            //使用的queryForObject方法
            return (List<Good>) jdbcTemplate.query(sql, new RowMapper<Good>(){
                @Override
                public Good mapRow(ResultSet rs, int rowNum) throws SQLException{
                    Good good = new Good(
                            rs.getInt("goodId"),
                            rs.getString("goodName"),
                            rs.getDouble("goodPrice"),
                            rs.getInt("goodInventory"),
                            rs.getString("goodIntro"),
                            rs.getString("goodImg"),
                            rs.getInt("goodSeller"));
                    return good;
                }
            },userId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
