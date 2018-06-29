package merge_sort;

import java.io.FileInputStream;
import java.util.Scanner;

public class MergerSort {
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/merge_sort/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		int arr[], N;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			System.out.print("Before Array : ");
			printArray(arr, N);
			
			mergeSort(arr, 0, N-1);
			
			
			System.out.print("After Array  : ");
			printArray(arr, N);
		}
		
		System.out.println();
		sc.close();
	}

	private static void mergeSort(int[] arr, int start, int end) {
		
		if (end > start) {
			int mid = (start+end)/2;
			//divide
			mergeSort(arr, start, mid);
			mergeSort(arr, mid+1, end);
			
			//merge
			mergeArray(arr, start, mid, end);
		}
	}

	private static void mergeArray(int[] arr, int start, int mid, int end) {
		
		int l1 = mid-start+1;
		int l2 = end-mid;
		int left[] = new int[l1];
		int right[] = new int[l2];
		
		//copy left array
		for (int i = 0; i < l1; i++) {
			left[i] = arr[i+start];
		}
		
		//copy right array
		for (int i = 0; i < l2; i++) {
			right[i] = arr[i+mid+1];
		}
		int i = 0, j = 0, k = start;
		
		//merge left and right array in main array
		while (i < l1 && j < l2) {
			if (left[i] <= right[j]) {
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}
		
		while (i < l1) {
			arr[k++] = left[i++];
		}
		
		while (j < l2) {
			arr[k++] = right[j++];
		}
		
	}

	private static void printArray(int[] arr, int N) {
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
