package Lab_Practicals;

// Q7. Write a program to draw a line using Bresenham line drawing algorithm.

import Base.Point;
import Base.Plotter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Q7_LineDrawing_Bresenhams {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program for 2D line drawing as Raster Graphics Display using Bresenham's Line Drawing Algorithm.");

		//Instantiating Scanner object
		Scanner scan = new Scanner(System.in);

		//Taking input for the parameters for the chosen algorithm
		System.out.println("- Input start & end coordinates in the following format: x1 y1 x2 y2");
		Point startPoint = new Point(scan.nextInt(), scan.nextInt());
		Point endPoint = new Point(scan.nextInt(), scan.nextInt());

		//Calculating line coordinates
		List<Point> Line = BresenhamsLine(startPoint, endPoint);

		//Printing line coordinates
		Plotter.printCoordinates(Line);
		System.out.println();

		//Plotting line coordinates
		int gridSize = (int) Math.max(Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y));
		Plotter plotObj = new Plotter(gridSize, gridSize);
		plotObj.WorldPlotObject(Line, '*');
		plotObj.WorldDisplay();
	}

	//Bresenham's Line Drawing Algorithm
	public static List<Point> BresenhamsLine(Point startPoint, Point endPoint) {

		//Calculating delX & delY
		double delX = Math.abs(endPoint.x - startPoint.x);
		double delY = Math.abs(endPoint.y - startPoint.y);
		boolean isSteep = delY > delX; //Swapping x and y for steep slopes
		if (isSteep) {
			double temp = startPoint.x;
			startPoint.x = startPoint.y;
			startPoint.y = temp;

			temp = endPoint.x;
			endPoint.x = endPoint.y;
			endPoint.y = temp;

			delX = Math.abs(endPoint.x - startPoint.x);
			delY = Math.abs(endPoint.y - startPoint.y);
		}

		//Initializing decision parameter
		double decisionParameter = 2 * delY - delX;

		//Deciding increment sign+values (-1, 0, 1)
		int xIncrement = Double.compare(endPoint.x, startPoint.x);
		int yIncrement = Double.compare(endPoint.y, startPoint.y);

		//Initializing point list for Line
		List<Point> Line = new ArrayList<>();

		//Adding the first point to the point list
		if (isSteep) Line.add(new Point(startPoint.y, startPoint.x));
		else Line.add(new Point(startPoint.x, startPoint.y));

		//Initializing loop variables
		double x = startPoint.x;
		double y = startPoint.y;
		endPoint.x = Math.round(endPoint.x);
		endPoint.y = Math.round(endPoint.y);

		//Calculating line coordinates
		while ((int) x != endPoint.x || (int) y != endPoint.y) {
			x = x + xIncrement;
			if (decisionParameter < 0) {
				decisionParameter += 2 * delY;
			} else {
				y = y + yIncrement;
				decisionParameter += 2 * (delY - delX);
			}
			if (isSteep) Line.add(new Point(y, x));
			else Line.add(new Point(x, y));
		}

		return Line;

	}

}