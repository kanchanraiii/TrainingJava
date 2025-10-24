
public class PrimeNos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int limit = 500;
        System.out.println("Prime numbers up to " + limit + ":");

        
        for (int number = 2; number <= limit; number++) {
            if (isPrime(number)) {
                System.out.print(number + " ");
            }
        }
    }

	public static boolean isPrime(int num) {
       
        if (num <= 1) {
            return false;
        }
        
       
        for (int i = 2; i <= Math.sqrt(num); i++) {
           
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }

}
