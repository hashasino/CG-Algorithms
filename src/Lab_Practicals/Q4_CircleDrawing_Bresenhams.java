package Lab_Practicals;

// Q4. Write a program to display a series of concentric circles of varying radius.

import Base.Plotter;
import Base.Point;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Q4_CircleDrawing_Bresenhams {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program to display a series of concentric circles of varying radius using Bresenham's Circle Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Taking input for number of concentric circles
		System.out.println("How many concentric circles do you want?");
		int circle = scan.nextInt();

		//Taking input for the radii of the concentric circles
		int[] radii = new int[circle];
		System.out.println("Enter their radii: ");
		for (int i = 0; i < circle; i++) {
			radii[i] = scan.nextInt();
		}

		//Drawing circles
		for (int i = 0; i < circle; i++) {
			System.out.println("\n" + (i + 1) + ". Radius = " + radii[i] + "\n");
			Bresenhams(radii[i]);
		}
	}

	//Bresenham's Circle Drawing Algorithm
	static void Bresenhams(int radius) {

		//Initializing point list for Algorithms.Circle
		List<Point> Circle = new ArrayList<>();

		//Initializing loop variables
		int x = 0;
		int y = radius;
		int decisionParameter = 3 - 2 * radius;

		//Calculating the first octant
		while (x <= y) {
			Circle.add(new Point(x, y));
			x++;
			if (decisionParameter < 0) {
				decisionParameter += 4 * x + 6;
			} else {
				y--;
				decisionParameter += 4 * (x - y) + 10;
			}
		}

		//Plotting the Circle using the coordinates calculated
		Plotter plotObj = new Plotter();
		plotObj.plotCoordinates(Circle, radius);

	}

}
