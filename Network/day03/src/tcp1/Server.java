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
	// client �� �����ϸ� client�� ���� ����ϴ� �� outputstream ����
	OutputStream out;
	// �ѱ۶����� ���°� 2Byte ü���� ,
	// client �� c����̰ų� �� �� �ѱ۾��� �ͺ��� ���� ���� ü����
	OutputStreamWriter outw;
	boolean flag;

	public Server() throws IOException {
		port = 7777;
		flag = true;
		// port �� �ߺ��� ��, ������ ������� ��
		// ���⼭ try-catch�� �ϸ� main() �Լ������� error��Ȳ�� �� �� ����
		// �׷��� �Լ� ��ü���� throws ����
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

				outw.write("�ȳ�");
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
