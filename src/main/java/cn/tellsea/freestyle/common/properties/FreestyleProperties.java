package cn.tellsea.freestyle.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 系统属性
 *
 * @author Tellsea
 * @date 2020/3/5
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "freestyle")
public class FreestyleProperties {

    private SwaggerProperties swagger;

    private ShiroProperties shiro;
}
