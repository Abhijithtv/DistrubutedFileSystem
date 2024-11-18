package com.DFS.MasterNode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MasterNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterNodeApplication.class, args);
	}

}
