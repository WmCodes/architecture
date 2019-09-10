package xyz.drafter.architecture.gupao.springV2.framework.context;

import xyz.drafter.architecture.gupao.springV2.framework.annotation.Autowired;
import xyz.drafter.architecture.gupao.springV2.framework.annotation.Controller;
import xyz.drafter.architecture.gupao.springV2.framework.annotation.Service;
import xyz.drafter.architecture.gupao.springV2.framework.beans.BeanDefinition;
import xyz.drafter.architecture.gupao.springV2.framework.beans.BeanPostProcessor;
import xyz.drafter.architecture.gupao.springV2.framework.beans.BeanWrapper;
import xyz.drafter.architecture.gupao.springV2.framework.context.support.BeanDefinitionReader;
import xyz.drafter.architecture.gupao.springV2.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption classPathXmlApplication
 */
public class GPApplicationContext extends GPDefaultListableBeanFactory implements BeanFactory {

    private String[] configLocations;

    private BeanDefinitionReader reader;

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    // 用来保证注册式单例的容器
    private Map<String,Object> beanCacheMap = new HashMap<>();

    // 用来存储所有的被代理过的对象
    private Map<String,BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public GPApplicationContext(String ...locations){
        this.configLocations = locations;
        this.refresh();
    }

    public void refresh(){
        // 定位
        this.reader = new BeanDefinitionReader(configLocations);


        // 加载
        List<String> beanDefinitios =  reader.loadBeanDefinitions();

        // 注册
        doRegisty(beanDefinitios);

        // 依赖注入(lazy-init = false)
        // 自动调用getBean 方法
        doAutowrited();

    }



    private void doAutowrited() {

        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry : this.beanDefinitionMap.entrySet()){
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().getLazyInit()){
                // 不是延时加载
                getBean(beanName);
            }
        }


    }


    public void populateBean(String beanName,Object instance){
        Class clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(Controller.class)
                || clazz.isAnnotationPresent(Service.class))){
            // 不是对应的注解
            return;

        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (!field.isAnnotationPresent(Autowired.class)){
                continue;
            }

            Autowired autowired = field.getAnnotation(Autowired.class);

            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)){
                // 这个属性的类全名
                autowiredBeanName = field.getType().getName();
            }

            field.setAccessible(true);
            try {
                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    // 真正将BeanDefinition注册到beandefinitionMap中
    private void doRegisty(List<String> beanDefinitions) {

        try {


            for (String className : beanDefinitions) {

                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()){continue;}
                 BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> interface_i :interfaces){
                    // 如果多个实现类，只能覆盖
                    // spring没有那么智能，会报错
                    this.beanDefinitionMap.put(interface_i.getName(), beanDefinition);
                }

                // beanName 有三种情况:
                // 1.默认是类名首字母小写
                // 2.自定义名字
                // 3.接口注入


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    // 通过读取BeanDefinition中的信息，通过反射机制，创建一个实例并返回
    // Spring中，不会把最原始的对象放出去，会用一个BeanWrapper进行一次包装
    // 装饰器模式:
    // 1.保留原来的OOP关系
    // 2.进行扩展，增强
    @Override
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        String className = beanDefinition.getBeanClassName();

        try {

            // 生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();


            Object instance = instantionBean(beanDefinition);
            if (null ==  instance){
                return null;
            }
            // 在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

            BeanWrapper beanWrapper = new BeanWrapper(instance);
            beanWrapper.setPostProcessor(beanPostProcessor);
            this.beanWrapperMap.put(beanName, beanWrapper);

            // 在实例初始化以后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            // 依赖需要处理
           // populateBean(beanName,instance);




            return this.beanWrapperMap.get(beanName).getWrappedInstance();



        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    // 传一个BeanDefinition 返回一个实例Bean
    private Object instantionBean(BeanDefinition beanDefinition){
        String className = beanDefinition.getBeanClassName();
        Object instance = null;

        try {
            if (this.beanCacheMap.containsKey(className)){
                instance = this.beanCacheMap.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.beanCacheMap.put(className, instance);
            }

            return instance;


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String[] getBeanDefinitionNames(){
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }

}
