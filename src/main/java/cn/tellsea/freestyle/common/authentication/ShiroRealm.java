package cn.tellsea.freestyle.common.authentication;

import cn.tellsea.freestyle.common.properties.FreestyleProperties;
import cn.tellsea.freestyle.common.utils.RedisUtil;
import cn.tellsea.freestyle.system.entity.ResourceInfo;
import cn.tellsea.freestyle.system.entity.RoleInfo;
import cn.tellsea.freestyle.system.entity.UserInfo;
import cn.tellsea.freestyle.system.service.ResourceInfoService;
import cn.tellsea.freestyle.system.service.RoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

import java.util.Set;
import java.util.stream.Collectors;

/**
 * shiro 验证域
 *
 * @author: Tellsea
 * @date : 2020/3/4
 */
@Slf4j
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private ResourceInfoService resourceInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FreestyleProperties properties;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String token = (String) principal.getPrimaryPrincipal();
        String userName = JwtUtil.getUsername(token);
        // 设置角色集合
        Set<String> roleSet = roleInfoService.getByUserName(userName).stream().map(RoleInfo::getName).collect(Collectors.toSet());
        info.addRoles(roleSet);
        // 设置权限集合
        Set<String> permissionSet = resourceInfoService.getByUserName(userName).stream().map(ResourceInfo::getPerms).collect(Collectors.toSet());
        info.setStringPermissions(permissionSet);
        return info;
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
        // 解密获得userName您还没有登录呦
        String userName = JwtUtil.getUsername(token);
        if (StringUtils.isEmpty(userName)) {
            throw new AuthenticationException("令牌无效");
        }
        UserInfo userBean = (UserInfo) redisUtil.get(token);
        if (userBean == null) {
            throw new AuthenticationException("令牌已过期");
        } else {
            // 延长token一小时
            redisUtil.expire(token, properties.getShiro().getJwtTokenTimeOut());
            return new SimpleAuthenticationInfo(token, token, "ShiroRealm");
        }
    }
}
