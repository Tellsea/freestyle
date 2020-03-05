package cn.tellsea.freestyle.common.properties;

import lombok.Data;

/**
 * shiro 属性
 *
 * @author Tellsea
 * @date 2020/3/5
 */
@Data
public class ShiroProperties {

    /**
     * jwt token 时长，默认3600，单位秒
     */
    private int jwtTokenTimeOut;
}
