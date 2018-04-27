package tcp4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	String ip;
	int port;	
	Socket socket;
	boolean flag = true;
	InputStream in = null;
	DataInputStream din = null;
	OutputStream out = null;
	DataOutputStream dout = null;

	public Client() {
		this.ip = "70.12.114.150";
		this.port = 7777;				
	}
	
	public void startClient() throws IOException {
		Scanner scanner = new Scanner(System.in);
		
		socket = new Socket(ip, port);
		in = socket.getInputStream();
		din = new DataInputStream(in);
		out = socket.getOutputStream();
		dout = new DataOutputStream(out);
		
		new Receiver().start();
		
		while(flag) {
			System.out.println("Input Client . . . ");
			String str = scanner.nextLine();
			
			if(str.equals("q")) {
				scanner.close();
				break;
			}

			Thread t = new Thread(new Sender(str));
			t.start();
		}
		
		System.out.println("Stop Client . . . ");
	}
	
	class Receiver extends Thread {
		@Override
		public void run() {
			while (flag) {
				try {
					System.out.println(din.readUTF());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class Sender implements Runnable {
		String msg;

		public Sender(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			try {
				dout.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.startClient();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
