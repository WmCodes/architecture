package xyz.drafter.architecture.gupao.rpc.server;

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
        // 处理请求
    }
}
