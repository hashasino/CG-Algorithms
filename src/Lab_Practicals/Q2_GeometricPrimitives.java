package Lab_Practicals;

// Q2. Write a program to display basic 2D geometric primitives.

import Base.Point;
import Base.Plotter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

// The basic 2D geometrics primitives are defined as: points, lines, arcs, sectors & polygons.

public class Q2_GeometricPrimitives {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program to display basic 2D geometric primitives by drawing them on a grid.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Instantiating Plotter Object
		Plotter plotObj = new Plotter(15, 15);

		while (true) {
			System.out.println("\nWhat do you want to do?");
			System.out.println("1. Draw Point    2. Draw Line");
			System.out.println("3. Draw Arc      4. Draw Sector");
			System.out.println("5. Draw Polygon  6. Draw Ellipse");
			System.out.println("7. Display Grid  8. Clear Grid");
			System.out.println("9. Resize Grid   0. Exit Program");

			int choice = scan.nextInt();

			switch (choice) {
				case 1: {
					System.out.print("Enter the point's coordinates in the following format: x y \n");
					List<Point> Point = new ArrayList<>();
					Point.add(new Point(scan.nextInt(), scan.nextInt()));
					plotObj.WorldPlotObject(Point, '+');
					System.out.println("Point drawn.");
				}
				break;

				case 2: {
					System.out.println("Enter the line's start & end coordinates in the following format: x1 y1 x2 y2 ");
					Point startPoint = new Point(scan.nextInt(), scan.nextInt());
					Point endPoint = new Point(scan.nextInt(), scan.nextInt());
					List<Point> Line = Q1_LineDrawing_SimpleDDA.SimpleDDALine(startPoint, endPoint);
					plotObj.WorldPlotObject(Line, '*');
					System.out.println("Line drawn.");
				}
				break;

				case 3: {
					System.out.println("Enter the angle for arc (in degrees): ");
					int angle = scan.nextInt();
					System.out.println("Enter the radius for arc: ");
					int radius = scan.nextInt();
					System.out.println("Enter starting coordinates (x y) for arc: ");
					Point startPoint = new Point(scan.nextInt(), scan.nextInt());
					List<Point> Arc = Arc(angle, radius, startPoint);
					plotObj.WorldPlotObject(Arc, 'o');
					System.out.println("Arc drawn.");
				}
				break;

				case 4: {
					System.out.println("Enter the angle for sector (in degrees): ");
					int angle = scan.nextInt();
					System.out.println("Enter the radius for sector: ");
					int radius = scan.nextInt();
					System.out.println("Enter starting coordinates (x y) for sector: ");
					Point startPoint = new Point(scan.nextInt(), scan.nextInt());
					List<Point> Sector = Sector(angle, radius, startPoint);
					plotObj.WorldPlotObject(Sector, 'o');
					System.out.println("Sector drawn.");
				}
				break;

				case 5: {
					System.out.println("Enter the number of sides for the polygon: ");
					int sides = scan.nextInt();
					System.out.println("Enter the length for those sides: ");
					int length = scan.nextInt();
					List<Point> Polygon = Polygon(sides, length);
					plotObj.WorldPlotObject(Polygon, 'x');
					System.out.println("Polygon drawn.");
				}
				break;

				case 6:
					System.out.println("Implementation not available yet.\nTry something else.");
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

	//Arc Drawing Algorithm using parametric functions
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

	//Sector Drawing Method
	public static List<Point> Sector(double angle, int radius, Point startPoint) {

		//Initializing point list for the Sector with the arc
		List<Point> Sector = Arc(angle, radius, startPoint);

		//Calculating center & end point
		double startAngle = Math.atan2(startPoint.y, startPoint.x);
		double endAngle = startAngle + Math.toRadians(angle);

		Point centerPoint = new Point(0, 0);
		Point endPoint = new Point(radius * Math.cos(endAngle), radius * Math.sin(endAngle));

		//Adding the two radii to complete the Sector
		Sector.addAll(Q1_LineDrawing_SimpleDDA.SimpleDDALine(startPoint, centerPoint));
		Sector.addAll(Q1_LineDrawing_SimpleDDA.SimpleDDALine(endPoint, centerPoint));

		return Sector;
	}

	//Regular Polygon Drawing Algorithm
	public static List<Point> Polygon(int number_of_sides, int side_length) {

		//Calculating the radius of the circle circumscribing the polygon
		double radius = (side_length / (2 * Math.sin(Math.PI / number_of_sides)));

		//Initializing point list to store points generated
		List<Point> Polygon = new ArrayList<>();

		//Declaring vertex variables
		int[] vertex_X = new int[number_of_sides];
		int[] vertex_Y = new int[number_of_sides];

		//Generating vertex list
		for (int i = 0; i < number_of_sides; i++) {
			double vertex_angle = 2 * Math.PI * i / number_of_sides;
			vertex_X[i] = (int) Math.round(radius * Math.cos(vertex_angle));
			vertex_Y[i] = (int) Math.round(radius * Math.sin(vertex_angle));
		}

		//Generating the first n-1 sides for polygon
		for (int i = 0; i < number_of_sides - 1; i++) {
			Polygon.addAll(Q1_LineDrawing_SimpleDDA.SimpleDDALine(new Point(vertex_X[i], vertex_Y[i]), new Point(vertex_X[i + 1], vertex_Y[i + 1])));
		}
		//Generating the nᵗʰ side for polygon
		Polygon.addAll(Q1_LineDrawing_SimpleDDA.SimpleDDALine(new Point(vertex_X[number_of_sides - 1], vertex_Y[number_of_sides - 1]), new Point(vertex_X[0], vertex_Y[0])));

		return Polygon;
	}


}