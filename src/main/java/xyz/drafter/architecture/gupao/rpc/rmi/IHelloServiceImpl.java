package xyz.drafter.architecture.gupao.rpc.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class IHelloServiceImpl extends UnicastRemoteObject implements IHelloService {


    protected IHelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException{
        return "Hello"+msg;
    }
}
