package com.thezp.annotation;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * Created by zhangpeng on 2017/11/30.
 */
@Slf4j
@Aspect
@Component
public class IntervalTimeInterceptor {

    @Around("@annotation(it)")
    public Object Interceptor(ProceedingJoinPoint pjp, IntervalTime it) {
        Object result = null;
        try {
            long beginTime = System.currentTimeMillis();
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Object target = pjp.getTarget();
            Method method = target.getClass()
                .getMethod(signature.getName(), signature.getParameterTypes());
            //获取被拦截的方法名
            String methodName = method.getName();

            String fieldKey = "#";
            if (StringUtils.isNotEmpty(it.mainProperty())) {
                fieldKey = parseKey(it.mainProperty(), method, pjp.getArgs());
            }

            try {
                result = pjp.proceed();
            } catch (Throwable e) {
                log.error("method: {} with mainProperty:{} exec error", methodName, fieldKey, e);
            }

            long endTime = System.currentTimeMillis();

            log.info("method:{} with mainProperty:{} exec totaltime: {} ms", methodName, fieldKey,
                (endTime - beginTime));
        } catch (Exception e) {
            log.error("intervalTime error.", e);
        }

        return result;
    }

    /**
     * 支持SPEL表达式
     * @return
     */
    private String parseKey(String key, Method method, Object[] args) {

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
            new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

}
