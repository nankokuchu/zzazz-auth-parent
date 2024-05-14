package com.zzazz.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: ServiceAuthApplication
 * Package: com.zzazz.system
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 10:01
 * @Description: main
 * @Version: v1.0
 */
@SpringBootApplication
@MapperScan("com.zzazz.system.mapper")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }
}
