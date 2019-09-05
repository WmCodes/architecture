package xyz.drafter.architecture.gupao.springV2.framework.context.support;

import xyz.drafter.architecture.gupao.springV2.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption 配置文件进行查找，读取，解析
 */
public class BeanDefinitionReader {


    private Properties config = new Properties();

    private List<String> registyBeanClasses = new ArrayList<>();

    // 包扫描路径
    private final String SCAN_PACKAGE = "scanPachage";

    public BeanDefinitionReader(String ... locations){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if ( null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty("SCAN_PACKAGE"));
    }


    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/"+packageName.replace("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file:classDir.listFiles()){
            if (file.isDirectory()){
                doScanner(packageName+"."+file.getName());
            }else {
                registyBeanClasses.add(packageName+"."+file.getName().replace(".class", ""));
            }
        }

    }

    public List<String> loadBeanDefinitions(){


        return this.registyBeanClasses;
    }


    // 每注册一个className  就返回一个 BeanDefinition
    public BeanDefinition registerBean(String className){
        if (this.registyBeanClasses.contains(className)){
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            // bean 在IOC的名字
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".")+1)));
            return beanDefinition;
        }

        return null;
    }

    public Properties getConfig(){
        return this.config;
    }

    private String lowerFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
