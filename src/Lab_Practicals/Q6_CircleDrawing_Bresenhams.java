package Lab_Practicals;

// Q6. Write a program for circle drawing as Raster Graphics Display.

import Base.Point;
import Base.Plotter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Q6_CircleDrawing_Bresenhams {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program for circle drawing as Raster Graphics Display using DDA Circle Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Taking input for circle radius
		System.out.println("- Enter radius for the circle: ");
		int radius = scan.nextInt();

		//Drawing the circle
		Plotter plotObj = new Plotter(radius + 1, radius + 1);
		plotObj.WorldPlotObject(BresenhamsCircle(radius), '*');
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