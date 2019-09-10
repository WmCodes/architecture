package xyz.drafter.architecture.gupao.springV2.framework.webmvc.servlet;

import xyz.drafter.architecture.gupao.springV2.framework.annotation.Controller;
import xyz.drafter.architecture.gupao.springV2.framework.annotation.RequestMapping;
import xyz.drafter.architecture.gupao.springV2.framework.annotation.RequestParam;
import xyz.drafter.architecture.gupao.springV2.framework.context.GPApplicationContext;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPHandlerAdapter;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPHandlerMapping;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPModelAndView;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption
 */
public class DispatcherServlet extends HttpServlet {

    private final String LOCATION = "contextConfigLocation";

    //private Map<String, GPHandlerMapping> handlerMapping = new HashMap<>();

    private List<GPHandlerMapping> handlerMappings = new ArrayList<>();

    private Map<GPHandlerMapping, GPHandlerAdapter> handlerAdapters = new HashMap<>();

    private List<GPViewResolver> viewResolvers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        GPApplicationContext context = new GPApplicationContext(config.getInitParameter(LOCATION));

        initStrategies(context);

    }

    private void initStrategies(GPApplicationContext context) {
        iniMultipartResolver(context);//文件上传解析

        initLocalResolver(context);//本地化加息

        initHandlerMapping(context);// 通过HandlerMapping  将请求映射到处理器

        initHandlerAdapters(context);// 通过HandlerAdapter 进行多类型的参数动态匹配


        initHandlerExceptionResolvers(context);// 解析执行过程中遇到的异常


        initRequestToViewNameTranslator(context);//解析请求到视图


        initViewResolvers(context);//通过viewResolver解析逻辑视图到具体视图实现


        initFlashMapManager(context); //flash 映射管理器

    }

    private void initFlashMapManager(GPApplicationContext context) {
    }


    private void initViewResolvers(GPApplicationContext context) {
        //在页面敲一个http://localhost/first.html
        // 解决页面名字和模板文件关联的问题
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GPViewResolver(template.getName(), template));
        }


    }

    private void initRequestToViewNameTranslator(GPApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(GPApplicationContext context) {
    }

    // 所有的方法参数动态配置
    private void initHandlerAdapters(GPApplicationContext context) {
        //在初始化阶段，将这些参数的名字或者类型按一定的顺序保存下来
        // 后面用反射调用的时候，传的形参是一个数组
        // 可以用过记录这些参数的位置index，挨个从数组中填值，这样的话，就和参数的顺序无关
        for (GPHandlerMapping handlerMapping : this.handlerMappings) {
            Map<String, Integer> paramMapping = new HashMap<>();
            // 每个方法的每个参数上都可能有多个注解
            // 处理命名参数
            Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof RequestParam) {
                        String paramName = ((RequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            //处理非命名参数
            // 只处理Request 和 Response
            Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class ||
                        type == HttpServletResponse.class) {
                    paramMapping.put(type.getName(), i);
                }
            }

            this.handlerAdapters.put(handlerMapping, new GPHandlerAdapter(paramMapping));

        }
    }

    // 将controller中配置的RequestMapping 和Method一一对应
    private void initHandlerMapping(GPApplicationContext context) {

        // 从容器中取到
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            Object controller = context.getBean(beanName);
            Class<?> clazz = controller.getClass();
            //
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            String baseUrl = "";

            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 扫描所有的public方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                // /+  正则表达式，表示多个/ 替换成一个/
                String regex = ("/" + baseUrl + requestMapping.value().replace("/+", "/"));
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new GPHandlerMapping(controller, method, pattern));
                System.out.println("Mapping " + regex + ", " + method);

            }


        }


    }

    private void initLocalResolver(GPApplicationContext context) {
    }

    private void iniMultipartResolver(GPApplicationContext context) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*       String url = req.getRequestURI();
       String contextPath = req.getContextPath();
       url = url.replace(contextPath, "").replace("/+", "/");
       GPHandlerMapping handler = handlerMapping.get(url);
        try {
            GPModelAndView MV =  (GPModelAndView)handler.getMethod().invoke(handler.getController(), null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/


        try {


            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details: " + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", ""));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        GPHandlerMapping handlerMapping = getHandler(req);
        if (handlerMapping == null){
            resp.getWriter().write("404 Not Found");
        }

        GPHandlerAdapter ha = getHandlerAdapter(handlerMapping);

        GPModelAndView mv = ha.handle(req, resp, handlerMapping);

        processDispatchResult(resp, mv);

    }

    private void processDispatchResult(HttpServletResponse resp, GPModelAndView mv) throws Exception {
        // 调用viewResolver 的resolveView
        if (null == mv){
            return;
        }

        if (this.viewResolvers.isEmpty()){
            return;
        }
        for (GPViewResolver viewResolver : viewResolvers){
            if (!mv.getViewName().equals(viewResolver.getViewName())){
                continue;
            }
            String out = viewResolver.viewResolver(mv);
            if (out != null){
                resp.getWriter().write(out);
                break;
            }
        }

    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handlerMapping) {

        if (this.handlerAdapters.isEmpty()){
            return null;
        }

        return this.handlerAdapters.get(handlerMapping);
    }

    private GPHandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()){
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        for (GPHandlerMapping handlerMapping: this.handlerMappings){
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()){
                continue;
            }
            return handlerMapping;
        }
        return null;
    }

}
