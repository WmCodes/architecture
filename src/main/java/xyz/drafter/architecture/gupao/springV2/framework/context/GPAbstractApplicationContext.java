package xyz.drafter.architecture.gupao.springV2.framework.context;

/**
 * @author wangmeng
 * @date 2019/9/10
 * @desciption
 */
public abstract class GPAbstractApplicationContext {

    protected void onRefresh(){

    }

    protected abstract void refreshBeanFactory();
}
