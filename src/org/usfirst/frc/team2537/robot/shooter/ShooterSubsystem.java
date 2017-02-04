package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	public static final double FAST_SPEED = 1;
	public static final double SLOW_SPEED = .5;
	public static final int LEEWAY = 1;
	public static final int DISTANCE_TO_BOILER = 10;
	private CANTalon slowMotor = new CANTalon(Ports.SLOW_SHOOTER);
	private CANTalon fastMotor = new CANTalon(Ports.FAST_SHOOTER);

	public ShooterSubsystem() {

	}

	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOnButton, new ShooterCommand(false));
		HumanInput.registerWhenPressedCommand(HumanInput.shooterOffButton, new ShooterCommand(true));
	}

	@Override
	protected void initDefaultCommand() {

	}

	/**
	 * sets both flywheels to their respective speeds
	 */
	public void flyOn() {
		slowMotor.set(SLOW_SPEED);
		fastMotor.set(FAST_SPEED);
	}

	/**
	 * turns both flywheels off
	 */
	public void flyOff() {
		slowMotor.set(0);
		fastMotor.set(0);
	}
	
}
