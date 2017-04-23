package org.usfirst.frc.team2537.robot.testing;

import org.usfirst.frc.team2537.robot.Robot;

public class RPiTest extends TestCommand {

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
	public String getSensor() {
		return "RPi Duty Cycle: " + Robot.piSys.getDutyCycle();
	}

}
