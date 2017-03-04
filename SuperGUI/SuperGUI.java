import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Creates an interactive gui to plan robot actions
 *
 * Keybindings:
 * lclick - create robot
 * rclick - gear/shoot
 * space - stop turning to follow cursor, snap cursor to line
 * enter - print course
 * c - clear all points
 * bakaspace - remove latest point
 * s - snap to mode
 * esc - quit
 * 
 *
 * @author Arden Zhang
 *
 */
public class SuperGUI {

	public static final double FIELD_LENGTH = 54+1.0/3; // feet
	public static final double FIELD_WIDTH = 27; // feet
	public static final int SCALE = 20; // px/ft

	public static final double ROBOT_LENGTH = 33.0 / 12; // feet
	public static final double ROBOT_WIDTH = 29.0 / 12; // feet
	public static final double ROBOT_DIAMETER = Math.sqrt(Math.pow(ROBOT_LENGTH, 2) + Math.pow(ROBOT_WIDTH, 2));
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("SuperGUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);

		SuperPanel panel = new SuperPanel();

		frame.add(panel);
		frame.addKeyListener(panel);
		panel.requestFocusInWindow();

		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

}