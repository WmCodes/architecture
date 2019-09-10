package xyz.drafter.architecture.gupao.springV2.framework.context;

import xyz.drafter.architecture.gupao.springV2.framework.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmeng
 * @date 2019/9/10
 * @desciption
 */
public class GPDefaultListableBeanFactory extends GPAbstractApplicationContext {

    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected void onRefresh() {
        super.onRefresh();
    }

    @Override
    protected void refreshBeanFactory() {

    }
}
