package xyz.drafter.architecture.gupao.springV2.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wangmeng
 * @date 2019/9/5
 * @desciption
 */
public class GPHandlerAdapter {

    private Map<String,Integer> paramMapping;

    public GPHandlerAdapter(Map<String,Integer> paramMapping){
        this.paramMapping = paramMapping;
    }

    // 根据用户请求的参数信息，跟method中的参数信息进行动态匹配，resp 为了将其赋给方法参数
    public GPModelAndView handle(HttpServletRequest req, HttpServletResponse resp, GPHandlerMapping handlerMapping) throws Exception {
        //方法的形参列表
        // 方法的重载:参数的个数，参数的类型，参数的顺序，方法的名字
        Class<?>[] paramTypes = handlerMapping.getMethod().getParameterTypes();

        //拿到自定义命名参数所在的位置
        Map<String,String[]> reqParameterMap = req.getParameterMap();

        //构造实参列表
        Object[] paramValus = new Object[paramTypes.length];
        for (Map.Entry<String,String[]> param : reqParameterMap.entrySet()){
            String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s", ",");
            // paramMapping 是在确定了handlerMapping 唯一的方法后才确定的，指的是要访问的方法的参数，所以不会重复
            if (!this.paramMapping.containsKey(param.getKey())){continue;}
            int index = this.paramMapping.get(param.getKey());

            // 页面上传过来的值都是String类型 需要做转换
            paramValus[index] = caseStringValue(value, paramTypes[index]);
        }

        int reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
        paramValus[reqIndex] = req;

        int respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
        paramValus[respIndex] = resp;

        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramTypes);

        if (result == null){
            return null;
        }
        Boolean isModelAndView = handlerMapping.getMethod().getReturnType() == GPModelAndView.class;
        if (isModelAndView){
            return (GPModelAndView) result;
        }else {
            return null;
        }

    }

    private Object caseStringValue(String value,Class<?> clazz){
        if (clazz == String.class){
            return value;
        }else if (clazz == Integer.class){
            return Integer.valueOf(value);
        }else if (clazz == int.class){
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }
    }
}
