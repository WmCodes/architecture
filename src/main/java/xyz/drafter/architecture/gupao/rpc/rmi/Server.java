package xyz.drafter.architecture.gupao.rpc.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class Server {


    public static void main(String[] args) {

        try {
            IHelloService helloService = new IHelloServiceImpl();

            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/Hello", helloService);
            System.out.println("服务启动成功");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
