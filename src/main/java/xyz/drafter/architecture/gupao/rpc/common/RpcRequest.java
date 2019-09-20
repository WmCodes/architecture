package xyz.drafter.architecture.gupao.rpc.common;

import java.io.Serializable;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption 序列化的包名需要一致
 */
public class RpcRequest implements Serializable {


    private static final long serialVersionUID = -7393782181875591702L;

    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
