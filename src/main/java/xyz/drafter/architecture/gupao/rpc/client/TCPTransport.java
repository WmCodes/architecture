package xyz.drafter.architecture.gupao.rpc.client;
import xyz.drafter.architecture.gupao.rpc.common.RpcRequest;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class TCPTransport {


    private String host;
    private int port;

    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket(){

        System.out.println("创建新连接");
        Socket socket;

        try {

            socket = new Socket(host,port);
            return socket;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("连接建立失败");
        }
    }


    public Object send(RpcRequest rpcRequest){
        Socket socket = null;

        try {
            socket = newSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Object result = inputStream.readObject();
            inputStream.close();
            objectOutputStream.close();
            return result;

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("发起远程调用异常");
        }finally {
            if (socket!= null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
