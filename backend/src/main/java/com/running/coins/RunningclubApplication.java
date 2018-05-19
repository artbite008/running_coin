package com.running.coins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.running.coins.dao")
public class RunningclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunningclubApplication.class, args);
	}
}