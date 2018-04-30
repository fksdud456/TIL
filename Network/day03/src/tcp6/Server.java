package tcp6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {

	ServerSocket serverSocket;
	ArrayList<DataOutputStream> list = new ArrayList<>();

	private int port = 9999;
	private boolean flag = true;

	public Server() throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server Ready . . .");
	}

	public void start() throws IOException {
		while (flag) {
			Socket socket = serverSocket.accept();
			System.out.println(serverSocket.getInetAddress() + " : " + socket.getInetAddress() + "Connected");
			new Receiver(socket).start();
		}
	}

	class Receiver extends Thread {
		InputStream in;
		DataInputStream din;

		OutputStream out;
		DataOutputStream dout;

		public Receiver(Socket socket) throws IOException {
			in = socket.getInputStream();
			din = new DataInputStream(in);

			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			list.add(dout);
			System.out.println("Client count : " + list.size());
		}

		@Override
		public void run() {

			while (din != null) {
				String str = "";
				try {
					str = din.readUTF();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (str != null && str.equals("q")) {
					break;
				}
				System.out.println(str);
				SendHttp http = new SendHttp(str);
				http.start();
			}
			list.remove(dout);
			try {
				if (dout != null) {
					dout.close();
				}
				if (din != null) {
					din.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class SendHttp extends Thread {
		String msg;
		String urls = "http://70.12.114.150/webserver2/main.do";

		public SendHttp(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			StringTokenizer st = new StringTokenizer(msg, ",");
			urls += "?speed=" + st.nextToken();
			urls += "&temp=" + st.nextToken();

			try {
				URL url = new URL(urls);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(5000);
				conn.getInputStream();
				System.out.println(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String args[]) {
		Server server = null;
		try {
			server = new Server();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
