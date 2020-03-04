package cn.tellsea.vuefreestyle.system.controller;

import cn.tellsea.vuefreestyle.common.authentication.JwtUtil;
import cn.tellsea.vuefreestyle.common.entity.ResponseResult;
import cn.tellsea.vuefreestyle.common.utils.RedisUtil;
import cn.tellsea.vuefreestyle.system.entity.UserInfo;
import cn.tellsea.vuefreestyle.system.service.UserInfoService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("getToken")
    public ResponseResult login(@RequestParam("userName") String userName,
                                @RequestParam("password") String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return new ResponseResult(HttpStatus.UNAUTHORIZED, "用户名和密码不能为空");
        }
        UserInfo userInfo = new UserInfo();
        String token = JwtUtil.sign(userName, password);
        redisUtil.set(token, userInfo, 60);
        return new ResponseResult(HttpStatus.OK, "成功", token);
    }

    @GetMapping("admin")
    @RequiresRoles("admin")
    public ResponseResult admin() {
        return new ResponseResult(HttpStatus.OK, "成功");
    }

    @GetMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseResult unauthorized(HttpServletRequest request) {
        String data = (String) request.getAttribute("msg");
        return new ResponseResult(HttpStatus.UNAUTHORIZED, "认证失败");
    }
}
