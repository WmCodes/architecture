package xyz.drafter.architecture.gupao.rpc.server;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class ServerDemo {

    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(hello,8888);
    }
}
