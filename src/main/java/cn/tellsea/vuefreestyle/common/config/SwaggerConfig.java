package cn.tellsea.vuefreestyle.common.config;

import org.springframework.context.annotation.Bean;
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
 * swagger2 api 配置
 *
 * @link 接口扩展：https://blog.csdn.net/xqnode/article/details/86557784
 * @author: Tellsea
 * @date : 2020/3/3
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统模块接口")
                .apiInfo(apiInfo("VueFreestyle System Api", "1.0"))
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tellsea.vuefreestyle.system.controller"))
                .paths(PathSelectors.regex("/system.*"))
                .build();
    }

    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试模块接口")
                .apiInfo(apiInfo("VueFreestyle Test Api", "1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tellsea.vuefreestyle.test.controller"))
                .paths(PathSelectors.regex("/test.*"))
                .build();
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("更多请关注: https://github.com/tellsea")
                .termsOfServiceUrl("https://github.com/tellsea")
                .contact(new Contact("Tellsea", "https://blog.csdn.net/qq_38762237", "3210054449@qq.com"))
                .version(version)
                .build();
    }
}
