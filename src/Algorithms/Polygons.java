package Algorithms;

import Base.Plotter;
import Base.Point;

import java.util.ArrayList;
import java.util.List;

public class Polygons {

	//For testing purposes
	public static void main(String[] args) {
		Plotter.plotObject(Polygons.Polygon(6, 9), 'p');
	}

	//Algorithm to draw regular polygons
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
			Polygon.addAll(Line.Midpoint(new Point(vertex_X[i], vertex_Y[i]), new Point(vertex_X[i + 1], vertex_Y[i + 1])));
		}
		//Generating the nᵗʰ side for polygon
		Polygon.addAll(Line.Midpoint(new Point(vertex_X[number_of_sides - 1], vertex_Y[number_of_sides - 1]), new Point(vertex_X[0], vertex_Y[0])));

		return Polygon;
	}

	//Scan Line Fill Algorithm (only works for convex polygons)
	public static List<Point> ScanLine(List<Point> Polygon) {

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

	public static void Flood() {
	}

	public static void Boundary() {
	}
}
