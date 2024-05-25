package com.payc.tool.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * controller request和response日志打印
 *
 * @author dyy
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
    //申明一个切点 里面是 execution表达式
    //申明一个切点 里面是 execution表达式
//    第一个 * 表示 返回值,*代表所有
//    com.demo.* 包路径,.*表示路径下的所有包
//    第三个.* 表示路径下,所有包下的所有类的方法
//            (..) 表示不限方法参数
    @Pointcut("(execution( * com.payc.tool.controller..*.*(..)))")
    private void controllerAspect() {
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        try {
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();

            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String queryString = request.getQueryString();
            Object[] args = joinPoint.getArgs();
            String params = "";        //获取请求参数集合并进行遍历拼接
            /*if (StringUtils.isNotEmpty(queryString)){
                log.info("queryString===参数:" + queryString);
            }*/
            if (args.length > 0) {
                if ("POST".equals(method)) {
                    Object object = args[args.length - 1];
                    Map map = getKeyAndValue(object);
                    params = JSON.toJSONString(map);
                } else if ("GET".equals(method)) {
                    params = queryString;
                }
            }
            log.info("请求信息===\nip:{}\nurl:{}\nmethod:{}\ndata:{}", ip, url, method, params);
            // result的值就是被拦截方法的返回值
        } catch (Exception e) {
            log.error("参数日志打印错误[{}]：", e.getMessage());
        }
    }

    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        log.info("请求返回===\nResponse内容:" + JSON.toJSONString(o));
    }

    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();        // 得到类对象
        Class userCla = obj.getClass();        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            Object val;
            try {
                val = f.get(obj);                // 得到此属性的值
                map.put(f.getName(), val);// 设置键值
            } catch (Exception e) {
                log.error("", e);
            }
        }
        return map;
    }
}
