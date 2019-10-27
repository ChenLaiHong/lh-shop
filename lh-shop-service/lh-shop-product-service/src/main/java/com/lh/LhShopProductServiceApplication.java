package com.lh;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.lh.mapper")
public class LhShopProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LhShopProductServiceApplication.class, args);
	}

}
