package performance;

public class Worker implements Runnable{
	@Override
	public void run() {
		add(3,5);
	}	
	int add(int a,int b) {
		return a+b;
	}

}
