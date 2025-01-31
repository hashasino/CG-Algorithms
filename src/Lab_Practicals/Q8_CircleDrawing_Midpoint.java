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
		System.out.println("This is a program for drawing circles, arcs & sectors using Midpoint Circle Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Instantiating Plotter Object
		Plotter plotObj = new Plotter(10, 10);

		while (true) {
			System.out.println("\nWhat do you want to do?");
			System.out.println("1. Draw Arc      2. Draw Sector");
			System.out.println("3. Draw Circle   7. Display Grid");
			System.out.println("8. Clear Grid    9. Resize Grid");
			System.out.println("0. Exit Program");

			int choice = scan.nextInt();
			switch (choice) {
				case 1: {
					System.out.println("Enter the angle for arc (in degrees): ");
					int angle = scan.nextInt();
					System.out.println("Enter the radius for arc: ");
					int radius = scan.nextInt();
					System.out.println("Enter starting coordinates (x y) for arc: ");
					Point startPoint = new Point(scan.nextInt(), scan.nextInt());
					plotObj.WorldPlotObject(Q2_GeometricPrimitives.Arc(angle, radius, startPoint), 'o');
					System.out.println("Arc drawn.");
				}
				break;

				case 2: {
					System.out.println("Enter the angle for sector (in degrees): ");
					int angle = scan.nextInt();
					System.out.println("Enter the radius for sector: ");
					int radius = scan.nextInt();
					System.out.println("Enter starting coordinates (x y) for sector: ");
					Point startPoint = new Point(scan.nextInt(), scan.nextInt());
					List<Point> Sector = Q2_GeometricPrimitives.Sector(angle, radius, startPoint);
					plotObj.WorldPlotObject(Sector, 'o');
					System.out.println("Sector drawn.");
				}
				break;

				case 3: {
					System.out.println("Enter the radius for circle: ");
					int radius = scan.nextInt();
					plotObj.WorldPlotObject(MidpointCircle(radius), '*');
					System.out.println("Circle drawn.");
				}
				break;

				case 7:
					plotObj.WorldDisplay();
					break;

				case 8:
					plotObj.ClearWorld();
					System.out.println("Grid cleared.");
					break;

				case 9:
					System.out.println("WARNING: Resizing grid will clear all objects drawn.");
					System.out.println("1. Continue  0.Cancel");
					choice = scan.nextInt();
					if (choice == 1) {
						System.out.println("Enter xRadius for new grid: ");
						int xRadius = scan.nextInt();
						System.out.println("Enter yRadius for new grid: ");
						int yRadius = scan.nextInt();
						plotObj.ResizeWorld(xRadius, yRadius);
						System.out.println("Grid resized.");
					}
					break;

				case 0:
					System.out.println("Exiting program.");
					scan.close();
					System.exit(0);

				default:
					System.out.println("Invalid choice. Try again.");

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