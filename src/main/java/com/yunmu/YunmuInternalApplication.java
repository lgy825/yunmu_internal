package com.yunmu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yunmu.back","com.yunmu.bapp","com.yunmu.core"})
@MapperScan(basePackages = {"com.yunmu.core.dao"})
public class YunmuInternalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunmuInternalApplication.class, args);
	}

}
