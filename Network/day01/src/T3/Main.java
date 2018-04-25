package T3;

import java.util.Scanner;

class Receiver implements Runnable {
	String cmd;

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("while :: ");
			if (cmd != null && cmd.equals("s")) {
				for (int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			} else if(cmd!= null && cmd.equals("e")) {
				break;
			}
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Receiver r = new Receiver();
		Thread t = new Thread(r);
		t.start();
		
		Scanner sc = new Scanner(System.in);
		String cmd = sc.nextLine();
		r.setCmd(cmd);

		String cmd2 = sc.nextLine();
		r.setCmd(cmd2.trim());		
		
		System.out.println("Main .. .. ");
	}

}
