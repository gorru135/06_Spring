package edu.kh.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


// exclude= {SecurityAutoConfiguration.class} Spring Security에서 기본 제공하는 로그인페이지 이용 안하겠다! 
@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class BoardProjectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardProjectBootApplication.class, args);
	}

}
