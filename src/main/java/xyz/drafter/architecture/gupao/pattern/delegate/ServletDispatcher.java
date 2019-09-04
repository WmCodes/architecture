package xyz.drafter.architecture.gupao.pattern.delegate;

import xyz.drafter.architecture.gupao.pattern.delegate.controller.MemberAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmeng
 * @date 2019/8/26
 * @desciption
 */
public class ServletDispatcher {

    private List<Handler> handlerMapping = new ArrayList<>();
    public ServletDispatcher(){
        Class<?> memberActionClass = MemberAction.class;



        Handler handler = new Handler();
        try {
            handler.setController(memberActionClass.newInstance());
            handler.setMethod(memberActionClass.getMethod("getMemberById", new Class[]{String.class} ));
            handler.setUrl("/web/getMemberById.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        handlerMapping.add(handler);
    }


    public void doService(HttpServletRequest request, HttpServletResponse response){
        doDispatch(request, response);
    }

    public void doDispatch(HttpServletRequest request, HttpServletResponse response){

        // 获取用户请求的url

        //Servlet 拿到url，做权衡，通过url找到对应的java类的方法
        String uri = request.getRequestURI();

        //
        Handler handler = null;

        for (Handler h:handlerMapping){
            if (h.getUrl().equals(uri)){
                    handler = h;
                    break;
            }
        }

        // 通过反射去调用其对应的方法
        Object object = null;
        try {
            object =  handler.getMethod().invoke(handler.getController(),request.getParameter("mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 获取到Method执行的结果，通过Response返回出去

       // response.getWriter().write();



    }

  class Handler{
      private Object controller;
      private Method method;
      private String url;


      public Object getController() {
          return controller;
      }

      public void setController(Object controller) {
          this.controller = controller;
      }

      public Method getMethod() {
          return method;
      }

      public void setMethod(Method method) {
          this.method = method;
      }

      public String getUrl() {
          return url;
      }

      public void setUrl(String url) {
          this.url = url;
      }
  }
}
