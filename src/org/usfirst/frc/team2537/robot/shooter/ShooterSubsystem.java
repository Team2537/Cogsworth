package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	private Talon shooterMotor;
	//private Talon shooterMotor = new Talon(Ports.SHOOTER_MOTOR);
	public ShooterSubsystem(){
		 shooterMotor = new Talon(Ports.SHOOTER_MOTOR);
	}
	@Override
	protected void initDefaultCommand() {
	}
	
	public void setShooterLocked() {
		shooterMotor.set(-1);
	}
	
	public void setShooterReleased(){
		shooterMotor.set(1);
	}
	
	public void setShooterNeutral(){
		shooterMotor.set(0);
	}
	
	public void registerButtons() {
		HumanInput.registerWhenPressedCommand(HumanInput.shooterBallRelease, new ShooterCommand());
		HumanInput.shooterBallRelease.whenReleased(new ShooterClose());
	}

	

}
