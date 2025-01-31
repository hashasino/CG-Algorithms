package Lab_Practicals;

// Q4. Write a program to display a series of concentric circles of varying radius.

import Base.Point;
import Base.Plotter;

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
		System.out.println("- How many concentric circles do you want?");
		int circle = scan.nextInt();

		//Taking input for the radii of the concentric circles
		int[] radii = new int[circle];
		System.out.println("Enter their radii: ");
		for (int i = 0; i < circle; i++) {
			radii[i] = scan.nextInt();
		}

		//Finding maximum of the radii
		int maxRadius = 0;
		for (int radius : radii) {
			if (maxRadius < radius) maxRadius = radius;
		}

		//Instantiating Plotter object of maxRadius
		Plotter plotObj = new Plotter(2 * maxRadius + 1, 2 * maxRadius + 1);

		//Drawing circles
		for (int i = 0; i < circle; i++)
			plotObj.WorldPlotObject(BresenhamsCircle(radii[i]), 'o');

		//Displaying circles
		plotObj.WorldDisplay();

	}

	//Bresenham's Circle Drawing Algorithm
	public static List<Point> BresenhamsCircle(int radius) {

		//Initializing point list for Octant
		List<Point> Octant = new ArrayList<>();

		//Initializing loop variables
		int x = 0;
		int y = radius;
		int decisionParameter = 3 - 2 * radius;

		//Calculating the first octant
		while (x <= y) {
			Octant.add(new Point(x, y));
			x++;
			if (decisionParameter < 0) {
				decisionParameter += 4 * x + 6;
			} else {
				y--;
				decisionParameter += 4 * (x - y) + 10;
			}
		}

		//Initializing point list for Circle
		List<Point> Circle = new ArrayList<>();

		//Generating other octants
		for (Point point : Octant) {
			Circle.add(new Point(point.x, point.y));
			Circle.add(new Point(point.y, point.x));
			Circle.add(new Point(point.y, -point.x));
			Circle.add(new Point(point.x, -point.y));
			Circle.add(new Point(-point.x, -point.y));
			Circle.add(new Point(-point.y, -point.x));
			Circle.add(new Point(-point.y, point.x));
			Circle.add(new Point(-point.x, point.y));
		}

		return Circle;
	}
}
