package com.haojie.manage.controller;

import com.haojie.manage.domain.SysLog;
import com.haojie.manage.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime; //访问开始时间
    private Class clazz;  //访问的是哪个类
    private Method method; //访问的是哪个方法

    @Autowired
    private ISysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;
    /**
     * 前置通知  获取开始时间,访问的是哪个的类,方法,
     */
    @Before("execution(* com.haojie.manage.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date(); //得到访问时间
        clazz = joinPoint.getTarget().getClass();//具体要访问的类
        String name = joinPoint.getSignature().getName();//只能获取访问方法的名称
        Object[] args = joinPoint.getArgs();//获取参数
        if (args == null || args.length == 0) {
            //无参方法直接获取
            method = clazz.getMethod(name);//只能获取无参方法
        } else {
            //有参获取具体执行方法
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method=clazz.getMethod(name, classArgs);
        }
    }


    /**
     * 后置通知
     */
    @After("execution(* com.haojie.manage.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) {
        long time = new Date().getTime() - visitTime.getTime();//获取访问时长
        //获取url
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取@requestMapping("/orders')的值
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                //class上的@requestMapping("/orders')的url
                String[] classValue = clazzAnnotation.value();
                //method上的@requestMapping("/findAll.do')的url
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }
        //获取访问IP地址
        String ip = request.getRemoteAddr();
        System.out.println(ip);

        //获取当前操作用户
        //从上下文中获取当前登录用户
        SecurityContext context= SecurityContextHolder.getContext();
        //此User为security自带的
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();


        //将日志相关信息封装到Syslog对象里面
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setIp(ip);
        //防止空指针异常
        try{
            sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
        }catch (Exception e){
            sysLog.setMethod("[类名] null"+"[方法名] null");
        }
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(visitTime);

        //调用service完成数据库持久化
        sysLogService.save(sysLog);
    }
}
