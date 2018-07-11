package hello_world;

import java.io.FileInputStream;
import java.util.Scanner;

public class HelloWorld {
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("src/hello_world/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		System.out.println(str);
		sc.close();
	}
}
