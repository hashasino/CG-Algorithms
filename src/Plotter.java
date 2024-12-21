import java.util.List;

public class Plotter {

	public void printCoordinates(List<Point> Line) {
		for (Point point : Line) {
			System.out.print(point.getCoordinates() + ' ');
		}
	}

	public void plotCoordinates(List<Point> Line) {

		//Setting initial min/max value
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;

		//Finding min & max for x & y for all points in the list
		for (Point point : Line) {
			if (point.x < minX) minX = point.x;
			if (point.x > maxX) maxX = point.x;
			if (point.y < minY) minY = point.y;
			if (point.y > maxY) maxY = point.y;
		}

		// Calculating grid dimensions
		int width = (int) (maxX - minX + 1);
		int height = (int) (maxY - minY + 1);

		//Initializing grid/frame buffer
		String[][] grid = ObjectCoordinates(width, height);

		// Plotting coordinates
		for (Point point : Line) {
			int X = (int) (Math.round(point.x - minX) + 1);
			int Y = (int) (Math.round(point.y - minY) + 1);

			grid[X - 1][height - Y] = String.valueOf('*');
		}

		// Displaying coordinate grid
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				String cell = grid[i][j];
				System.out.printf("%4s", cell);
			}
			System.out.println();
		}
	}

	String[][] ObjectCoordinates(int width, int height) {

		String[][] grid = new String[width][height];

		//Drawing background characters
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

	String[][] WorldCoordinates(int width, int height) {

		String[][] grid = new String[width][height];

		//Drawing background characters
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

}
