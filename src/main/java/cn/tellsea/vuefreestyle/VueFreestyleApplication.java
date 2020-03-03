package cn.tellsea.vuefreestyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Vue Freestyle 启动类
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@EnableAsync
@SpringBootApplication
public class VueFreestyleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueFreestyleApplication.class, args);
    }

}
