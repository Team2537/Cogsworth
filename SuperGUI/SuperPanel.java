import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Panel
 *
 * @author Arden Zhang
 */
public class SuperPanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener, ActionListener {

	private static final int mouseSize = 8; // pixels
	private static final int toggleFollowCursorKey = KeyEvent.VK_SPACE;
	private static final int printCourseKey = KeyEvent.VK_ENTER;
	private static final int exitKey = KeyEvent.VK_ESCAPE;

	private Image field;
	private boolean followCursor;
	private Point mousePos;
	private SuperPoint startingPoint;
	private int botTransparency;
	private JFrame jframe;
	private SuperMenu menu;

	public SuperPanel() {
		field = new ImageIcon("SuperGUI\\FIELD.jpg").getImage();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		setPreferredSize(new Dimension((int) (SuperGUI.FIELD_LENGTH * SuperGUI.SCALE),
				(int) (SuperGUI.FIELD_WIDTH * SuperGUI.SCALE)));
		followCursor = true;
		mousePos = new Point(0, 0);
		botTransparency = 255;
		jframe = new JFrame();
		menu = new SuperMenu(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(field, 0, 0, null);

		if (startingPoint != null) {
			if (followCursor && !menu.isVisible()) startingPoint.point(mousePos);
			startingPoint.draw(g, botTransparency);
		}

		g.setColor(new Color(255, 255, 0));
		g.drawOval(mousePos.x - mouseSize, mousePos.y - mouseSize, mouseSize * 2, mouseSize * 2);
	}

	private void quit() {
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == toggleFollowCursorKey) followCursor = !followCursor;
		if (k.getKeyCode() == printCourseKey) {
			System.out.println("Course================" + startingPoint.getNumBots());
			String mapName = (String) JOptionPane.showInputDialog(jframe, "Enter map name:\n", "File Name",
					JOptionPane.PLAIN_MESSAGE, null, null, "");
			if (mapName != null) {
				File fl = new File("src\\org\\usfirst\\frc\\team2537\\maps\\" + mapName + ".java");
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(fl));
					writer.flush();
					writer.write("package org.usfirst.frc.team2537.maps;\n\n");
					writer.write("import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;\n");
					writer.write("import org.usfirst.frc.team2537.robot.auto.CourseCorrect;\n\n");
					writer.write("import edu.wpi.first.wpilibj.command.CommandGroup;\n\n");
					writer.write("public class " + mapName + " extends CommandGroup {\n");
					writer.write("\tpublic " + mapName + "() {\n");
					
					if(startingPoint.getPoint().x < SuperGUI.FIELD_LENGTH*SuperGUI.SCALE/2)
						SuperPrinter.printCourse(startingPoint, 0, writer);
					else
						SuperPrinter.printCourse(startingPoint, 180, writer);
					
					writer.write("\t}\n");
					writer.write("}\n");
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		if (k.getKeyCode() == exitKey) quit();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent k) {
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent m) {
		mousePos.x = m.getX();
		mousePos.y = m.getY();
		if (startingPoint != null && !followCursor) mousePos = snap(mousePos);
		repaint();
	}

	/**
	 * Snaps a point to the line that the SuperPoint is currently pointing to
	 *
	 * @param p
	 *            - point to snap to the angle of SuperPoint
	 * @return the point on the SuperPoint direction line closest to the inputted point
	 */
	private Point snap(Point p) {
		double slope = Math.tan(startingPoint.getFinalAngle()); // slope of
		// final point
		double invslope = -1 / slope; // slope of line perpendicular

		// y-intercept of perpendicular line
		double b_perp = startingPoint.getFinalPoint().y - p.y - invslope * (p.x - startingPoint.getFinalPoint().x); // of

		double x = (b_perp - 0) / (slope - invslope);
		double y = -slope * x + 0;
		return new Point((int) (x + startingPoint.getFinalPoint().x), (int) (y + startingPoint.getFinalPoint().y));
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		if (SwingUtilities.isRightMouseButton(m)) {
			menu.show(m.getComponent(), m.getX(), m.getY());
		} else {
			if (startingPoint == null)
				startingPoint = new SuperPoint(mousePos);
			else {
				startingPoint.add(mousePos);
				followCursor = true;
			}
		}
		repaint();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		botTransparency -= 10 * m.getPreciseWheelRotation();
		if (botTransparency > 255) botTransparency = 255;
		if (botTransparency < 0) botTransparency = 0;
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent k) {}

	@Override
	public void mousePressed(MouseEvent m) {}

	@Override
	public void mouseReleased(MouseEvent m) {}

	@Override
	public void mouseDragged(MouseEvent m) {
		mouseMoved(m);
	}

	@Override
	public void mouseEntered(MouseEvent m) {}

	@Override
	public void mouseExited(MouseEvent m) {}

	@Override
	public void actionPerformed(ActionEvent e){
		double angle = Math.atan2(startingPoint.getFinalPoint().y - mousePos.y, mousePos.x - startingPoint.getFinalPoint().x);
		switch(e.getActionCommand()){
		case "GEAR": startingPoint.addAction(new SuperAction(SuperEnum.GEAR, angle)); break;
		case "SHOOT": startingPoint.addAction(new SuperAction(SuperEnum.SHOOT, angle)); break;
		default: System.out.println("Right click command not found");
		}
	}
}
