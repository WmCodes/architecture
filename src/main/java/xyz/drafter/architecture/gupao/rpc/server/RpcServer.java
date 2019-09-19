package xyz.drafter.architecture.gupao.rpc.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class RpcServer {


    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service,int port){
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port); // 启动一个服务监听
            while (true){
                Socket socket = serverSocket.accept();

                executorService.execute(new ProcessorHandler(socket,service));

            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
