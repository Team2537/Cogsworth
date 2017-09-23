package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ChopDrive extends Command {
	
	double leftSpeed;
	double rightSpeed;
	
	public ChopDrive(double leftSpeed, double rightSpeed, double timeout) {
		super(timeout);
		this.leftSpeed = leftSpeed;
		this.rightSpeed = rightSpeed;
	}
	
	protected void initialize() {
		Robot.driveSys.setDriveMotors(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isTimedOut();
	}
	
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}
	
	protected void interrupted() {
		Robot.driveSys.setDriveMotors(0);
	}
	
}
