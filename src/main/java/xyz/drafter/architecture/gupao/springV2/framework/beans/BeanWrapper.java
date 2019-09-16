package xyz.drafter.architecture.gupao.springV2.framework.beans;

import xyz.drafter.architecture.gupao.springV2.framework.aop.GPAopConfig;
import xyz.drafter.architecture.gupao.springV2.framework.aop.GPAopProxy;
import xyz.drafter.architecture.gupao.springV2.framework.core.FactoryBean;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption
 */
public class BeanWrapper  extends FactoryBean {



    private GPAopProxy aopProxy = new GPAopProxy();

    // 还会用到 观察者 模式
    // 1.支持事件响应，会有一个箭筒

    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;
    // 原生的
    private Object originalInstance;

    public BeanWrapper(Object instance){
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originalInstance = instance;
    }

    public Object getWrappedInstance(){
        return this.wrapperInstance;
    }

    // 返回代理以后的Class
    // 可能会使这个 $Proxy0
    public Class<?> getWrappedClass(){

        return this.wrapperInstance.getClass();
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public void setAopConfig(GPAopConfig config){
        aopProxy.setConfig(config);
    }
}
