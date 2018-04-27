package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

class Sender implements Runnable {
	Socket socket;
	OutputStream out = null;
	OutputStreamWriter outw = null;
	
	public Sender(Socket socket) throws IOException {
		this.socket = socket;
		out = socket.getOutputStream();
		outw = new OutputStreamWriter(out);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);						
			outw.write("안녕");
		} catch (Exception e) {

		} finally {
			// thread안에서는 Exception을 throw로 던질 수 없기 때문에
			// try-catch로 처리해야한다.
			if (outw != null)
				try {
					outw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}