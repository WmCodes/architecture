package xyz.drafter.architecture.gupao.rpc.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public interface IHelloService extends Remote {

    String sayHello(String msg) throws RemoteException;
}
