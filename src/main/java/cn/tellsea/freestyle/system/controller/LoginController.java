package cn.tellsea.freestyle.system.controller;

import cn.tellsea.freestyle.common.authentication.JwtUtil;
import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.common.properties.FreestyleProperties;
import cn.tellsea.freestyle.common.utils.RedisUtil;
import cn.tellsea.freestyle.system.entity.UserInfo;
import cn.tellsea.freestyle.system.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "登录接口")
@RestController
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FreestyleProperties properties;

    @ApiOperation("登录")
    @PostMapping("login")
    public ResponseResult login(@RequestParam(value = "userName") String userName,
                                @RequestParam(value = "password") String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return ResponseResult.error("用户名或者密码不能为空");
        }
        UserInfo userInfo = userInfoService.getBaseMapper().selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserName, userName));
        if (userInfo == null) {
            return ResponseResult.error("系统查询不到该用户");
        } else {
            String token = JwtUtil.sign(userName, password);
            redisUtil.set(token, userInfo, properties.getShiro().getJwtTokenTimeOut());

            // 返回前端所需数据
            Map<String, Object> map = new HashMap<>(16);
            map.put("token", token);
            map.put("userInfo", userInfo);
            map.put("roleList", userInfoService.getRoleByUserName(userName));
            map.put("permissionList", userInfoService.getPermissonByUserName(userName));
            return ResponseResult.success(map);
        }
    }

    @GetMapping("admin")
    @RequiresRoles("admin")
    public ResponseResult admin() {
        return ResponseResult.success();
    }

    @GetMapping("401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult unauthorized(HttpServletRequest request) {
        String data = (String) request.getAttribute("msg");
        return ResponseResult.error(data);
    }
}
