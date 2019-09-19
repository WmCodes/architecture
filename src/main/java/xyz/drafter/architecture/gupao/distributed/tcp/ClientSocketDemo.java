package xyz.drafter.architecture.gupao.distributed.tcp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author wangmeng
 * @date 2019/9/18
 * @desciption
 */
public class ClientSocketDemo {


    public static void main(String[] args) throws IOException {
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1",8080);

            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("Hello");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (socket != null){
                socket.close();
            }
        }

    }
}
