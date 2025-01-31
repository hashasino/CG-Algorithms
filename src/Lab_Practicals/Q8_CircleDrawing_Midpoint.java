package Lab_Practicals;

// Q8. Write a program to draw a circle using Midpoint algorithm. Modify the same for drawing an arc and sector.

import Base.Point;
import Base.Plotter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Q8_CircleDrawing_Midpoint {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program for drawing a circle, an arc & a sector using Midpoint Circle Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("- What do you want to draw?");
			System.out.println("1. Arc    2.Sector    3.Circle    0. Exit Program");
			int choice = scan.nextInt();
			int angle, radius, x, y;
			switch (choice) {
				case 1:
					System.out.println("Enter the angle for arc (in degrees): ");
					angle = scan.nextInt();
					System.out.println("Enter the radius for arc: ");
					radius = scan.nextInt();
					System.out.println("Enter the start coordinates (x y) for arc: ");
					x = scan.nextInt();
					y = scan.nextInt();
					Plotter.plotObject(Q2_GeometricPrimitives.Arc(angle, radius, new Point(x, y)), '0');
					break;
				case 2:
					System.out.println("Enter the angle for sector (in degrees): ");
					angle = scan.nextInt();
					System.out.println("Enter the radius for sector: ");
					radius = scan.nextInt();
					System.out.println("Enter the start coordinates (x y) for sector: ");
					x = scan.nextInt();
					y = scan.nextInt();
					Plotter.plotObject(Q2_GeometricPrimitives.Sector(angle, radius, new Point(x, y)), '0');
					break;
				case 3:
					System.out.println("Enter the radius for circle: ");
					radius = scan.nextInt();
					Plotter.plotObject(MidpointCircle(radius), '0');
					break;
				case 0:
					scan.close();
					System.exit(0);
				default:
					System.out.println("Invalid input. Try again.");

			}
		}
	}

	//TODO - Add methods to draw arc & sector by modifying Midpoint Circle Drawing Method
	//Midpoint Circle Drawing Algorithm
	public static List<Point> MidpointCircle(int radius) {

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
				decisionParameter += 2 * x + 1;
			} else {
				y--;
				decisionParameter += 2 * (x - y) + 1;
			}
		}

		//Initializing point list for Circle
		List<Point> Circle = new ArrayList<>();

		//Generating other octants
		for (Point point : Octant) {
			int X = (int) (Math.round(point.x));
			int Y = (int) (Math.round(point.y));
			Circle.add(new Point(X, Y));
			Circle.add(new Point(Y, X));
			Circle.add(new Point(Y, -X));
			Circle.add(new Point(X, -Y));
			Circle.add(new Point(-X, -Y));
			Circle.add(new Point(-Y, -X));
			Circle.add(new Point(-Y, X));
			Circle.add(new Point(-X, Y));
		}

		return Circle;
	}

}
