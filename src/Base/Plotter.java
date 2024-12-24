package Base;

import java.util.List;

public class Plotter { //Contains methods to print & plot objects
	private final int width;
	private final int height;
	private final String[][] World;
	private final int centerX;
	private final int centerY;

	public Plotter(int width, int height) {
		this.width = width;
		this.height = height;
		this.World = initializeWorldGrid(width, height);
		this.centerX = width / 2;
		this.centerY = height / 2;
	}

	//To initialize grid for plotting a single object
	private static String[][] initializeObjectGrid(int width, int height) {

		//Initializing grid/frame buffer
		String[][] grid = new String[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = ".";
			}
		}

//		//Drawing ordinate & abscissa markings
//		int centerX = width / 2;
//		int centerY = height / 2;
//		for (int j = 0; j < height; j++) {
//			int value = centerY - j;
//			if (value > 0)
//				grid[centerX][j] = String.valueOf('|');
//			if (value < 0)
//				grid[centerX][j] = String.valueOf('|'); //Ordinate
//		}
//		for (int i = 0; i < width; i++) {
//			int value = i - centerX;
//			if (value > 0)
//				grid[i][centerY] = String.valueOf("--");
//			if (value < 0)
//				grid[i][centerY] = String.valueOf("--"); //Abscissa
//		}
//		grid[centerX][centerY] = "+"; //Center

		return grid;
	}

	//To initialize grid for plotting multiple objects
	private String[][] initializeWorldGrid(int width, int height) {

		String[][] grid = new String[width][height];

		//Initializing grid/frame buffer
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[i][j] = ".";
			}
		}

		//Drawing ordinate & abscissa markings
		int centerX = width / 2;
		int centerY = height / 2;
		for (int j = 0; j < height; j++) {
			int value = centerY - j;
			grid[centerX][j] = String.valueOf(value); //Ordinate
		}
		for (int i = 0; i < width; i++) {
			int value = i - centerX;
			grid[i][centerY] = String.valueOf(value); //Abscissa
		}
		grid[centerX][centerY] = "0"; //Center

		return grid;
	}

	// To check if the point to be plotted is within World Grid
	private boolean isValidCoordinate(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	//To print object coordinates
	public static void printObject(List<Point> Object) {
		for (Point point : Object) {
			System.out.print(point.getCoordinates() + ' ');
		}
	}

	//To plot object in its ObjectGrid
	public static void plotObject(List<Point> Object, char character) {

		//Setting initial min/max value
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;

		//Finding min & max for x & y for all points in the list
		for (Point point : Object) {
			if (point.x < minX) minX = point.x;
			if (point.x > maxX) maxX = point.x;
			if (point.y < minY) minY = point.y;
			if (point.y > maxY) maxY = point.y;
		}

		// Calculating grid dimensions
		int width = (int) (maxX - minX + 1);
		int height = (int) (maxY - minY + 1);

		//Initializing grid/frame buffer
		String[][] grid = Plotter.initializeObjectGrid(width, height);

		// Plotting coordinates
		for (Point point : Object) {
			int X = (int) (Math.round(point.x - minX) + 1);
			int Y = (int) (Math.round(point.y - minY) + 1);

			grid[X - 1][height - Y] = String.valueOf(character);
		}

		// Displaying coordinate grid
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				String cell = grid[i][j];
				System.out.printf("%3s", cell);
			}
			System.out.println();
		}
	}

	//To plot a point in the World Grid
	public void WorldPlotPoint(Point Point, char character) {
		int X = (int) (Math.round(Point.x));
		int Y = (int) (Math.round(Point.y));
		if (isValidCoordinate(X, Y)) {
			World[centerX + X][centerY - Y] = String.valueOf(character);
		}
	}

	//To plot objects in the World Grid
	public void WorldPlotObject(List<Point> Object, char character) {
		for (Point point : Object) {
			WorldPlotPoint(point, character);
		}
	}

	//To display the World Grid
	public void WorldDisplay() {
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				String cell = World[i][j];
				System.out.printf("%4s", cell);
			}
			System.out.println();
		}
	}

	//To clear a point in World Grid
	public void ClearPoint(Point Point) {
		WorldPlotPoint(Point, '.');
	}

	//To clear objects in World Grid
	public void ClearObject(List<Point> Object) {
		WorldPlotObject(Object, '.');
	}

	//To completely clear the WorldGrid
	public void ClearWorld() {
		initializeWorldGrid(width, height);
	}

}