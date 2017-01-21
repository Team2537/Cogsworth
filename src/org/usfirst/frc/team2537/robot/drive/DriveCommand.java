package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	public int ultronRange = 10; //temp
	
	public DriveCommand(){
		requires(Robot.driveSys);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Drive Command Initiated");
		
	}

	protected void execute() {
		System.out.println(Robot.driveSys.getUltron());
		if (Robot.driveSys.getUltron() < ultronRange) {
			if (Robot.driveSys.getLeftJoystick(AxisType.kY) > 0) {
				Robot.driveSys.setLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
				Robot.driveSys.setfrontLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
			} else {
				Robot.driveSys.setLeftMotor(0);
				Robot.driveSys.setfrontLeftMotor(0);
			}
			if (Robot.driveSys.getRightJoystick(AxisType.kY) > 0) {
				Robot.driveSys.setRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));
				Robot.driveSys.setfrontRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));
			} else {
				Robot.driveSys.setRightMotor(0);
				Robot.driveSys.setfrontRightMotor(0);
			}
		} else {
			Robot.driveSys.setfrontLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
			Robot.driveSys.setLeftMotor(Robot.driveSys.getLeftJoystick(AxisType.kY));
			Robot.driveSys.setRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));
			Robot.driveSys.setfrontRightMotor(Robot.driveSys.getRightJoystick(AxisType.kY));	
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;  
	}
		
		

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		System.out.println("Drive command end");
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		Robot.driveSys.setfrontLeftMotor(0);
		Robot.driveSys.setfrontRightMotor(0);
		
	}

	@Override
	protected void interrupted() {
		System.out.println("Drive command interrupted");
		Robot.driveSys.setLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
		Robot.driveSys.setfrontLeftMotor(0);
		Robot.driveSys.setRightMotor(0);
	}

}
