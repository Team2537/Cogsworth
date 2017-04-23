package org.usfirst.frc.team2537.robot.testing;

import org.usfirst.frc.team2537.robot.Robot;

public class WheelTest extends TestCommand {

	private boolean left;
	private boolean reversed;
	
	public WheelTest(boolean left, boolean reversed) {
		this.left = left;
		this.reversed = reversed;
	}
	
	@Override
	public void initialize() {
		requires(Robot.driveSys);
	}

	@Override
	public void execute() {
		if (left) {
			Robot.driveSys.setLeftDriveMotors(reversed ? -1 : 1);
		} else {
			Robot.driveSys.setRightDriveMotors(reversed ? 1 : -1);
		}
	}

	@Override
	public void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	public String getSensor() {
		return left ? "Left encoder: " + Robot.driveSys.getLeftEncoders() : "Right encoder: " + Robot.driveSys.getRightEncoders();
	}

}
