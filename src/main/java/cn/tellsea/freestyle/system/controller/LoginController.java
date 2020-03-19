package cn.tellsea.freestyle.system.controller;

import cn.tellsea.freestyle.common.authentication.JwtUtil;
import cn.tellsea.freestyle.common.entity.FreestyleConst;
import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.common.exception.FreestyleException;
import cn.tellsea.freestyle.common.properties.FreestyleProperties;
import cn.tellsea.freestyle.common.utils.IpUtil;
import cn.tellsea.freestyle.common.utils.RedisUtil;
import cn.tellsea.freestyle.system.entity.ResourceInfo;
import cn.tellsea.freestyle.system.entity.RoleInfo;
import cn.tellsea.freestyle.system.entity.UserInfo;
import cn.tellsea.freestyle.system.service.ResourceInfoService;
import cn.tellsea.freestyle.system.service.RoleInfoService;
import cn.tellsea.freestyle.system.service.UserInfoService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 登录 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "登录接口")
@Validated
@RestController
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private ResourceInfoService resourceInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FreestyleProperties properties;

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult login(@NotNull(message = "用户名不能为空") @RequestParam("username") String username,
                                @NotNull(message = "密码不能为空") @RequestParam("password") String password,
                                HttpServletRequest request) {
        UserInfo userInfo = userInfoService.getByUserName(username);
        if (userInfo == null) {
            return ResponseResult.errorMsg("用户不存在");
        }
        if (!StringUtils.equals(userInfo.getPassword(), password)) {
            return ResponseResult.errorMsg("密码错误");
        } else {
            String token = JwtUtil.sign(username, password);
            String ip = IpUtil.getClientIp(request);
            //redisUtil.set(FreestyleConst.TOKEN_PREFIX + token + StringPool.DOT + ip, userInfo, properties.getShiro().getJwtTokenTimeOut());
            redisUtil.set(token, userInfo, properties.getShiro().getJwtTokenTimeOut());
            // 返回前端所需数据
            Map<String, Object> map = new HashMap<>(16);
            map.put("token", token);
            map.put("userInfo", userInfo);
            map.put("roleList", roleInfoService.getByUserName(username).stream().map(RoleInfo::getName).collect(Collectors.toSet()));
            map.put("permissionList", resourceInfoService.getByUserName(username).stream().map(ResourceInfo::getPerms).collect(Collectors.toSet()));
            map.put("routerList", resourceInfoService.getByUserName(username));
            return ResponseResult.success(map);
        }
    }

    @ApiOperation("根据token获取账号信息")
    @GetMapping("getUserInfoByUserName")
    public ResponseResult getUserInfo(@NotNull(message = "token不能为空") @RequestParam("token") String token) {

    }





    @GetMapping("admin")
    @RequiresRoles("admin")
    public ResponseResult admin() throws FreestyleException {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println(token);
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            throw new FreestyleException("测试自定义异常");
        }
        return ResponseResult.success();
    }

    @GetMapping("testValid")
    public ResponseResult testValid(@Valid UserInfo userInfo) {
        System.out.println(userInfo);
        return ResponseResult.success();
    }

    @GetMapping("401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult unauthorized(HttpServletRequest request) {
        String data = (String) request.getAttribute("msg");
        return ResponseResult.error(data);
    }
}
