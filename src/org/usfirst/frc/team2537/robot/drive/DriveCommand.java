package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	long startTime = System.currentTimeMillis();
	
	public DriveCommand(){
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		Robot.driveSys.setLeftMotor(Robot.driveSys.getLeftJoystick());
		Robot.driveSys.setfrontLeftMotor(Robot.driveSys.getLeftJoystick());
		Robot.driveSys.setRightMotor(Robot.driveSys.getRightJoystick());
		Robot.driveSys.setfrontRightMotor(Robot.driveSys.getRightJoystick());
		
	
		
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;  
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		Robot.driveSys.setfrontLeftMotor(0);
		Robot.driveSys.setfrontRightMotor(0);
	}

	@Override
	protected void interrupted() {
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		Robot.driveSys.setfrontLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
	}

}
