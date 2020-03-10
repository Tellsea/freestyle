package cn.tellsea.freestyle.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis plus 配置
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@Configuration
@MapperScan("cn.tellsea.freestyle.*.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型，多租户
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
