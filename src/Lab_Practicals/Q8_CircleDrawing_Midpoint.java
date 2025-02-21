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
		System.out.println("This is a program to draw a circle using Midpoint Circle Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Taking input for circle radius
		System.out.println("- Enter radius for the circle: ");
		int radius = scan.nextInt();

		//Drawing the circle
		Plotter plotObj = new Plotter(radius + 1, radius + 1);
		plotObj.WorldPlotObject(MidpointCircle(radius), '*');
		plotObj.WorldDisplay();
	}

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