package Lab_Practicals;

// Q6. Write a program for circle drawing as Raster Graphics Display.

import Base.Point;
import Base.Plotter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Q6_CircleDrawing_DDA {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program for circle drawing as Raster Graphics Display using DDA Circle Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Taking input for circle radius
		System.out.println("- Enter radius for the circle: ");
		int radius = scan.nextInt();

		//Drawing the circle
		Plotter.plotObject(DDA(radius), 'o');
	}

	//DDA Circle Drawing Algorithm
	public static List<Point> DDA(int radius) {

		//Finding epsilon
		int power = 0;
		while (Math.pow(2, power) <= radius) {
			power++;
		}
		double epsilon = Math.pow(2, -power);

		//Initializing point list for Octant
		List<Point> Octant = new ArrayList<>();

		//Initializing loop variables
		double x = 0;
		double y = radius;

		//Calculating the first octant
		while (x <= y) {
			Octant.add(new Point(x, y));
			x = x + y * epsilon;
			y = y - x * epsilon;
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
