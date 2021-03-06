package cc.momas.javase.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 这是一个UDP协议app演示[测试版本]
 *
 * @author sod
 */
public class MyUdp {

    // 定义一些常量
    private final int MAX_LENGTH = 1024; // 最大接收字节长度
    private final int PORT_NUM = 2049;   // port号
    // 用以存放接收数据的字节数组
    private byte[] receMsgs = new byte[MAX_LENGTH];
    // 数据报套接字
    private DatagramSocket recever;
    // 用以接收数据报
    private DatagramPacket sender;

    public static void main(String[] args) {
        new MyUdp().init();
    }

    private void init() {
        try {
            /******* 接收数据流程**/
            // 创建一个数据报套接字，并将其绑定到指定port上
            recever = new DatagramSocket(PORT_NUM);
            // DatagramPacket(byte buf[], int length),建立一个字节数组来接收UDP包
            sender = new DatagramPacket(receMsgs, receMsgs.length);
            // receive()来等待接收UDP数据报
            recever.receive(sender);

            /****** 解析数据报****/
            String receStr = new String(sender.getData(), 0, sender.getLength());
            System.out.println("Server Rece:" + receStr);
            System.out.println("Server Port:" + sender.getPort());

            /***** 返回ACK消息数据报*/
            // 组装数据报
            byte[] buf = "I receive the message".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, sender.getAddress(), sender.getPort());
            // 发送消息
            recever.send(sendPacket);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (recever != null) {
                recever.close();
            }
        }

    }
}
