package Lab_Practicals;

// Q4. Write a program to display a series of concentric circles of varying radius.

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class Q4 {

	public static void main(String[] args) {
		ThreeConcentricCircles circles = new ThreeConcentricCircles();
		circles.setTitle("Q4. To display a series of concentric circles of varying radius");
		circles.setVisible(true);
	}

	public static class ThreeConcentricCircles extends Frame {
		private int r1, r2, r3;

		public ThreeConcentricCircles() {
			setTitle("To draw 3 concentric circles of varying radius");
			setSize(800, 800);
			setLayout(new FlowLayout());
			setLocationRelativeTo(null);
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent we) {
					dispose();
				}
			});

			add(new Label("Input the radii for circles:"));

			add(new Label("Radius 1:"));
			TextField r1Field = new TextField(3);
			add(r1Field);

			add(new Label("Radius 2:"));
			TextField r2Field = new TextField(3);
			add(r2Field);

			add(new Label("Radius 3:"));
			TextField r3Field = new TextField(3);
			add(r3Field);

			Button submitButton = new Button("Draw");
			add(submitButton);
			submitButton.addActionListener(e -> {
				r1 = Integer.parseInt(r1Field.getText());
				r2 = Integer.parseInt(r2Field.getText());
				r3 = Integer.parseInt(r3Field.getText());
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

			g2d.draw(new Ellipse2D.Double(-r1, -r1, 2 * r1, 2 * r1));
			g2d.draw(new Ellipse2D.Double(-r2, -r2, 2 * r2, 2 * r2));
			g2d.draw(new Ellipse2D.Double(-r3, -r3, 2 * r3, 2 * r3));
		}
	}
}