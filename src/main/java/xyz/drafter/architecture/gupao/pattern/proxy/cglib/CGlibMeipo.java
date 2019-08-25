package xyz.drafter.architecture.gupao.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class CGlibMeipo implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception{

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 业务增强
        System.out.println("cglib代理");
        methodProxy.invokeSuper(o,objects);

        return null;
    }
}
