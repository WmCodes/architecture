package xyz.drafter.architecture.gupao.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class RpcClientProxy {

    public <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port){

        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                new Class[]{interfaceCls}, new RemoteInvocationHandler(host,port));

    }
}
