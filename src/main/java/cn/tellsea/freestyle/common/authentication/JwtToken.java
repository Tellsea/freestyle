package cn.tellsea.freestyle.common.authentication;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * jwt token
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
