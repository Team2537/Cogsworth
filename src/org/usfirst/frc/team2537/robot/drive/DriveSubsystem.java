package org.usfirst.frc.team2537.robot.drive;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {

	private CANTalon leftMotor = new CANTalon(Ports.LEFT_MOTOR);
	private CANTalon rightMotor = new CANTalon(Ports.RIGHT_MOTOR);
	private CANTalon frontLeftMotor = new CANTalon(Ports.FRONT_LEFT_MOTOR);
	private CANTalon frontRightMotor = new CANTalon(Ports.FRONT_RIGHT_MOTOR);
	private static final double DEADZONE_THRESHOLD = 0.1;
	protected static final double SPEED_MULTIPLIER = 1;

	public DriveSubsystem() {

	}

	DigitalInput limitSwitch = new DigitalInput(Ports.LIMIT_SWITCH_BUTTON);

	@Override
	public void initDefaultCommand() {
		DriveCommand dc = new DriveCommand();
		this.setDefaultCommand(dc);
	}

	public void registerButtons() {
	}

	/**
	 * Set left motor to speed
	 * 
	 * @param speed
	 * 
	 */
	public void setLeftMotor(double speed) {
		leftMotor.set(-speed * SPEED_MULTIPLIER);
	}

	/**
	 * Set right motor to speed
	 * 
	 * @param speed
	 */
	public void setRightMotor(double speed) {
		rightMotor.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * Set front left motor to speed
	 * 
	 * @param speed
	 * 
	 */
	public void setfrontLeftMotor(double speed) {
		frontLeftMotor.set(-speed * SPEED_MULTIPLIER);
	}

	/**
	 * Set front left motor to speed
	 * 
	 * @param speed
	 * 
	 */
	public void setfrontRightMotor(double speed) {
		frontRightMotor.set(speed * SPEED_MULTIPLIER);
	}

	/**
	 * Gets value based on direction left joy stick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getLeftJoystick(AxisType axis) {
		double leftJoystickValue = HumanInput.leftJoystick.getAxis(axis);
		if (Math.abs(leftJoystickValue) > DEADZONE_THRESHOLD)
			return leftJoystickValue;
		else
			return 0;
	}

	/**
	 * Gets value based on direction right joy stick is pressed
	 * 
	 * @param axis
	 * @return
	 */
	public double getRightJoystick(AxisType axis) {
		double rightJoystickValue = HumanInput.rightJoystick.getAxis(axis);
		if (Math.abs(rightJoystickValue) > DEADZONE_THRESHOLD)
			return rightJoystickValue;
		else
			return 0;
	}

	Encoder leftEncoder = new Encoder(3, 4, false, Encoder.EncodingType.k4X);
	int lCount = leftEncoder.get();
	double lDistance = leftEncoder.getRaw();
	double lRawCount = leftEncoder.getDistance();
	double lRate = leftEncoder.getRate();
	boolean lDirection = leftEncoder.getDirection();
	boolean lStopped = leftEncoder.getStopped();
	
	Encoder rightEncoder = new Encoder(5, 6, false, Encoder.EncodingType.k4X);
	int rCount = rightEncoder.get();
	double rDistance = rightEncoder.getRaw();
	double rRawCount = rightEncoder.getDistance();
	double rRate = rightEncoder.getRate();
	boolean rDirection = rightEncoder.getDirection();
	boolean rStopped = rightEncoder.getStopped();
	
	public double getLeftEncoderDouble() {
		return lDistance;
		return lRawCount;
		return lRate;
	}
	
	public int getLeftEncoderInt(){
		return lCount;
	}
	
	public boolean getLeftEncoderBoolean(){
		return lDirection;
		return lStopped;
	}
	
	public double getRightEncoderDouble(){
		return rDistance;
		return rRawCount;
		return rRate ;
		
	}
	
	public int getRightEncoderInt(){
		return rCount;
		
	}
	
	public boolean getRightEncoderBoolean(){
		return rDirection;
		return rStopped;
	}
	
		
		
}
