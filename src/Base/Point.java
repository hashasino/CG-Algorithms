package Base;

public class Point { //The basic point class/blueprint

	public double x;
	public double y;
	public double z;
	boolean PointIs2D = false;

	//Constructor for 2D point
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		PointIs2D = true;
		z = 1;
	}

	//Constructor for 3D point
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//To get coordinate value as a string
	public String getCoordinates() {
		int x = (int) Math.round(this.x);
		int y = (int) Math.round(this.y);
		int z = (int) Math.round(this.z);

		if (PointIs2D)
			return "(" + x + "," + y + ")";
		else
			return "(" + x + "," + y + "," + z + ")";
	}

	//For testing purposes
	public static void main(String[] args) {
		Point point = new Point(2.235, 3.1234567890123455, 1.03);
		System.out.println(point.getCoordinates());
	}
}