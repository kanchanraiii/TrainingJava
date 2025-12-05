package performance;
public class Main {
	public static void main(String[]args) {
		Worker w= new Worker();
		Thread t1 =new Thread(w);
		t1.start();
		
		System.out.print("done");
		
	}
}