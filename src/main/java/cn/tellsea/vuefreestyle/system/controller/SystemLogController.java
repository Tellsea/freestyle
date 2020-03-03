package cn.tellsea.vuefreestyle.system.controller;

import cn.tellsea.vuefreestyle.common.annotation.ControllerLog;
import cn.tellsea.vuefreestyle.common.entity.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志表 控制器
 *
 * @author Tellsea
 * @date 2020-03-03
 */
@Api(tags = "系统日志表接口")
@RestController
@RequestMapping("/system/systemLog")
public class SystemLogController {

    @GetMapping("test")
    @ControllerLog
    public ResponseResult test() {
        return new ResponseResult(HttpStatus.OK, "测试成功");
    }

    @GetMapping("test2")
    public ResponseResult test2() {
        return new ResponseResult(HttpStatus.OK, "测试成功2");
    }
}
