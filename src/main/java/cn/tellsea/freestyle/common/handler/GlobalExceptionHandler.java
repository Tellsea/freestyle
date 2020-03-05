package cn.tellsea.freestyle.common.handler;

import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.common.enums.StatusEnums;
import cn.tellsea.freestyle.common.exception.FreestyleException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Tellsea
 * @date 2020/3/5
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleException(Exception e) {
        log.error("全局异常处理器Exception：{}", e);
        return ResponseResult.error(StatusEnums.SERVER_ERROR);
    }

    @ExceptionHandler(value = FreestyleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleFreestyleException(FreestyleException e) {
        log.error("全局异常处理器FreestyleException：{}", e.getMessage());
        return ResponseResult.error(e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleUnauthorizedException(Exception e) {
        log.error("全局异常处理器UnauthorizedException：{}", e.getMessage());
    }
}
