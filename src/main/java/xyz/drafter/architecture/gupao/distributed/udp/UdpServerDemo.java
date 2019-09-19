package xyz.drafter.architecture.gupao.distributed.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author wangmeng
 * @date 2019/9/18
 * @desciption
 */
public class UdpServerDemo {

    public static void main(String[] args) {

        try {


            DatagramSocket datagramSocket = new DatagramSocket(8080);
            byte[] receiveDate = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveDate, receiveDate.length);

            datagramSocket.receive(receivePacket);
            System.out.println(new String(receiveDate,0,receivePacket.getLength()));
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }
}
