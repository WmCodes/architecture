package xyz.drafter.architecture.gupao.springV2.framework.webmvc.servlet;

import xyz.drafter.architecture.gupao.springV2.framework.context.GPApplicationContext;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPHandlerAdapter;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPHandlerMapping;
import xyz.drafter.architecture.gupao.springV2.framework.webmvc.GPModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption
 */
public class DispatcherServlet extends HttpServlet {

    private  final String LOCATION = "contextConfigLocation";

    //private Map<String, GPHandlerMapping> handlerMapping = new HashMap<>();

    private List<GPHandlerMapping> handlerMappings = new ArrayList<>();

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
    }

    private void initRequestToViewNameTranslator(GPApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(GPApplicationContext context) {
    }

    private void initHandlerAdapters(GPApplicationContext context) {
    }

    private void initHandlerMapping(GPApplicationContext context) {


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


        //doDispatch(req, resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        GPHandlerMapping handlerMapping = getHandler(req);

         GPHandlerAdapter ha  = getHandlerAdapter(handlerMapping);

         GPModelAndView mv = ha.handle(req,resp,handlerMapping);

         processDispatchResult(resp,mv);
    }

    private void processDispatchResult(HttpServletResponse resp, GPModelAndView mv) {
        // 调用viewResolver 的resolveView
    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handlerMapping) {
        return null;
    }

    private GPHandlerMapping getHandler(HttpServletRequest req) {
        return null;
    }

}
