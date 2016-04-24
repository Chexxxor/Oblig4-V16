package task2;

import java.util.Scanner; 

public class TowerOfHanoi {
	private static int calls;
	private static int calls2;
	private static int moves;
	/** Main method */
	public static void main(String[] args) {
		// Create a Scanner
		Scanner input = new Scanner(System.in);
		System.out.print("Antall skiver: ");
		int n = input.nextInt();
		input.close();

		// Find the solution recursively
		System.out.println("Flyttingene blir da:");
		calls = 0;
		calls2 = 0;
		moves = 0;

		calls2++;
		moveDisks(n, 'A', 'B', 'C');

		System.out.println("Antall funksjonskall: " + calls + " (Fra inni metodekallet)");
		System.out.println("Antall funksjonskall: " + calls2 + " (Fra utenfor metodekallet)");
		System.out.println("Skivene ble flyttet: " + moves + " ganger.");
	}

	/** The method for finding the solution to move n disks
      from fromTower to toTower with auxTower */
	public static void moveDisks(int n, char fromTower,
			char toTower, char auxTower) {
		calls++; //Counting inside calls
		if (n == 1){ // Stopping condition
			moves++; //Counting moves
			System.out.println("Flytter skive " + n + " fra " +
					fromTower + " til " + toTower);
		}
		else {
			calls2++; //Counting outside calls
			moveDisks(n - 1, fromTower, auxTower, toTower);
			
			moves++;
			System.out.println("Flytter skive " + n + " fra " +
					fromTower + " til " + toTower);
			
			calls2++;
			moveDisks(n - 1, auxTower, toTower, fromTower);
		}
	}
}