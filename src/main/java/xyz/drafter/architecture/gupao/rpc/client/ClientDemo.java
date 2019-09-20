package xyz.drafter.architecture.gupao.rpc.client;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class ClientDemo {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();

        Hello hello = rpcClientProxy.clientProxy(Hello.class, "localhost", 8888);

        System.out.println(hello.sayHello("mic"));

    }
}
