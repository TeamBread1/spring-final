package com.bbangduck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Scheduled ��� ������̼��� ����ϱ� ���� �ʿ��� ������̼�
@EnableScheduling
@SpringBootApplication
public class BbangduckSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbangduckSpringApplication.class, args);
	}

}
