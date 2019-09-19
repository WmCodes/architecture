package xyz.drafter.architecture.gupao.rpc.server;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String msg) {
        return "Hello ,"+msg;
    }
}
