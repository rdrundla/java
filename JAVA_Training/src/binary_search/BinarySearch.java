package binary_search;

import java.io.FileInputStream;
import java.util.Scanner;

public class BinarySearch {
	static int arr[], N;
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/binary_search/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			arr = new int[N];
			
			for (int i = 0; i< N; i++) {
				arr[i] = sc.nextInt();
				System.out.print(arr[i] + " ");
			}
			int f = sc.nextInt();
			for (int i = 1; i <= f; i++) {
				int n = sc.nextInt();
				int index = binarySearch(n, 0, N-1);
				System.out.print("\n" + n + " found at : " + index);
			}
		}
		sc.close();
	}
	private static int binarySearch(int n, int start, int end) {
		
		if (start < end) {
			
			int mid = (end+start)/2;
			
			if (arr[mid] == n) {
				return mid;
			}
			
			if (arr[mid] > n) {
				return binarySearch(n, start, mid);
			} else {
				return binarySearch(n, mid+1, end);
			}
		}
		return -1;
		
	}
}
