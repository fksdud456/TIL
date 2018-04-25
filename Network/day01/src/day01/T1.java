package day01;

class Thread1 extends Thread {
	String msg;
	boolean flag = true;

	public Thread1(String msg) {
		this.msg = msg;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		int i = 0;
		while (flag) {
			System.out.println(msg + i++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Thread2 implements Runnable {
	String msg;
	boolean flag = true;

	public Thread2(String msg) {
		this.msg = msg;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag; 
	}
	
	@Override
	public void run() {
		int i = 0;
		while (flag) {
			System.out.println(msg + i++);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

public class T1 {

	public static void main(String[] args) throws InterruptedException{
		// 무조건 쓰레드를 생성할 때 Thread1 이라는 이름으로 생성해야돼.
		Thread1 t1 = new Thread1("T1 :: ");
		// Thread라는 변수로 선언하고 Runnable 객체를 이용해 생성하기 때문에
		// 다양하게 사용할 수 있다.
		Thread2 r = new Thread2("T2 :: ");
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
		
		Thread.sleep(5000);
		t1.setFlag(false);
		r.setFlag(false);		
	}
}
