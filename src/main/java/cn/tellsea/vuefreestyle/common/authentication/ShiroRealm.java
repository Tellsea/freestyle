package cn.tellsea.vuefreestyle.common.authentication;

import cn.tellsea.vuefreestyle.common.utils.RedisUtil;
import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import cn.tellsea.vuefreestyle.system.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * shiro 验证域
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
@Slf4j
@Service
public class ShiroRealm extends AuthorizingRealm {

    private UserInfoService userInfoService;

    private RedisUtil redisUtil;

    @Autowired
    public ShiroRealm(UserInfoService userInfoService, RedisUtil redisUtil) {
        super();
        this.userInfoService = userInfoService;
        this.redisUtil = redisUtil;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证.登录
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("令牌无效");
        }
        UserInfo userBean = (UserInfo) redisUtil.get(token);
        if (userBean == null) {
            throw new AuthenticationException("令牌已过期");
        } else {
            redisUtil.expire(token, 60);
            return new SimpleAuthenticationInfo(token, token, "MyRealm");
        }
    }

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String token = (String) principal.getPrimaryPrincipal();
        String username = JwtUtil.getUsername(token);
        List<String> permissions = new ArrayList<>();
        List<String> rolesName = new ArrayList<>();
        /*UserInfo user = userInfoService.findUserByUserName(username);
        List<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                rolesName.add(role.getRname());
                List<Module> modules = role.getModules();
                if (modules.size() > 0) {
                    for (Module module : modules) {
                        permissions.add(module.getMname());
                    }
                }
            }
        }*/
        rolesName.add("admin");
        rolesName.add("system");
        permissions.add("system:user:add");
        permissions.add("system:user:select");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(rolesName);
        info.addStringPermissions(permissions);
        return info;
    }
}