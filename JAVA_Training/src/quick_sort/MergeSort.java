package quick_sort;

import java.io.FileInputStream;
import java.util.Scanner;

public class MergeSort {
	
	static int arr[], N;
	public static void main(String[] args) throws Exception {
	
	System.setIn(new FileInputStream("src/quick_sort/input.txt"));
	Scanner sc = new Scanner(System.in);
	
	int T = sc.nextInt();
	for (int test_case = 1; test_case <= T; test_case++) {
		N = sc.nextInt();
		arr = new int[N];
		
		for (int i = 0; i< N; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.print("Data before sorting : \t");
		printArray(arr);
		
		quickSort(0, N-1);
		
		System.out.print("Data after sorting : \t");
		printArray(arr);
	}
	sc.close();
}
	private static void quickSort(int start, int end) {
		
		if (start < end) {
			
			int pi = partition(start, end);
			
			//recurrence ofor left and right sub-array
			quickSort(start, pi-1);
			quickSort(pi+1, end);
		}
	}
	private static int partition(int start, int end) {
		
		//choose last element as pivot
		int pivot = arr[end];
		
		// i is index of smaller element and j is for current element which is being compared to pivot
		int i = start-1;
		for (int j = start; j < end; j++) {
			
			//if current element is smaller or equal to pivot swap it with next element to last smaller(i+1)
			if (arr[j] <= pivot) {
				i++;
				swap(i, j);
			}
		}
		
		//swap pivot and next element to last smaller(i+1)
		swap(i+1, end);
		
		return i+1;
	}
	
	private static void swap(int i, int j) {
		
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static void printArray(int[] array) {
		
		int l = array.length;
		for (int i = 0; i < l; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
