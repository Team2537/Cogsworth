package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	private Talon backLeftMotor = new Talon(Ports.BACK_LEFT_MOTOR);
	private Talon backRightMotor = new Talon(Ports.BACK_RIGHT_MOTOR);
	private CANTalon frontLeftMotor = new CANTalon(Ports.FRONT_LEFT_MOTOR);
	private CANTalon frontRightMotor = new CANTalon(Ports.FRONT_RIGHT_MOTOR);

	private static final double DEADZONE_THRESHOLD = 0.1; // Unless joystick is beyond this threshold, don't move
	protected static final double SPEED_MULTIPLIER = 1; // Speed  * multiplier = output speed

	private Ultrasonic ultrasonic = new Ultrasonic(Ports.ULTRASONIC_TRIGGER, Ports.ULTRASONIC_ECHO);

	public DriveSubsystem() {
		ultrasonic.setAutomaticMode(true); // Automagically get ultrasonic readings
	}

	/**
	 * Sets drive command as default command
	 */
	@Override
	public void initDefaultCommand() {
		DriveCommand dc = new DriveCommand();
		this.setDefaultCommand(dc);
	}

	/**
	 * Set left motor to speed; inverted due to wiring
	 * 
	 * @param speed
	 */
	public void setLeftMotors(double speed) {
		backLeftMotor.set(-speed * SPEED_MULTIPLIER);
		frontLeftMotor.set(-speed * SPEED_MULTIPLIER);
	}

	/**
	 * Set right motor to speed
	 * 
	 * @param speed
	 */
	public void setRightMotors(double speed) {
		backRightMotor.set(speed * SPEED_MULTIPLIER);
		frontRightMotor.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * Set both motors to two speeds
	 * @param leftSpeed
	 * @param rightSpeed
	 */
	public void setMotors(double leftSpeed, double rightSpeed) {
		setLeftMotors(leftSpeed);
		setRightMotors(rightSpeed);
	}

	/**
	 * Set both motors to speed
	 * 
	 * @param speed
	 */
	public void setMotors(double speed) {
		setMotors(speed, speed);
	}
	
	/**
	 * Gets value based on direction left joystick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getLeftJoystick() {
		double leftJoystickValue = HumanInput.leftJoystick.getAxis(AxisType.kY);
		if (Math.abs(leftJoystickValue) > DEADZONE_THRESHOLD)
			return leftJoystickValue;
		else
			return 0;
	}

	/**
	 * Gets value based on direction right joystick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getRightJoystick() {
		double rightJoystickValue = HumanInput.rightJoystick.getAxis(AxisType.kY);

		if (Math.abs(rightJoystickValue) > DEADZONE_THRESHOLD)
			return rightJoystickValue;

		return 0;
	}

	/**
	 * Gets ultrasonic range
	 * @return Ultrasonic range in inches
	 */
	public double getUltrasonic() {
		return ultrasonic.getRangeInches();
	}
}