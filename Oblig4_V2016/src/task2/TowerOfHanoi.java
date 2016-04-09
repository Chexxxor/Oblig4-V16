package task2;

import java.util.Scanner; 

public class TowerOfHanoi {
  /** Main method */
  public static void main(String[] args) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);
    System.out.print("Antall skiver: ");
    int n = input.nextInt();
    input.close();

    // Find the solution recursively
    System.out.println("Flyttingene blir da:");
    moveDisks(n, 'A', 'B', 'C');
  }

  /** The method for finding the solution to move n disks
      from fromTower to toTower with auxTower */
  public static void moveDisks(int n, char fromTower,
      char toTower, char auxTower) {
    if (n == 1) // Stopping condition
      System.out.println("Flytter skive " + n + " fra " +
        fromTower + " til " + toTower);
    else {
      moveDisks(n - 1, fromTower, auxTower, toTower);
      System.out.println("Flytter skive " + n + " fra " +
        fromTower + " til " + toTower);
      moveDisks(n - 1, auxTower, toTower, fromTower);
    }
  }
}