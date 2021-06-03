package dove.Netty.client;

public class EchoClientMain {
    public static void main(String[] args) throws InterruptedException {
        EchoClient echoClient = new EchoClient();
        echoClient.run();
    }
}
