package com.example.ehmall.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 配置接口文档类
 * @author 施立豪
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        /**
         * @Author
         * @Description //关于分组接口,可后期根据多模块,拆解为根据模块来管理API文档
         * @Date 10:18 2020/9/29
         * @Param []
         * @return springfox.documentation.spring.web.plugins.Docket
         **/
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("数据访问层接口")
                .apiInfo(apiInfo())
                .host("localhost:8080")
                .select()
                //要扫描的接口的包
                .apis(RequestHandlerSelectors.basePackage("com.example.ehmall.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试项目API")
                .description("后台管理系统")
                //作者
                .contact("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}

