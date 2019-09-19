package xyz.drafter.architecture.gupao.rpc.rmi.client;

import xyz.drafter.architecture.gupao.rpc.rmi.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class ClientDemo {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
 /*       IHelloService helloService = null;
        try {
            helloService = new IHelloServiceImpl();

            System.out.println(helloService.sayHello("mic"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
*/

    // 从注册中心拿到代理类
        IHelloService helloService = (IHelloService) Naming.lookup("rmi://127.0.0.1/Hello");
        System.out.println(helloService.sayHello("mic"));

    }
}
