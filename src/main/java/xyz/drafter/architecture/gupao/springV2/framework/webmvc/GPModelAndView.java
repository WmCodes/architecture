package xyz.drafter.architecture.gupao.springV2.framework.webmvc;

import java.util.Map;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption
 */
public class GPModelAndView {


    private String viewName;
    private Map<String,?> model;

    public GPModelAndView(String viewName, Map<String,?> model){
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
