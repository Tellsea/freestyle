package cn.tellsea.freestyle.common.annotation;

import java.lang.annotation.*;

/**
 * 控制器日志 注解
 *
 * @author: Tellsea
 * @date : 2020/3/3
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerLog {

}
