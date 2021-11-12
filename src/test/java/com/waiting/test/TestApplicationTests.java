package com.waiting.test;

import com.waiting.test.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class TestApplicationTests {
	//数据源组件
	@Autowired
	private DataSource dataSource;
	//用于访问数据库的组件
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MailService mailService;

	@Test
	void contextLoads() throws SQLException {
	}
}
