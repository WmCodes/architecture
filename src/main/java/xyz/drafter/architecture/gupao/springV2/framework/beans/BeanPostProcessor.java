package xyz.drafter.architecture.gupao.springV2.framework.beans;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption 用于事件监听的
 */
public class BeanPostProcessor {


    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)  {
        return bean;
    }
}
