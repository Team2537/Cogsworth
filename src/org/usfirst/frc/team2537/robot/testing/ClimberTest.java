package org.usfirst.frc.team2537.robot.testing;

import org.usfirst.frc.team2537.robot.Robot;

public class ClimberTest extends TestCommand {
	
	private double speed;

	public ClimberTest(boolean fast) {
		speed = fast ? -1.0 : -0.8;
	}
	
	@Override
	public void initialize() {
		requires(Robot.climberSys);
	}

	@Override
	public void execute() {
		Robot.climberSys.setClimberMotor(speed);
	}

	@Override
	public void end() {
		Robot.climberSys.setClimberMotor(0);
	}

	@Override
	public String getSensor() {
		return "No Sensor";
	}

}
