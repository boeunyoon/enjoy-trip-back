package com.pjt.ssafybuddy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.pjt.ssafybuddy.mapper")
public class SsafybuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsafybuddyApplication.class, args);
	}

}
