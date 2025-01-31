package Lab_Practicals;

// Q2. Write a program to display basic 2D geometric primitives.

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// The basic 2D geometrics primitives are defined as: points, lines, arcs, sectors & polygons.

public class Q2 extends Frame {
	int screenCenterX;

	public static void main(String[] args) {
		Q2 menu = new Q2();
		menu.setVisible(true);
	}

	public Q2() {
		setTitle("Q4. To display basic 2D geometric primitives.");
		setLayout(new FlowLayout());
		setSize(800, 80);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		screenCenterX = (screenSize.width - 800) / 2;
		setLocation(screenCenterX, 0);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		add(new Label("Choose a primitive to draw:"));

		Button pointButton = new Button("Point");
		pointButton.addActionListener(e -> {
			Q2.Point point = new Q2.Point();
			point.setVisible(true);
		});
		add(pointButton);

		Button lineButton = new Button("Line");
		add(lineButton);
		lineButton.addActionListener(e -> {
			Q1.Line line = new Q1.Line();
			line.setLocation(screenCenterX, 80);
			line.setVisible(true);
		});

		Button arcButton = new Button("Arc");
		add(arcButton);
		arcButton.addActionListener(e -> {
			Q2.Arc arc = new Q2.Arc();
			arc.closureType = Arc2D.OPEN;
			arc.setVisible(true);
		});

		Button sectorButton = new Button("Sector");
		add(sectorButton);
		sectorButton.addActionListener(e -> {
			Q2.Arc sector = new Q2.Arc();
			sector.closureType = Arc2D.PIE;
			sector.setTitle("To draw sectors");
			sector.setVisible(true);
		});

		Button polygonButton = new Button("Polygon");
		add(polygonButton);
		polygonButton.addActionListener(e -> {
			Q2.Polygon polygon = new Q2.Polygon();
			polygon.setVisible(true);
		});
	}

	public class Point extends Frame {
		private int x, y;

		public Point() {
			setTitle("To draw points");
			setLayout(new FlowLayout());
			setSize(800, 720);
			setLocation(screenCenterX, 80);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});

			add(new Label("Input x & y values for the point:"));

			add(new Label("X:"));
			TextField xField = new TextField(3);
			add(xField);

			add(new Label("Y:"));
			TextField yField = new TextField(3);
			add(yField);

			Button submitButton = new Button("Draw");
			add(submitButton);
			submitButton.addActionListener(e -> {
				x = Integer.parseInt(xField.getText());
				y = Integer.parseInt(yField.getText());
				repaint();
			});
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(getWidth() / 2, getHeight() / 2);
			g2d.scale(1, -1);

			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(2));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2d.fill(new Ellipse2D.Double(x, y, 2, 2));
		}
	}

	public class Arc extends Frame {
		private int radius, startAngle, sweepAngle, closureType;

		public Arc() {
			setTitle("To draw arcs");
			setLayout(new FlowLayout());
			setSize(800, 720);
			setLocation(screenCenterX, 80);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});

			add(new Label("Input radius, start angle & sweep angle for the arc:"));

			add(new Label("Radius:"));
			TextField radiusField = new TextField(3);
			add(radiusField);

			add(new Label("Start angle:"));
			TextField startAngleField = new TextField(3);
			add(startAngleField);

			add(new Label("Sweep Angle:"));
			TextField sweepAngleField = new TextField(3);
			add(sweepAngleField);

			Button submitButton = new Button("Draw");
			add(submitButton);
			submitButton.addActionListener(e -> {
				radius = Integer.parseInt(radiusField.getText());
				startAngle = Integer.parseInt(startAngleField.getText());
				sweepAngle = Integer.parseInt(sweepAngleField.getText());
				repaint();
			});
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.translate(getWidth() / 2, getHeight() / 2);

			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(2));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2d.draw(new Arc2D.Double(-radius, -radius, 2 * radius, 2 * radius, startAngle, sweepAngle, closureType));
		}
	}

	public class Polygon extends Frame {
		private int numberOfSides, sideLength;

		public Polygon() {
			setTitle("To draw polygons");
			setLayout(new FlowLayout());
			setSize(800, 720);
			setLocation(screenCenterX, 80);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});

			add(new Label("Input number of sides & side length for the polygon:"));

			add(new Label("Number of Sides:"));
			TextField sidesField = new TextField(3);
			add(sidesField);

			add(new Label("Side Length:"));
			TextField lengthField = new TextField(3);
			add(lengthField);

			Button submitButton = new Button("Draw");
			add(submitButton);
			submitButton.addActionListener(e -> {
				numberOfSides = Integer.parseInt(sidesField.getText());
				sideLength = Integer.parseInt(lengthField.getText());
				repaint();
			});
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Graphics2D g2d = (Graphics2D) g;

			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(2));
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			Path2D path = new Path2D.Double();
			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;
			double radius = (sideLength / (2 * Math.sin(Math.PI / numberOfSides)));

			for (int i = 0; i <= numberOfSides; i++) {
				double angle = 2 * Math.PI * i / numberOfSides;
				double x = centerX + radius * Math.cos(angle);
				double y = centerY + radius * Math.sin(angle);

				if (i == 0) {
					path.moveTo(x, y);
				} else {
					path.lineTo(x, y);
				}
			}
			g2d.draw(path);
		}
	}
}
