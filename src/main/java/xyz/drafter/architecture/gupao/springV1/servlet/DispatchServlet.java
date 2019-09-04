package xyz.drafter.architecture.gupao.springV1.servlet;

import xyz.drafter.architecture.gupao.springV1.annotion.Autowired;
import xyz.drafter.architecture.gupao.springV1.annotion.Controller;
import xyz.drafter.architecture.gupao.springV1.annotion.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangmeng
 * @date 2019/9/4
 * @desciption
 */
public class DispatchServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String,Object> beanMap = new ConcurrentHashMap();

    private List<String> classNames = new ArrayList<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("----- 调用dopost -----");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 开始初始化的进程

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
        doScanner(contextConfig.getProperty("scanPachage"));

        //注册
        doRegistry();


        //自动依赖注入
        //spring中调用getBean方法才触发依赖注入
        doAutoWired();

        //如果是SpringMVC 会多设计一个HandlerMapping
        // 将@RequestMapping中配置的url和一个Method关联上
        // 以便于从浏览器获得用户的url后，能够找到具体的method方法通过反射调用
        initHandlerMapping();
        

    }

    private void initHandlerMapping() {
    }

    private void doAutoWired() {
        if (beanMap.isEmpty()){
            return;
        }

        for (Map.Entry<String,Object> entry:beanMap.entrySet()){
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields){
                if (!field.isAnnotationPresent(Autowired.class)){continue;}

                Autowired autowired = field.getAnnotation(Autowired.class);

                String beanName = autowired.value().trim();
                if ("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    // 向 entry.getValue() 对象中的  field 属性设置新的值
                    field.set(entry.getValue(), beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void doRegistry() {
        if (classNames.isEmpty()){
            return;
        }

        try {
            for (String className:classNames){
                Class<?> clazz = Class.forName(className);

                // 在spring中用的多个子方法来处理的:parseArray parseMap
                if (clazz.isAnnotationPresent(Controller.class)){
                    // 如果加了Controller注解
                    String beanName = lowerFirstCase(clazz.getSimpleName());
                    // spring里 put 的是 BeanDefinition
                    beanMap.put(beanName,clazz.newInstance());

                }else if (clazz.isAnnotationPresent(Service.class)){

                    Service service = clazz.getAnnotation(Service.class);
                    // 默认用类名首字母注入
                    // 如果自己定义了beanName，那么优先使用自己定义的beanName
                    // 如果是一个接口，使用接口类型注入

                    //在spring中同样会分别调用不同的方法  autowriedByName autowriedByType
                    String beanName = service.value();
                    if ("".equals(beanName.trim())){
                        beanName = lowerFirstCase(clazz.getSimpleName());
                    }

                    Object instancec = clazz.newInstance();
                    beanMap.put(beanName, instancec);


                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i :interfaces){
                        beanMap.put(i.getName(), instancec);
                    }


                }else {
                    continue;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/"+packageName.replace("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file:classDir.listFiles()){
            if (file.isDirectory()){
                doScanner(packageName+"."+file.getName());
            }else {
                classNames.add(packageName+"."+file.getName().replace(".class", ""));
            }
        }

    }

    private void doLoadConfig(String location) {

        //spring中通过Reader去查找定位
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String lowerFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
