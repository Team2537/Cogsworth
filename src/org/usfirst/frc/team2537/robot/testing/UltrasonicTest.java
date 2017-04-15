package org.usfirst.frc.team2537.robot.testing;

import org.usfirst.frc.team2537.robot.Robot;

public class UltrasonicTest extends TestCommand {

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
		return Robot.driveSys.ultraSanic.getRangeInches();
	}

}
