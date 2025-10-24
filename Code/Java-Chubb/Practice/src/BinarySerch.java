
public class BinarySerch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = {2,6,7,17,29,31,67,89,101};
		int target = 8;
		int result = binarySearch(data, target);
        System.out.println("Array elements:");
        printArray(data);
        System.out.println("Target element: " + target);
		if (result != -1) {
			System.out.println("Element found at index: " + result);
		} else {
			System.out.println("Element not found in the array.");
		}
		
	}
	
	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	

	public static int binarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == target) {
				return mid; 
			} else if (arr[mid] < target) {
				left = mid + 1; 
			} else {
				right = mid - 1; 
			}
		}
		return -1; 
	}

}
