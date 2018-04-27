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
		// port �� �ߺ��� ��, ������ ������� ��
		// ���⼭ try-catch�� �ϸ� main() �Լ������� error��Ȳ�� �� �� ����
		// �׷��� �Լ� ��ü���� throws ����
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
