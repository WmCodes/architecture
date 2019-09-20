package xyz.drafter.architecture.gupao.rpc.client;
import xyz.drafter.architecture.gupao.rpc.common.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class RemoteInvocationHandler implements InvocationHandler {


    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        TCPTransport tcpTransport = new TCPTransport(this.host,
                this.port);

        return tcpTransport.send(rpcRequest);

    }
}
