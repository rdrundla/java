package kmp_search_string_matches;

import java.io.FileInputStream;
import java.util.Scanner;

public class KMP {
	
	static String text, pattern;
	static int lspArray[];
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/kmp_search_string_matches/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			text = sc.next();
			pattern = sc.next();
			System.out.println("Text : " + text + "\nPattern : " + pattern);
			lspArray = new int[pattern.length()];
			calculateLspArray();
			int noOfMatches = findPattern();
			System.out.println("No of matches = " + noOfMatches + "\n");
			//printArray(lspArray, lspArray.length);
		}
		
		System.out.println();
		sc.close();
	}
	private static int findPattern() {
		
		int textLength = text.length();
		int patternLength = pattern.length();
		int count = 0;
		int i = 0;
		int j = 0;
		
		while (i < textLength) {
			if (text.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
			}
			
			if (j == patternLength) {
				System.out.println("Pattern found at : " + (i-j+1));
				count ++;
				j = lspArray[j-1];
			} else if (i < textLength && text.charAt(i) != pattern.charAt(j)) {
				if (j != 0) {
					j = lspArray[j-1];
				} else {
					i++;
				}
			}
		}
		return count;
	}
	private static void printArray(int[] arr, int length) {
		System.out.print("Array : ");
		for (int  i = 0; i < length; i ++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
	}
	private static void calculateLspArray() {
		
		int len = pattern.length();
		int k = 0;
		lspArray[0] = 0;
		for (int i = 1; i < len; i++) {
			
			while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
				k = lspArray[k-1];
			}
			
			if (pattern.charAt(i) == pattern.charAt(k)) {
				k++;
			}
			
			lspArray[i] = k;
		}
	}

}
