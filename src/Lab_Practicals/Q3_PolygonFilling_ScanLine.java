package Lab_Practicals;

// Q3. Write a program to display a filled square.

import Base.Point;
import Base.Plotter;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Q3_PolygonFilling_ScanLine {
	public static void main(String[] args) {

		//Program Declaration
		System.out.println("This is a program to display a filled square using Scan Line Fill Algorithm.");

		//Taking input for square's side length
		Scanner scan = new Scanner(System.in);
		System.out.println("- Enter side length for the square: ");
		int sideLength = scan.nextInt();

		//Plotting the square with fill
		Plotter plotObj = new Plotter(sideLength, sideLength);
		List<Point> SquareOutline = Q2_GeometricPrimitives.Polygon(4, sideLength);
		plotObj.WorldPlotObject(SquareOutline, 'x');
		List<Point> SquareFill = ScanLineFill(SquareOutline);
		plotObj.WorldPlotObject(SquareFill, '+');
		plotObj.WorldDisplay();
	}

	//Scan Line Fill Algorithm (only works for convex polygons)
	public static List<Point> ScanLineFill(List<Point> Polygon) {

		//Plotting the polygon's outline on a grid to scan
		String[][] grid = Plotter.initializeObjectGrid(Polygon, 'o');
		int centerX = grid[0].length / 2;
		int centerY = grid.length / 2;

		//Initializing point list for fill
		List<Point> Fill = new ArrayList<>();

		for (int y = 0; y < grid.length; y++) {

			//Checking if the row has only a single boundary point & skipping if true
			int count = 0;
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x].equals("o")) count++;
			}
			if (count % 2 != 0) continue;

			//Checking if current point is inside polygon's boundaries & adding it to the list if true
			boolean inside = false;
			for (int x = 0; x < grid[0].length; x++) {
				if (grid[y][x].equals("o")) {
					inside = !inside;
				} else if (inside) {
					Fill.add(new Point(x - centerX, y - centerY));
				}
			}
		}

		return Fill;
	}

}