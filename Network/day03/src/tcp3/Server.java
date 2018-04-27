package tcp3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	class ReceiverAndSender implements Runnable {
		Socket socket;
		OutputStream out = null;
		OutputStreamWriter outw = null;
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		public ReceiverAndSender() {
		}

		public ReceiverAndSender(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			isr = new InputStreamReader(in);
			br = new BufferedReader(isr);
			out = socket.getOutputStream();
			outw = new OutputStreamWriter(out);
		}

		@Override
		public void run() {
			try {				
				String msg = br.readLine();
				System.out.println(socket.getInetAddress() + " :: " + msg);
				outw.write("¾È³ç1\n");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (outw != null)
						outw.close();
					if (socket != null)
						socket.close();
					if (br != null)
						br.close();
					if (outw != null)
						outw.close();
					if(isr != null)
						isr.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	int port;
	ServerSocket serverSocket;
	boolean flag;

	public Server() throws IOException {
		port = 7777;
		flag = true;
		serverSocket = new ServerSocket(port);
	}

	public void startServer() {
		while (flag) {
			try {
				System.out.println("Ready server. . .");
				Socket socket = serverSocket.accept();
				ReceiverAndSender rns = new ReceiverAndSender(socket);
				new Thread(rns).start();
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
