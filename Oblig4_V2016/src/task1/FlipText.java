package task1;

import java.util.Scanner;

public class FlipText {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Tekst som skal snus: ");
		String text = input.nextLine();
		System.out.print("Teksten baklengs: ");
		backwards(text);
	}

	public static void backwards(String text){
		if(text.length() > 1)
			backwards(text.substring(1));
		System.out.print(text.charAt(0));
	}
}
