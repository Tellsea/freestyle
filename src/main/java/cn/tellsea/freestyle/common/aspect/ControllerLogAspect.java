package cn.tellsea.freestyle.common.aspect;

import cn.tellsea.freestyle.common.annotation.ControllerLog;
import cn.tellsea.freestyle.common.utils.AddressUtil;
import cn.tellsea.freestyle.common.utils.IpUtil;
import cn.tellsea.freestyle.system.entity.SystemLog;
import cn.tellsea.freestyle.system.service.SystemLogService;
import com.alibaba.fastjson.JSON;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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

    private final SystemLogService systemLogService;

    public ControllerLogAspect(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @Pointcut("execution(public * cn.tellsea.freestyle.*.controller..*(..))")
    public void controllerLog() {
    }

    @Around(value = "controllerLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 1、记录执行时间
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed(joinPoint.getArgs());
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        // 2、有无日志监控注解，有则输出
        String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        String value = targetMethod.getName();
        if (targetMethod.isAnnotationPresent(ControllerLog.class)) {
            // 3、入系统日志表
            SystemLog systemLog = new SystemLog();
            // todo 设置用户id
            systemLog.setUserId(0);
            systemLog.setOperation(value);
            systemLog.setTime((int) totalTime);
            systemLog.setMethod(methodName);
            systemLog.setParams(JSON.toJSONString(getFieldsName(joinPoint)));
            systemLog.setIp(IpUtil.getClientIp(request));
            systemLog.setLocation(AddressUtil.getAddress(systemLog.getIp()));
            String header = request.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            Browser browser = userAgent.getBrowser();
            OperatingSystem operatingSystem = userAgent.getOperatingSystem();
            systemLog.setDevice(browser + " -- " + operatingSystem);
            systemLogService.save(systemLog);
            System.out.println("异步");
        }
        return proceed;
    }

    /**
     * 获取参数列表
     *
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     */
    private static Map<String, Object> getFieldsName(ProceedingJoinPoint joinPoint) {
        // 参数值
        Object[] args = joinPoint.getArgs();
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}
