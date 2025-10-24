
public class LinearSearch {
		
	public static int linearSearch(int[] arr, int target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				return i; 
			}
		}
		return -1;
	}
	
	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] data = {2,6,7,17,29,31,67,89,101};
		int target = 8;
		int result = linearSearch(data, target);
		System.out.println("Array elements:");
		printArray(data);
		System.out.println("Target element: " + target);
		if (result != -1) {
			System.out.println("Element found at index: " + result);
		} else {
			System.out.println("Element not found in the array.");
		}
	}
 
}
