package xyz.drafter.architecture.gupao.distributed.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author wangmeng
 * @date 2019/9/18
 * @desciption
 */
public class UdpClientDemo {

    public static void main(String[] args) {

        try {
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] sendData = "Hello,mic".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,address,8080);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(sendPacket);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }
}
