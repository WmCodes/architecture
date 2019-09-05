package xyz.drafter.architecture.gupao.springV2.framework.beans;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption 存储配置文件中的信息，保存在内容中
 */
public class BeanDefinition {

    private String beanClassName;
    private Boolean lazyInit = false;
    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public Boolean getLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(Boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }


    /*    public void setBeanClassName( String beanClassName){

    }

    public String getBeanClassName(){
        return null;
    }


    public void setFactoryBeanName( String factoryBeanName){

    }


    public String getFactoryBeanName(){
        return null;
    }

    public void setLazyInit(boolean lazyInit){

    }

    public boolean isLazyInit(){
        return false;
    }*/
}
