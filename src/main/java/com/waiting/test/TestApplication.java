package com.waiting.test;

import com.waiting.test.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TestApplication {
	public static void main(String[] args) {
		System.out.print("TestApplication.class: ");
		System.out.println(TestApplication.class);
		SpringApplication.run(TestApplication.class, args);
	}
}
