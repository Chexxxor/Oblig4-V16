package task1;

import java.util.Scanner;

public class FlipText {
	private static int count = 0;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Tekst som skal snus: ");
		String text = input.nextLine();
		System.out.print("Teksten baklengs: ");
		count = 0;
		backwards(text);
		System.out.println("\nAntall tegn: " + count);
		input.close();
	}
	
	public static void backwards(String text){
		backwards(text, 0);
	}

	public static void backwards(String text, int last){
		if(text.length() > last + 1)
			backwards(text, last + 1);
		count++;
		System.out.print(text.charAt(last));
	}
}
