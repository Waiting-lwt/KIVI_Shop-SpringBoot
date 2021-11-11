package com.waiting.test.repository;

import com.waiting.test.domain.*;
import com.waiting.test.service.MailService;
import com.waiting.test.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class UserDao implements UserService{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addUser(String name, String password, String email){
        // 检查是否有重名
        boolean exit = true;
        String sqlsel = "select exists (select * from userInfo where userName = ?)";
        try{
            exit = jdbcTemplate.queryForObject(sqlsel,boolean.class,name);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }
        if(exit==true){
            return -1;
        }

        // 返回maxId
        int userId = 0;
        String sqlm = "select MAX(userId) from userInfo";
        try{
            userId = jdbcTemplate.queryForObject(sqlm,int.class) + 1;
        }catch(EmptyResultDataAccessException e){
            return 0;
        }

        // add
        String sqladd = "insert into userInfo(userId, userName, userType, userPassword, userEmail) values(?, ?, ?, ?, ?)";
        Object[] params = new Object[]{userId,name,1,password,email};
        try{
            jdbcTemplate.update(sqladd, params);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }finally {
            return userId;
        }
    }

    public User selectUser(String name) {
        String sql = "select * from userInfo where userName = ?";
        try{
            //使用的queryForObject方法
            return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException{
                    User stu = new User(
                            rs.getInt("userId"),
                            rs.getString("userName"),
                            rs.getShort("userType"),
                            rs.getString("userPassword"),
                            rs.getString("userEmail"));
                    return stu;
                }
            }, name);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<UserCart> getCart(int userId) {
        String sql = "SELECT userCart.goodId,goodNum,goodImg,goodName,goodPrice " +
                "FROM goodInfo,userCart " +
                "where userId = ? and goodInfo.goodId = userCart.goodId;";
        try{
            //使用的query方法
            return (List<UserCart>) jdbcTemplate.query(sql, new RowMapper<UserCart>(){
                @Override
                public UserCart mapRow(ResultSet rs, int rowNum) throws SQLException{
                    UserCart cart = new UserCart();
                    cart.setGoodId(rs.getInt("goodId"));
                    cart.setGoodNum(rs.getInt("goodNum"));
                    cart.setGoodImg(rs.getString("goodImg"));
                    cart.setGoodName(rs.getString("goodName"));
                    cart.setGoodPrice(rs.getDouble("goodPrice"));
                    return cart;
                }
            },userId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public int addCart(int userId,int goodId,int goodNum,String addTime) {
        String sql;
        // 检查已有商品
        boolean exit = true;
        String sqlsel = "select exists (select * from userCart where userId = ? and goodId= ?)";
        try{
            exit = jdbcTemplate.queryForObject(sqlsel,boolean.class,userId,goodId);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }
        if(exit==true){
            sql = "Update userCart " +
                    "set goodNum = goodNum + ? , addTime = ? " +
                    "where userId = ? and goodId = ?";
        }
        else{
            sql = "insert into userCart " +
                    "(goodNum,addTime,userId,goodId) " +
                    "values(?, ?, ?, ?);";
        }
        try {
            //使用的query方法
            int ret = jdbcTemplate.update(sql, goodNum, addTime, userId, goodId);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }finally {
            return 1;
        }
    }

    public int subCart(int userId,int goodId,int goodNum,String addTime) {
        String sql;
        // 检查已有商品
        boolean exit = true;
        String sqlsel = "select exists (select * from userCart where userId = ? and goodId= ?)";
        try{
            exit = jdbcTemplate.queryForObject(sqlsel,boolean.class,userId,goodId);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }
        if(exit==false){
            return -1;
        }

        sql = "Update userCart " +
                "set goodNum = goodNum - ? , addTime = ? " +
                "where userId = ? and goodId = ?";
        try {
            //使用的query方法
            int ret = jdbcTemplate.update(sql, goodNum, userId, goodId);
        }catch(EmptyResultDataAccessException e){
            return 0;
        }finally {
            return 1;
        }
    }

    public int addOrder(UserOrder userOrder){
        return 1;
    }


    public List<BuyerOrder> getBuyerOrder(int buyerId) {
        String sql = "SELECT sellerId,buyerId,userInfo.userName as sellerName," +
                "orderLog.goodId,goodNum,goodImg,goodName,goodPrice,orderTime " +
                "FROM userInfo,goodInfo,orderLog " +
                "where buyerId = ? and goodInfo.goodId = orderLog.goodId and userInfo.userId = sellerId ";
        try{
            //使用的query方法
            return (List<BuyerOrder>) jdbcTemplate.query(sql, new RowMapper<BuyerOrder>(){
                @Override
                public BuyerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
                    BuyerOrder order = new BuyerOrder();
                    order.setUserId(buyerId);
                    // int userId, int goodId,String goodName,double goodPrice,int goodNum,String goodImg
                    order.setUserCart(new UserCart(
                            rs.getInt("goodId"),
                            rs.getString("goodName"),
                            rs.getDouble("goodPrice"),
                            rs.getInt("goodNum"),
                            rs.getString("goodImg")));
                    order.setUserId(rs.getInt("buyerId"));
                    order.setSellerId(rs.getInt("sellerId"));
                    order.setSellerName(rs.getString("sellerName"));
                    order.setOrderTime(rs.getString("orderTime"));
                    return order;
                }
            },buyerId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }


    public List<UserBrowsed> getUserBrowsed(int userId) {
        String sql = "SELECT browseLog.userId,browserId,userInfo.userName as browserName," +
                "browseLog.goodId,goodImg,goodName,browseTime " +
                "FROM userInfo,goodInfo,browseLog " +
                "where browseLog.userId = ? and goodInfo.goodId = browseLog.goodId and userInfo.userId = browseLog.userId ";
        try{
            //使用的query方法
            return (List<UserBrowsed>) jdbcTemplate.query(sql, new RowMapper<UserBrowsed>(){
                @Override
                public UserBrowsed mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserBrowsed userBrowsed = new UserBrowsed();
                    userBrowsed.setUserId(userId);
                    // int userId, int goodId,String goodName,double goodPrice,int goodNum,String goodImg
                    userBrowsed.setGoodId(rs.getInt("goodId"));
                    userBrowsed.setGoodName(rs.getString("goodName"));
                    userBrowsed.setGoodImg(rs.getString("goodImg"));
                    userBrowsed.setUserId(rs.getInt("userId"));
                    userBrowsed.setBrowserId(rs.getInt("browserId"));
                    userBrowsed.setBrowserName(rs.getString("browserName"));
                    userBrowsed.setBrowseTime(rs.getString("browseTime"));
                    return userBrowsed;
                }
            },userId);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
