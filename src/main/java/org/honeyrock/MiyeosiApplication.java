package org.honeyrock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="org.honeyrock.mapper")
public class MiyeosiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiyeosiApplication.class, args);
	}
}
