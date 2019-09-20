package xyz.drafter.architecture.gupao.rpc.server;

import xyz.drafter.architecture.gupao.rpc.common.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        // 处理请求
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());



           RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();

            Object result = invoke(rpcRequest);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private Object invoke(RpcRequest request) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] args = request.getParameters();
        Class<?>[] types = new Class[args.length];
        for (int i =0;i<args.length;i++){
            types[i] = args[i].getClass();
        }

        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service, args);
    }
}
