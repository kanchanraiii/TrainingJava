
public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {1002,110,23445,6789,9807,6455,75432};
		int n=arr.length;
		System.out.println("Array before sorting:");
		printArray(arr);
		bubbleSort(arr,n);
		System.out.println("Array after sorting:");
		printArray(arr);
	}
	
	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	
	public static void bubbleSort(int arr[],int len) {
		
		for(int i=0;i<len-1;i++) {
			for(int j=0;j<len-i-1;j++) {
				if(arr[j]>arr[j+1]) {
					
					int temp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}
			}
		}
	}

}
