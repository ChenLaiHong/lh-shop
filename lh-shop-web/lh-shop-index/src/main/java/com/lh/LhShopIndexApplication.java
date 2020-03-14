package com.lh;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import({FdfsClientConfig.class})
public class LhShopIndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(LhShopIndexApplication.class, args);
	}

}
