package Lab_Practicals;

// Q1. Write a program for 2D line drawing as Raster Graphics Display.

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class Q1 {

	public static void main(String[] args) {
		Q1.Line line = new Q1.Line();
		line.setTitle("Q1. For 2D Line Drawing as Raster Graphics Display");
		line.setVisible(true);
	}

	public static class Line extends Frame {
		private int x1, y1, x2, y2;

		public Line() {
			setTitle("To draw lines");
			setSize(800, 720);
			setLayout(new FlowLayout());
			setLocationRelativeTo(null);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});

			add(new Label("Input start & end coordinates for the line:"));

			add(new Label("x1"));
			TextField x1Field = new TextField(3);
			add(x1Field);

			add(new Label("y1"));
			TextField y1Field = new TextField(3);
			add(y1Field);

			add(new Label("x2"));
			TextField x2Field = new TextField(3);
			add(x2Field);

			add(new Label("y2"));
			TextField y2Field = new TextField(3);
			add(y2Field);

			Button submitButton = new Button("Draw");
			add(submitButton);
			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					x1 = Integer.parseInt(x1Field.getText());
					y1 = Integer.parseInt(y1Field.getText());
					x2 = Integer.parseInt(x2Field.getText());
					y2 = Integer.parseInt(y2Field.getText());
					repaint();
				}
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

			g2d.draw(new Line2D.Double(x1, y1, x2, y2));
		}
	}
}
