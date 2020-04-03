package eg.edu.alexu.csd.datastructure.stack.cs;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Stack stack = new Stack();
		do {
			System.out.println("Please choose an action");
			System.out.println("-----------------------");
			System.out.println("1: Push");
			System.out.println("2: Pop");
			System.out.println("3: Peak");
			System.out.println("4: Get size");
			System.out.println("5: Check if empty");
			System.out.println("6: Exit");

			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int x = sc.nextInt();
			switch (x) {
				case 1:
					System.out.println("Enter object you want to push to the stack");
					Object obj = sc.next();
					stack.push(obj);
					break;
				case 2:
					try {
						System.out.println(stack.pop());
					} catch (RuntimeException e) {
						System.out.println("Empty stack");
					}
					break;
				case 3:
					try {
						System.out.println("Peak is:" + stack.peek());
					} catch (RuntimeException e) {
						System.out.println("Empty stack");
					}
					break;
				case 4:
					System.out.println("Size =" + stack.size());
					break;
				case 5:
					System.out.println(stack.isEmpty());
					break;
				case 6:
					System.exit(0);
			}

		} while (true);
	}

}