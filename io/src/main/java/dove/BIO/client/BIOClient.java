package dove.BIO.client;

import dove.HostInfo;
import dove.InputUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket(HostInfo.HOST_IP, HostInfo.PORT);//创建连接
        log.info("客户端连接服务器成功。。。");
        Scanner scanner = new Scanner(client.getInputStream());
        scanner.useDelimiter("\n");
        PrintStream out = new PrintStream(client.getOutputStream());
        boolean flag = true;
        while (flag) {
            String inputData = InputUtil.getString("请输入要发送的内容：").trim();
            out.println(inputData);//把数据发送到服务器端上
            //客户端显示键盘输入的值
            if (scanner.hasNext()) {
                String val = scanner.next().trim();
                log.info(val);
            }
            if ("byebye".equalsIgnoreCase(inputData)) {
                flag = false;
            }
        }
        client.close();
    }
}
