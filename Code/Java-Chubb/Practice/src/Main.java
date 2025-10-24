
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int numberOfTerms = 8;
        
        System.out.print("Fibonacci Series: ");
        printFibonacciSeries(numberOfTerms);

	}
	
	public static void printFibonacciSeries(int count) {
        int n1 = 0, n2 = 1;

        
        for (int i = 0; i < count; i++) {
            System.out.print(n1 + " ");

            
            int sum = n1 + n2;
            n1 = n2;
            n2 = sum;
        }
    }

}
