package com.zzazz.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Knife4JConfig
 * Package: com.zzazz.system.config
 *
 * @Author: zzazz
 * @Create: 2024/5/14 - 12:02
 * @Description: Knife4JConfig
 * @Version: v1.0
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4JConfig {

    @Bean
    public Docket adminApiConfig() {
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token")
                .description("ユーザーtoken")
                .defaultValue("")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());
        // addHeadParameterEnd

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                //pathがadmin以降の部分のみ表示
                .apis(RequestHandlerSelectors.basePackage("com.zzazz"))
                .paths(PathSelectors.regex("/admin/.*"))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("APIドキュメント")
                .description("APIを表示している")
                .version("1.0")
                .contact(new Contact("zzazz", "http://zzazz.com", "zzazz"))
                .build();
    }

}
