package com.lh;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication
@EnableDubbo
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@MapperScan("com.lh.mapper")
public class LhShopBackgroundServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LhShopBackgroundServiceApplication.class, args);
	}

}
