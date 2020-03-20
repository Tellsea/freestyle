package cn.tellsea.freestyle.common.handler;

import cn.tellsea.freestyle.common.entity.ResponseResult;
import cn.tellsea.freestyle.common.enums.StatusEnums;
import cn.tellsea.freestyle.common.exception.FreestyleException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

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
        log.error("Exception：{}", "服务器错误");
        e.printStackTrace();
        return ResponseResult.error(StatusEnums.SERVER_ERROR);
    }

    @ExceptionHandler(value = FreestyleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleFreestyleException(FreestyleException e) {
        log.error("FreestyleException：{}", e.getMessage());
        return ResponseResult.errorMsg(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error("BindException：{}", message.toString());
        return ResponseResult.errorMsg(message.toString());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        log.error("ConstraintViolationException：{}", message.toString());
        return ResponseResult.errorMsg(message.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder message = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                message.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            message.append(fieldError.getField()).append(" :").append(fieldError.getDefaultMessage());
        }
        log.error("MethodArgumentNotValidException：{}", message.toString());
        return ResponseResult.errorMsg(message.toString());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String message = "请求参数错误：{" + e.getParameterType() + ":" + e.getParameterName() + "}";
        log.error("MissingServletRequestParameterException：{}", message);
        return ResponseResult.errorMsg(message);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult handleAuthenticationException(AuthenticationException e) {
        log.error("AuthenticationException：{}", e.getMessage());
        return ResponseResult.errorMsg(e.getMessage());
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseResult handleUnauthenticatedException(UnauthenticatedException e) {
        log.error("UnauthenticatedException：{}", "授权异常，请先登录");
        if (StringUtils.isNotEmpty(e.getMessage())) {
            return ResponseResult.errorMsg(e.getMessage());
        }
        return ResponseResult.errorMsg("操作异常，请先登录");
    }
}
