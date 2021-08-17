package com.bbangduck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Scheduled 라는 어노테이션을 사용하기 위해 필요한 어노테이션
@EnableScheduling
@SpringBootApplication
public class BbangduckSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbangduckSpringApplication.class, args);
	}

}
