package com.gaohanghang.mail.common.api;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/26 21:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket mailApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("邮件管理").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.gaohanghang.mail.web")).paths(PathSelectors.any()).build();
    }

    // 预览地址:swagger-ui.html
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring 中使用Swagger2构建文档").termsOfServiceUrl("http://ghang.top")
                .contact(new Contact("高行行", "http://ghang.top", "1341947277@qq.com")).version("1.2").build();
    }
}
