package cn.tellsea.freestyle.common.config;

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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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

//    @Value(value = "${freestyle.swagger.enabled}")
//    Boolean swaggerEnabled;

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统模块接口")
                .apiInfo(getApiInfo("VueFreestyle 系统模块", "1.0"))
                .globalOperationParameters(getGlobalparam())
                //.enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tellsea.freestyle.system.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试模块接口")
                .apiInfo(getApiInfo("VueFreestyle 测试模块", "1.0"))
                .globalOperationParameters(getGlobalparam())
                //.enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tellsea.freestyle.test.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> getGlobalparam() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("token")
                .description("用户登陆令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(false)
                .build());
        return parameters;
    }

    private ApiInfo getApiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .description("更多请关注: https://github.com/tellsea")
                .termsOfServiceUrl("https://github.com/tellsea")
                .contact(new Contact("Tellsea", "https://blog.csdn.net/qq_38762237", "3210054449@qq.com"))
                .version(version)
                .build();
    }
}
