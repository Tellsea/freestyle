package cn.tellsea.freestyle.system.controller;

import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.common.enums.StatusEnums;
import cn.tellsea.freestyle.system.entity.RoleInfo;
import cn.tellsea.freestyle.system.service.RoleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色表 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "角色表接口")
@RestController
@RequestMapping("/system/roleInfo")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    @ApiOperation("角色列表")
    @PostMapping("listByTable")
    public ResponseResult listByTable(RoleInfo roleInfo) {
        return roleInfoService.listByTable(roleInfo);
    }

    @ApiOperation("新增角色")
    @PostMapping("save")
    public ResponseResult save(RoleInfo roleInfo) {
        roleInfoService.saveRole(roleInfo);
        return ResponseResult.success(StatusEnums.SAVE_SUCCESS);
    }

    @ApiOperation("更新角色")
    @PostMapping("update")
    public ResponseResult update(RoleInfo roleInfo) {
        roleInfoService.updateRole(roleInfo);
        return ResponseResult.success(StatusEnums.UPDATE_SUCCESS);
    }

    @ApiOperation("删除角色")
    @PostMapping("deleteById")
    public ResponseResult deleteById(int id) {
        roleInfoService.deleteRole(id);
        return ResponseResult.success(StatusEnums.DELETE_SUCCESS);
    }
}
