package day04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Server extends Thread {
	ServerSocket serversocket;
	boolean flag = true;
	boolean rflag = true;
	HashMap<String, DataOutputStream> hmap = new HashMap<>();

	public Server() throws IOException {
		// ServerSocket Create
		serversocket = new ServerSocket(8888);
	}

	@Override
	public void run() {
		// Accept Client Connection
		try {
			while (flag) {
				System.out.println("Ready Accept");
				Socket socket = serversocket.accept(); // 멈춰있다가 반응이 오면 작동
				Receiver receiver = new Receiver(socket); // socket을 receiver에게 전달
				receiver.start();
			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) {
		// Server for Multichatting [2018. 05. 01]
		Server server = null;
		try {
			server = new Server();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Inner Class Receiver & Sender
	class Receiver extends Thread {
		// 여러 클라이언트가 서버에 들어오면 가장 먼저 거치는 스레드
		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;
		Socket socket;
		String ip;

		public Receiver(Socket socket) {
			try {
				System.out.println("Connected Count : "+ hmap.size());

				this.socket = socket;
				in = socket.getInputStream();
				din = new DataInputStream(in);
				out = socket.getOutputStream();
				dout = new DataOutputStream(out);
				ip = socket.getInetAddress().toString();
				hmap.put(ip, dout);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end Receiver

		@Override
		public void run() {
			try {
				while (rflag) {
					if (socket.isConnected() && din != null && din.available() > 0) {
						String str = din.readUTF();
						if (str != null && str.equals("q")) {
							hmap.remove(ip);
							break;
						}
						System.out.println(str);
						sendAll("[" + ip + "] " + str);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(200);
					if (din != null) {
						din.close();
					}
					if (dout != null) {
						dout.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public void sendAll(String msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}

	// Send Message All clients
	class Sender extends Thread {
		String msg;

		public void setMsg(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			// 클라이언트에 보내주기
			try {
				Set<String> keys = hmap.keySet();
				Iterator<String> it = keys.iterator();
				if (hmap.size() > 0) {
					while (it.hasNext()) {
						String str = it.next();
						hmap.get(str).writeUTF(msg);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}