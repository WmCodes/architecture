package xyz.drafter.architecture.gupao.pattern.proxy.jdk;

import xyz.drafter.architecture.gupao.pattern.proxy.staticed.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class JDKMeipo implements InvocationHandler {

    // 被代理对象
    private Person target;

    public Object getInstance(Person target) throws Exception{
        this.target = target;
        Class<?> clazz = target.getClass();

        // 用来生成一个新的对象(字节码重组)
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this::invoke);

    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("媒婆寻找对象");
        method.invoke(this.target, args);
        return null;
    }
}
