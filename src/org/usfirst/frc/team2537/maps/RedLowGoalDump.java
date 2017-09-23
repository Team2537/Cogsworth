package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.RotateCommand;
import org.usfirst.frc.team2537.robot.shooter.ShooterClose;
import org.usfirst.frc.team2537.robot.shooter.ShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RedLowGoalDump extends CommandGroup {
	public RedLowGoalDump() {
		this.addSequential(new DriveStraightCommand(9));
		this.addSequential(new ShooterCommand(), 3);
		this.addSequential(new ShooterClose());
		this.addSequential(new RotateCommand(-45));
		this.addSequential(new DriveStraightCommand(45));
	}
}
