import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello NIO Server!"); // Gửi tin nhắn
        socket.close();
    }
}