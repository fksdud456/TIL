package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	ServerSocket serverSocket;
	boolean flag;

	public Server() throws IOException {
		port = 7777;
		flag = true;
		// port 가 중복될 때, 누군가 사용중일 때
		// 여기서 try-catch를 하면 main() 함수에서는 error상황을 알 수 없음
		// 그래서 함수 자체에서 throws 해줌
		serverSocket = new ServerSocket(port);
	}

	// Accept Client Socket
	// & Create(socket) and Start Sender Thread
	public void startServer() {
		while (flag) {
			try {
				System.out.println("Ready server. . .");
				Socket socket = serverSocket.accept();
				Sender sender = new Sender(socket);
				new Thread(sender).start();
				System.out.println("Accepted client . . .");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
