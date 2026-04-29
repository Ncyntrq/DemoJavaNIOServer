
# DemoJavaNIOServer

## Hướng dẫn vận hành

Để kiểm tra hoạt động của hệ thống, thực hiện theo các bước sau:

### Bước 1: Khởi động Server
Chạy file `NIOServer.java`. Lúc này Server sẽ ở trạng thái chờ (Listening) các kết nối từ Client.

### Bước 2: Kết nối Client
Mỗi khi chạy file `SimpleClient.java`, một kết nối mới sẽ được thiết lập tới Server.

### Bước 3: Kiểm tra kết quả
Tại cửa sổ Console của `NIOServer`, mỗi lần một `SimpleClient` được thực thi thành công, Server sẽ hiển thị thông báo:
> **`New client connected!`**

---

## Luồng hoạt động (Workflow)
1. **Server Start**: `NIOServer` khởi tạo Selector và mở ServerSocketChannel.
2. **Accept Event**: Khi `SimpleClient` kết nối, Selector phát hiện sự kiện `OP_ACCEPT`.
3. **Logging**: Server chấp nhận kết nối và in dòng chữ đại diện cho mỗi phiên kết nối thành công.
