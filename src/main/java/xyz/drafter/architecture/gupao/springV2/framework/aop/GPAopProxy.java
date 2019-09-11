package xyz.drafter.architecture.gupao.springV2.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangmeng
 * @date 2019/9/10
 * @desciption
 */
public class GPAopProxy implements InvocationHandler {

    private GPAopConfig config;
    private Object target;

    public void setConfig(GPAopConfig config){
        this.config = config;
    }



    public Object getProxy(Object instance){
        this.target = instance;
        Class<?> clazz = instance.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 原生方法
        Method m = this.target.getClass().getMethod(method.getName(), method.getParameterTypes());

        if (config.contains(m)){
            GPAopConfig.GPAspect aspect = config.get(m);
            aspect.getPoints()[0].invoke(aspect.getAspect(), null);
        }

        Object obj = method.invoke(this.target, args);

        if (config.contains(m)){
            GPAopConfig.GPAspect aspect = config.get(m);
            aspect.getPoints()[1].invoke(aspect.getAspect(), null);
        }
        return obj;
    }
}
