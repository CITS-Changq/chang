package com.example.batchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Spring Boot のエントリーポイントとなるクラスです。
 * 
 * @author XXX
 */
@SpringBootApplication
public class BatchDemoApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(BatchDemoApplication.class, args)));
	}

}
