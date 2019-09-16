package xyz.drafter.architecture.gupao.springV2.framework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @author wangmeng
 * @date 2019/9/11
 * @desciption
 */
public class GPProxyUtils {

    public static Object getTargetObject(Object proxy)throws  Exception{
        // 判断是否是代理对象
        if (!isAopProxy(proxy)){
            return proxy;
        }
        return getProxyTargetobject(proxy);
    }

    private static boolean isAopProxy(Object object){
        return Proxy.isProxyClass(object.getClass());
    }

    private static Object getProxyTargetobject(Object proxy) throws Exception{
        // h字段，代理后的属性值

        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        GPAopProxy aopProxy = (GPAopProxy)h.get(proxy);
        Field target = aopProxy.getClass().getDeclaredField("target");

        target.setAccessible(true);
        // 返回 aopProxy 对象中 属性名为 target的对象
        return target.get(aopProxy);

    }
}
