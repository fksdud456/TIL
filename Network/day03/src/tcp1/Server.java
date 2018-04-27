package tcp1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	int port;
	ServerSocket serverSocket;
	Socket socket;
	// client 가 접속하면 client로 뭔가 줘야하는 데 outputstream 으로
	OutputStream out;
	// 한글때문에 쓰는것 2Byte 체제로 ,
	// client 가 c언어이거나 할 땐 한글쓰는 것보단 숫자 영어 체제로
	OutputStreamWriter outw;
	boolean flag;

	public Server() throws IOException {
		port = 7777;
		flag = true;
		// port 가 중복될 때, 누군가 사용중일 때
		// 여기서 try-catch를 하면 main() 함수에서는 error상황을 알 수 없음
		// 그래서 함수 자체에서 throws 해줌
		serverSocket = new ServerSocket(port);
	}

	public void startServer() throws IOException {
		System.out.println("Start Server. . . .");
		System.out.println("Ready Server. . . .");
		while (flag) {
			try {
				socket = serverSocket.accept();
				System.out.println("Accepted Client. . . ." + socket.getInetAddress());
				out = socket.getOutputStream();
				outw = new OutputStreamWriter(out);

				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				outw.write("안녕");
			} catch (IOException e) {
				throw e;
			} finally {
				if (outw != null)
					outw.close();
				if (socket != null)
					socket.close();
			}
		}
		System.out.println("End Server. . . .");
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public static void main(String[] args) {

		Server server = null;
		try {
			server = new Server();
			server.startServer();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
