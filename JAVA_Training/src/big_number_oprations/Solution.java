package long_num_multiplication_karatsuba_agorithm;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	
	
	private static int EQUAL = 0;
	private static int BIGGER = 1;
	private static int SMALLER = 2;

	public static void main(String[] args) throws Exception {
		FileInputStream fileInputStream = 
				new FileInputStream("src/long_num_multiplication_karatsuba_agorithm/input.txt");
		Scanner sc = new Scanner(fileInputStream);
		int T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			String num1 = sc.next();
			String num2 = sc.next();
			//String multiplication = multiply(num1, num2);
			String s = divide(Integer.parseInt(num1), Integer.parseInt(num2), 0, 11);
			System.out.println(s);
		}
		
		System.out.println();
		sc.close();
	}
	
	private static String divide(int x, int y, int min, int max) {
		String res = "";
		
		int mid = (min+max)/2;
		
		if (mid == min) {
			return res + mid;
		}
		
		int multiplication = mid * y;
		
		
		int equity = checkEquity(x, multiplication);

		
		if (equity == EQUAL)
			return res + mid;
		
		if (equity == BIGGER) {
			res = divide(x, y, min, mid);
		} else {
			res = divide(x, y, mid, max);
		}
		return res;
	}

	private static int checkEquity(int a, int b){
		if (b > a)
			return BIGGER;
		if (b < a)
			return SMALLER;
		
		return EQUAL;
	}

	private static String multiply(String x, String y) {
		// TODO Auto-generated method stub
		int n = makeEqualLength(x, y);
		
		if(n == 0) {
			return null;
		}
		
		if (n == 1) {
			return multiplySingleDigits(x, y);
		}
		
		int mid = n/2;
		
		String xl = getSubString(0, mid-1, x);
		String xr = getSubString(mid, n-1, x);
		
		String yl = getSubString(0, mid-1, y);
		String yr = getSubString(mid, n-1, y);
		
		String P1 = multiply(xl, yl);
		String p2 = multiply(xr, yr);
		String P3 = multiply(addString(xl,xr), addString(yl, yr));
		
		
		return null;
	}

	private static String addString(String a, String b) {
		int l1 = a.length();
		int l2 = b.length();
		String digit = "";
		for (int i = l1-1, j = l2 -1, carry = 0; i>=0 || j>=0 || carry != 0; i--,j--) {
			int d1 = i < 0 ? 0 : Integer.parseInt(Character.toString(a.charAt(i)));
			int d2 = j < 0 ? 0 : Integer.parseInt(Character.toString(b.charAt(j)));
			
			int d = d1 + d2 + carry;
			digit = d + digit;
			if (d > 9) {
				carry = 1;
				d -= 10;
			} else {
				carry = 0;
			}
		}
		
		return digit;
	}
	
	private static String subtractString(String str1, String str2) {
		return null;
	}

	private static String getSubString(int start, int end, String x) {
		String s = "";
		for(int i = start; i <= end; i++) {
			s = s + x.charAt(i);
		}
		return s;
	}

	private static String multiplySingleDigits(String x, String y) {
		int a = Integer.parseInt(x);
		int b = Integer.parseInt(y);
		return a*b + "";
	}

	private static int makeEqualLength(String num1, String num2) {
		int l1 = num1.length();
		int l2 = num2.length();
		int len = 0;
		if (l1 > l2) {
			for (int i = 0; i <l1-l2; i++) {
				num2 = '0' + num2;
			}
			len = l1;
		} else if (l2 > l1) {
			for (int i = 0; i < l2-l1; i++) {
				num1 = '0' + num1;
			}
			len = l2;
		}
		return len;
	}
}
