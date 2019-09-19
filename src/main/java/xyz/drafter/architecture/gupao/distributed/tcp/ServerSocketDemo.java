package xyz.drafter.architecture.gupao.distributed.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangmeng
 * @date 2019/9/18
 * @desciption
 */
public class ServerSocketDemo {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;

        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println(bufferedReader.readLine());

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if (bufferedReader != null){
                bufferedReader.close();
            }

            if (serverSocket != null){
                serverSocket.close();
            }
        }

    }
}
