import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;

public class PolygonGenerator extends Frame {
	private TextField sidesField;
	private TextField lengthField;
	private Button submitButton;
	private int numberOfSides;
	private double sideLength;

	public PolygonGenerator() {
		setTitle("Regular Polygon");
		setSize(800, 800);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

		Label sidesLabel = new Label("Number of Sides:");
		sidesField = new TextField(5);
		Label lengthLabel = new Label("Side Length:");
		lengthField = new TextField(5);
		submitButton = new Button("Submit");

		add(sidesLabel);
		add(sidesField);
		add(lengthLabel);
		add(lengthField);
		add(submitButton);

		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numberOfSides = Integer.parseInt(sidesField.getText());
				sideLength = Double.parseDouble(lengthField.getText());
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (numberOfSides > 0 && sideLength > 0) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;
			double radius = (sideLength / (2 * Math.sin(Math.PI / numberOfSides)));
			Path2D path = new Path2D.Double();

			for (int i = 0; i < numberOfSides; i++) {
				double angle = 2 * Math.PI * i / numberOfSides;
				double x = centerX + radius * Math.cos(angle);
				double y = centerY + radius * Math.sin(angle);

				if (i == 0) {
					path.moveTo(x, y);
				} else {
					path.lineTo(x, y);
				}
			}

			path.closePath();
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(2));
			g2d.draw(path);
		}
	}

	public static void main(String[] args) {
		PolygonGenerator polygon = new PolygonGenerator();
		polygon.setVisible(true);
	}
}