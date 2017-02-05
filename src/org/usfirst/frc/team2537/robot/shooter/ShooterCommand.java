package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	private boolean shooterOff;
	private long startTime;
	/**
	 * constructor that requires Robot.shooterSys
	 * 
	 * @param shooterOff
	 *            boolean: true = shooter motors are off, false = shooter motors
	 *            are on
	 */
	public ShooterCommand(boolean shooterOff) {
		requires(Robot.shooterSys);
		this.shooterOff = shooterOff;
	}

	/**
	 * turns shooter motors on if shooter on button is pressed, or turning them
	 * off if shooter off button is pressed
	 */
	@Override
	protected void initialize() {
		startTime = System.currentTimeMillis();
		
		
		
		if (shooterOff) {
			Robot.shooterSys.flyOff(); //TODO: Repeatability test
		} else {
			Robot.shooterSys.activateFastMotor();
		}
	}

	@Override
	protected void execute() {
		if (System.currentTimeMillis() - 1500 > startTime){
			Robot.shooterSys.activateSlowMotor();
		} /* else if (Robot.shooterSys.getFastVelocity > whateverSpeed) {
			Robot.shooterSys.acivateSlowMotor();
		}
		//Above code also to be used if using an encoder
		
		
		/*
		 * if (shooterOn && Robot.shooterSys.getLimitSwitch()) { if
		 * (Robot.shooterSys.UltronRange() > ShooterSubsystem.DISTANCE_TO_BOILER
		 * - ShooterSubsystem.LEEWAY && Robot.shooterSys.UltronRange() <
		 * ShooterSubsystem.DISTANCE_TO_BOILER + ShooterSubsystem.LEEWAY) {
		 * Robot.shooterSys.FlyOn(); } }
		 */
	}

	/**
	 * finishes immediately because it only has function in initialize()
	 * @return
	 */
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	/**
	 * turns flywheels off if interrupted
	 */
	@Override
	protected void interrupted() {
		Robot.shooterSys.flyOff();
	}

}
