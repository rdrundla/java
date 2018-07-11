package heap_sort;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	static int arr[], N;
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/heap_sort/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		System.out.print("Original data : ");
		printArray();
		
		System.out.print("Assending data : ");
		maxHeapSort();
		printArray();
		
		System.out.print("Desending data : ");
		minHeapSort();
		printArray();
		
		sc.close();
	}
	
	private static void maxHeapSort() {
		
		for (int i = N/2 -1; i >= 0; i--) {
			maxHeapify(N, i);
		}
		
		for (int i = N-1; i >= 0; i--) {
			
			swap(0, i);			//move largest to last
			maxHeapify(i, 0);
		}
	}
	
	private static void minHeapSort() {
		
		for (int i = N/2 -1; i >= 0; i--) {
			minHeapify(N, i);
		}
		
		for (int i = N-1; i >= 0; i--) {
			
			swap(0, i);			//move smallest to last
			minHeapify(i, 0);
		}
	}

	private static void minHeapify(int n, int i) {
		
		int smallest  = i;
		int left = 2 * i +1;
		int right = 2 * i +2;
		
		if (left < n && arr[left] < arr[smallest]) {
			smallest = left;
		}
		
		if (right < n && arr[right] < arr[smallest]) {
			smallest = right;
		}
		
		if (smallest != i) {
			swap(i, smallest);
			
			minHeapify(n, smallest);
		}
	}

	static void printArray()
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
	
	static void maxHeapify(int n, int i) {
		
		int largest = i;
		int left = 2*i + 1;			//left child index
		int right = 2*i + 2;		//right child index
		
		if (left < n && arr[left] > arr[largest]) {
			largest = left;		//left is larger than root
		}
		
		if (right < n && arr[right] > arr[largest]) {
			largest = right;		//right is larger than root
		}
		
		//swap if largest is not root
		if (largest != i) {
			swap(i, largest);
			
			maxHeapify(n, largest);
		}
	}

	static void swap(int i, int j) {
		
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
