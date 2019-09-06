package xyz.drafter.architecture.gupao.springV2.framework.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption
 */
public class GPHandlerMapping {

    private Object controller;
    private Method method;
    private Pattern pattern;

    public GPHandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

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

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
