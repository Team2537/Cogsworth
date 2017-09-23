package org.usfirst.frc.team2537.robot.shooter;

import org.usfirst.frc.team2537.robot.Ports;
import org.usfirst.frc.team2537.robot.Robot;
import org.usfirst.frc.team2537.robot.climber.ClimbStatus;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;
import org.usfirst.frc.team2537.robot.input.HumanInput;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {
	
	
	public ShooterCommand() {
		requires(Robot.shooterSys);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.shooterSys.setShooterReleased();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooterSys.setShooterNeutral();
	}

	@Override
	protected void interrupted() {
		Robot.shooterSys.setShooterNeutral();
	}

}
