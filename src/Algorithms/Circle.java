package Algorithms;

import Base.Plotter;
import Base.Point;

import java.util.ArrayList;
import java.util.List;

public class Circle { //Contains all Circle Drawing Algorithms

	//For testing purposes
	public static void main(String[] args) {

		int[] testRadii = {5, 9, 15, 21};
		int[] testAngles = {0, 45, 90, 135, 180, 225, 270, 315, 360};

//		for (int i : testAngles) {
//			System.out.println("  Angle - " + i);
//			Plotter.plotObject(Arc(i, 10, new Point(0, 10)), '0');
//			Plotter.plotObject(Sector(i, testRadii[1], new Point(0, testRadii[1])), '0');
//			System.out.println();
//		}

		for (int i : testRadii) {
			System.out.println("  Radius - " + i);
			Plotter.plotObject(DDA(i), '0');
			Plotter.plotObject(Bresenhams(i), '0');
			Plotter.plotObject(MidPoint(i), '0');
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

	//FIXME - PRIORITY! Find a way to find centerPoint of the circle & implement it in Sector
	//For drawing sectors
	public static List<Point> Sector(double angle, int radius, Point startPoint) {

		//Initializing point list for the Sector with the arc
		List<Point> Sector = Arc(angle, radius, startPoint);

		//Calculating center & end point
		double startAngle = Math.atan2(startPoint.y, startPoint.x);
		double endAngle = startAngle + Math.toRadians(angle);

		Point centerPoint = new Point(0, 0);
		Point endPoint = new Point(radius * Math.cos(endAngle), radius * Math.sin(endAngle));

		//Adding the two radii to complete the Sector
		Sector.addAll(Line.Midpoint(startPoint, centerPoint));
		Sector.addAll(Line.Midpoint(endPoint, centerPoint));

		return Sector;
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

	//Midpoint Circle Drawing Algorithm
	public static List<Point> MidPoint(int radius) {

		//Initializing point list for Octant
		List<Point> Octant = new ArrayList<>();

		//Initializing loop variables
		int x = 0;
		int y = radius;
		int decisionParameter = 1 - radius;

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