package com.zzazz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * ClassName: WebSecurityConfig
 * Package: com.zzazz.config
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 16:22
 * @Description: Spring Securityのコンフィグ
 * @Version: v1.0
 */
@Configuration
@EnableWebSecurity //@EnableWebSecurityはSpringSecurityのデフォルト行動をONにする
public class WebSecurityConfig {

}
