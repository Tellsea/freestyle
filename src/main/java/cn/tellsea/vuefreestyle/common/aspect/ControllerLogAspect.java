package cn.tellsea.vuefreestyle.common.aspect;

import cn.tellsea.vuefreestyle.common.annotation.ControllerLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 控制器日志 切面
 *
 * @author: Tellsea
 * @date : 2020/3/3
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    @Pointcut("execution(public * cn.tellsea.vuefreestyle.*.controller..*(..))")
    public void controllerLog() {
    }

    @Around(value = "controllerLog()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
//        UserInfoBO userInfo = WebSession.getUserInfo(request, response);
//        if(userInfo == null) {
//            return new ResponseResult().setSuccess(false).setMessage(ResultCode.RECORD_LOGIN_EXPIRE.getMessage()).setCode(ResultCode.RECORD_LOGIN_EXPIRE.getCOde());
//        }
        // 1、记录执行时间
        long startTime = System.currentTimeMillis();
        System.out.println(joinPoint.proceed(joinPoint.getArgs()));
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        // 2、有无日志监控注解，有则输出
        String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        String value = targetMethod.getName();
        if (targetMethod.isAnnotationPresent(ControllerLog.class)) {
            log.info("**********Method: {}, Start: {}, End: {}, Total: {}ms**********", methodName, startTime, endTime, totalTime);
        }
        // 3、入系统日志表
//        SystemLogDO systemLogDO = new SystemLogDO();
//        if(joinPoint.getArgs().length > 0){
//            systemLogDO.setPara(JsonToBeanUtil.beanToJSON(joinPoint.getArgs()[0]));
//        }
//        systemLogDO.setClientIp(IpUtil.getClientIp(request));
//        systemLogDO.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
//        systemLogDO.setOperator(userInfo.getUserName());
//        systemLogDO.setReqUri(request.getRequestURI());
//        systemLogDO.setReturnData(JsonToBeanUtil.beanToJSON(result));
//        systemLogDO.setStartTime(String.valueOf(startTime));
//        systemLogDO.setEndTime(String.valueOf(endTime));
//        systemLogDO.setTotalTime(String.valueOf(totalTime));
//        systemLogDO.setGmtCreateUser(userInfo.getUserName());
//        systemLogDO.setGmtModifiedUser(userInfo.getUserName());
//        systemLogDO.setIsDelete(0);
//        systemLogDAO.insert(systemLogDO);
//        id	自增ID	int	11	是	是
//        client_ip	客户端ip	varchar	100	否	否
//        req_uri	请求映射路径	varchar	100	否	否
//        method	方法名	varchar	200	否	否
//        param	参数	text	0	否	否
//        operator	操作人姓名	varchar	100	否	否
//        start_time	接口请求时间	varchar	50	否	否
//        end_time	接口返回时间	varchar	50	否	否
//        total_time	总消耗时间	varchar	50	否	否
//        return_data	接口返回数据	text	0	否	否
//        gmt_create	创建时间	datatime	0	是	否
//        gmt_create_user	创建人	varchar	50	是	否
//        gmt_modified	修改时间	datatime	0	是	否
//        gmt_modified_user	修改人	varchar	50	是	否
//        is_delete	是否删除（0：否 1：是）	tinyint	2	是	否
//        return result;
    }
}
