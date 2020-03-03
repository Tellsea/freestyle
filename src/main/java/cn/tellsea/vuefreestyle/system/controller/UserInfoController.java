package cn.tellsea.vuefreestyle.system.controller;

import cn.tellsea.vuefreestyle.common.controller.BaseController;
import cn.tellsea.vuefreestyle.common.utils.SpringUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表 控制器
 *
 * @author: Tellsea
 * @date : 2020/3/2
 */
@Api(tags = "用户表接口")
@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController extends BaseController {

    @GetMapping("test")
    public String test() {
        System.out.println(SpringUtil.getApplicationContext());
        return "hello";
    }
}
