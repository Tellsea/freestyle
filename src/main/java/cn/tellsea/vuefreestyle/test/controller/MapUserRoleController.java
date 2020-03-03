package cn.tellsea.vuefreestyle.test.controller;

import cn.tellsea.vuefreestyle.test.entity.MapUserRole;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色关联表 控制器
 *
 * @author Tellsea
 * @date 2020-03-03
 */
@Api(tags = "用户角色关联表接口")
@RestController
@RequestMapping("/test/mapUserRole")
public class MapUserRoleController {

    @GetMapping("test")
    public String test(MapUserRole mapUserRole) {
        return "hello";
    }
}
