
package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class CourseCorrect extends Command {

	private static final double DEFAULT_SPEED = 0.4;
	private static final double SLOWDOWN_START = 60;
	private static final double MINIMUM_SPEED = 0.05;
	private static final double CORRECTION_PROPORTION = 90; // it just worked, y'no?
	private static final double TOLERANCE = 1;
	private static final boolean debug = false;
	private double speed;
	private double startAngle;
	private double distance;
	private final double startSpeed;
	private AHRS ahrs = Robot.driveSys.getAhrs();
	public static final double DEFAULT_TIMEOUT = 3;

	/**
	 * Drives &lt;distance&gt; while correcting for angle
	 *
	 * @param distance
	 *            distance in inches
	 */
	public CourseCorrect(double distance) {
		this(distance, distance < 0 ? -DEFAULT_SPEED : DEFAULT_SPEED);
	}

	/**
	 * Drives &lt;distance&gt; at &lt;speed&gt;
	 *
	 * @param distance
	 *            Distance in inched
	 * @param speed
	 *            Speed in voltage percent
	 */
	public CourseCorrect(double distance, double speed) {
		this(distance, speed, DEFAULT_TIMEOUT);
	}

	public CourseCorrect(double distance, double speed, double timeout) {
		super(timeout);
		requires(Robot.driveSys);
		this.distance = distance;
		this.speed = speed;
		startSpeed = speed;
	}

	@Override
	protected void initialize() {
		startAngle = ahrs.getAngle();
		if (debug) System.out.println("CourseCorrect init: startAngle: " + startAngle);
		Robot.driveSys.resetEncoders();
		Robot.driveSys.setDriveMotors(speed);
	}

	@Override
	protected void execute() {
		double currentAngle = ahrs.getAngle();
		if (Math.abs(Math.abs(distance) - Math.abs(Robot.driveSys.getEncoderAverage())) < SLOWDOWN_START) {
			speed = Math.abs(Math.abs(distance) - Math.abs(Robot.driveSys.getEncoderAverage()))/SLOWDOWN_START*startSpeed + MINIMUM_SPEED ;
		}

		double left = speed;
		double right = speed;
		double correction = 0;

		double angleDiff = (currentAngle - startAngle)%360;
		if (debug) System.out.println("CourseCorrect exec: start: " + startAngle + "\tdiff: " + angleDiff);

		if (Math.abs(angleDiff) > TOLERANCE) correction = angleDiff / CORRECTION_PROPORTION;

		left += correction;
		right -= correction;

		Robot.driveSys.setDriveMotors(left, right);
	}

	@Override
	protected boolean isFinished() {
		//if (isTimedOut()) return true;
		return distance < 0 ? Robot.driveSys.getEncoderAverage() <= distance : Robot.driveSys.getEncoderAverage() >= distance;
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}
}
