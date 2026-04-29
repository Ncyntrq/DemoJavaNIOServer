import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // Mở Selector - Bộ giám sát sự kiện
        Selector selector = Selector.open();

        // Mở ServerSocketChannel và cấu hình Non-blocking
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 8888));
        serverChannel.configureBlocking(false);

        // Đăng ký sự kiện Accept kết nối mới với Selector
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("NIO Server started on port 8888...");

        // Vòng lặp sự kiện (Event Loop)
        while (true) {
            selector.select(); // Chờ sự kiện sẵn sàng từ OS
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                if (key.isAcceptable()) {
                    // Chấp nhận kết nối mới (Acceptor trong Reactor Pattern)
                    SocketChannel client = serverChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("New client connected!");
                }
                else if (key.isReadable()) {
                    // Đọc dữ liệu dựa trên Buffer-oriented
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256); // Buffer quản lý dữ liệu

                    if (client.read(buffer) == -1) {
                        client.close();
                    } else {
                        buffer.flip(); // Chuyển từ Ghi sang Đọc (như Ch.3 mô tả)
                        client.write(buffer); // Echo dữ liệu lại cho Client
                        buffer.clear();
                    }
                }
            }
        }
    }
}