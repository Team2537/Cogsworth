package org.usfirst.frc.team2537.robot.climber;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem {

	private Talon climberMotor1 = new Talon(Ports.CLIMBER_MOTOR_ONE); // creates motor for climber
	protected static final double SPEED_MULTIPLIER = 1; // Direct multiplier (set speed * this variable)
	protected static final double GROUND_AMPERAGE_THRESHOLD = 20;
	protected static final double AIR_AMPERAGE_CUTOFF = 40;
	
	public ClimberSubsystem() {
	}

	@Override
	public void initDefaultCommand() {
	}

	public void registerButtons() { 
		HumanInput.climberActivateButton.whenPressed(new ClimberSlowCommand());
		HumanInput.climberSlowActivateButton.whenPressed(new ClimberCommand());
		HumanInput.registerWhenPressedCommand(HumanInput.climberKillSwitch, new ClimberKillCommand());
	}

	/**
	 * Sets climber motor
	 * @param speed
	 */
	public void setClimberMotor(double speed) {
		climberMotor1.set(speed * SPEED_MULTIPLIER);
	}
}
