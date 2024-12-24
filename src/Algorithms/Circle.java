package Algorithms;

import Base.Plotter;
import Base.Point;

import java.util.ArrayList;
import java.util.List;

public class Circle { //Contains all Circle Drawing Algorithms
	public static void main(String[] args) {

		int[] angles = {0, 90, 180, 270, 360};

		for (int i : angles) {
			System.out.println("  Angle - " + i);
			Plotter.plotObject(Arc(i, 10, new Point(0, 10)), '0');
			System.out.println();
		}
	}

	//Algorithm to draw arcs using their parametric functions
	public static List<Point> Arc(double angle, int radius, Point startPoint) {

		//Calculating start & end angles
		double startAngle = Math.atan2(startPoint.y, startPoint.x);
		double endAngle = startAngle + Math.toRadians(angle);

		//Calculating the angle increment based on radius
		double angleIncrement = 1.0 / radius;

		//Initializing point list for the arc
		List<Point> Arc = new ArrayList<>();

		//Initializing loop variables
		double x, y, currentAngle = startAngle;

		//Generating arc coordinates
		while (currentAngle < endAngle) {
			x = radius * Math.cos(currentAngle);
			y = radius * Math.sin(currentAngle);
			Arc.add(new Point(x, y));
			currentAngle += angleIncrement;
		}
		//Adding the final point
		double endX = radius * Math.cos(endAngle);
		double endY = radius * Math.sin(endAngle);
		Arc.add(new Point(endX, endY));

		return Arc;
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

	//Bresenham's Circle Drawing Algorithm
	public static List<Point> Bresenhams(int radius) {

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

	//Midpoint Circle Drawing Algorithm
	public static List<Point> MidPoint(int radius) {

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

	//Midpoint Ellipse Drawing Algorithm
	public static void Ellipse(int radiusOne, int radiusTwo) {
	}

}