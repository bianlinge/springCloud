package dove.BIO.server;

import dove.HostInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO:同步阻塞IO
 */
@Slf4j
public class BIOServer {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ServerSocket serverSocket = new ServerSocket(HostInfo.PORT);
        log.info("服务端启动。。。。port:" + HostInfo.PORT);
        boolean flag = true;
        while (flag) {//服务端持续监听
            Socket client = serverSocket.accept();//接入客户端
            executorService.submit(new EchoClientHanlder(client));
        }
        executorService.shutdown();

    }

    private static class EchoClientHanlder implements Runnable {

        private Socket client;// 每一个客户端都需要启动一个任务(task)来执行。
        private Scanner scanner;
        private PrintStream out;
        private boolean flag = true;
        public EchoClientHanlder(Socket client) {
            this.client = client;
            try {
                this.scanner = new Scanner(this.client.getInputStream());
                this.scanner.useDelimiter("\n");
                this.out = new PrintStream(this.client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (this.flag) {
                if (this.scanner.hasNext()) {
                     String val = this.scanner.next().trim();
                    log.info("服务端接受处理："+val);
                    if ("byebye".equalsIgnoreCase(val)) {
                        flag = false;
                        this.out.println("byebyte......");
                    } else {
                        out.println("[ECHO]"+val);
                    }
                }
            }
            this.scanner.close();
            this.out.close();
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
