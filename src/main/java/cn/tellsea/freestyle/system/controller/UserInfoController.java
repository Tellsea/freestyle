package cn.tellsea.freestyle.system.controller;

import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.common.enums.StatusEnums;
import cn.tellsea.freestyle.system.entity.UserInfo;
import cn.tellsea.freestyle.system.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表 控制器
 *
 * @author Tellsea
 * @date 2020-03-04
 */
@Api(tags = "用户表接口")
@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("用户列表")
    @PostMapping("listByTable")
    public ResponseResult listByTable(UserInfo userInfo) {
        return userInfoService.listByTable(userInfo);
    }

    @ApiOperation("新增用户")
    @PostMapping("save")
    public ResponseResult save(UserInfo userInfo) {
        userInfoService.saveUserInfo(userInfo);
        return ResponseResult.success(StatusEnums.SAVE_SUCCESS);
    }

    @ApiOperation("更新用户")
    @PostMapping("update")
    public ResponseResult update(UserInfo userInfo) {
        userInfoService.updateUserInfo(userInfo);
        return ResponseResult.success(StatusEnums.UPDATE_SUCCESS);
    }

    @ApiOperation("修改用户状态")
    @PostMapping("updateStatus")
    public ResponseResult updateStatus(UserInfo userInfo) {
        userInfoService.updateStatus(userInfo);
        return ResponseResult.success(StatusEnums.DELETE_SUCCESS);
    }
}
