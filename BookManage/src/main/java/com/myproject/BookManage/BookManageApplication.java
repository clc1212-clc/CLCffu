package com.myproject.BookManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.myproject.BookManage.mapper")
@SpringBootApplication
public class BookManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManageApplication.class, args);
	}

}
