package T2;


class T1 extends Thread{
	int result = 1;
	boolean flag = true;

	public int getResult() {
		return result;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (flag) {
			result *= 2;
			
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		T1 t1 = new T1();
		t1.start();

		int result = 0;
		while(result <= 100) {
			Thread.sleep(300);
			result =t1.getResult();
			if(result > 100) {
				t1.setFlag(false);
				break;				
			}
			System.out.println(result);
		}
		
		t1.setFlag(false);
	}
}
