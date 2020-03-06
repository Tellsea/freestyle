package cn.tellsea.freestyle.common.config;

import cn.tellsea.freestyle.common.properties.FreestyleProperties;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FreestyleProperties properties;

    @Bean
    public Docket systemApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统模块接口")
                .apiInfo(getApiInfo("Freestyle 系统模块"))
                .globalOperationParameters(getGlobalParam())
                .enable(properties.getSwagger().isEnabled())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tellsea.freestyle.system.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试模块接口")
                .apiInfo(getApiInfo("Freestyle 测试模块"))
                .globalOperationParameters(getGlobalParam())
                .enable(properties.getSwagger().isEnabled())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tellsea.freestyle.test.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private List<Parameter> getGlobalParam() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder().name("token")
                .description("用户登陆令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(false)
                .build());
        return parameters;
    }

    private ApiInfo getApiInfo(String title) {
        return new ApiInfoBuilder()
                .title(title)
                .description(properties.getSwagger().getDescription())
                .termsOfServiceUrl(properties.getSwagger().getTermsOfServiceUrl())
                .contact(new Contact(properties.getSwagger().getAuthor(), properties.getSwagger().getUrl(), properties.getSwagger().getEmail()))
                .version(properties.getSwagger().getVersion())
                .build();
    }
}
