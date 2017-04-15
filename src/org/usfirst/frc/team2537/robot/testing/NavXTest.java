package org.usfirst.frc.team2537.robot.testing;

import org.usfirst.frc.team2537.robot.Robot;

public class NavXTest extends TestCommand {

	@Override
	public void initialize() {

	}

	@Override
	public void execute() {

	}

	@Override
	public void end() {

	}

	@Override
	public Double getSensor() {
		double angle = Robot.driveSys.getAhrs().getAngle();
		if(angle > 180){
    		angle -= 360;
    	} else if (angle < -180) {
    		angle += 360;
    	}
		return angle;
	}
}
