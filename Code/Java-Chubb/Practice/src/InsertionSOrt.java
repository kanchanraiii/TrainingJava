
public class InsertionSOrt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1002,110,23445,6789,9807,6455,75432};
		int n=arr.length;
		System.out.println("Array before sorting:");
		printArray(arr);
		insertionSort(arr,n);
		System.out.println("Array after sorting:");
		printArray(arr);
	}
	
	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	public static void insertionSort(int arr[],int len) {
		
		for(int i=1;i<len;i++) {
			int key=arr[i];
			int j=i-1;
			while(j>=0 && arr[j]>key) {
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=key;
		}
	}

}
