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

    public int addGood(Good good) {
        // 返回maxId
        int goodId = 0;
        String sqlm = "select MAX(goodId) from goodInfo";
        try{
            goodId = jdbcTemplate.queryForObject(sqlm,int.class) + 1;
        }catch(EmptyResultDataAccessException e){
            return 0;
        }

        String sql = "insert into goodInfo " +
                "(goodId, goodName, goodPrice, " +
                "goodInventory, goodImg, goodSeller)" +
                " values(?, ?, ?, ?, ?, ?)";
        try {
            int ret = jdbcTemplate.update
                    (sql, goodId, good.goodName, good.goodPrice,
                     good.goodInventory, good.goodImg, good.goodSeller);
        }catch(EmptyResultDataAccessException e){
            return -1;
        }finally {
            return 0;
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

    public int updateGood(Good good){
        String sql = "update goodInfo " +
                "set goodName = ?,goodPrice = ?,goodInventory = ?,goodImg = ? "+
                "where goodId = ? ";
        try {
            //使用的query方法
            int ret = jdbcTemplate.update
                    (sql, good.goodName, good.goodPrice, good.goodInventory, good.goodImg, good.goodId);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }finally {
            return 1;
        }
    }

    public int deleteGood(int goodId) {
        String sql = "delete from goodInfo " +
                "where goodId = ? ";
        try {
            //使用的query方法
            int ret = jdbcTemplate.update
                    (sql, goodId);
        }catch(EmptyResultDataAccessException e){
            return -1;
        }finally {
            return 0;
        }
    }

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
