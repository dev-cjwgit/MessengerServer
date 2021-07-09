import Server.Netty.Server;

public class Main {
    public static void main(String[] args) {
        new Server().run();
        System.out.println("안녕하세요!");
    }
}