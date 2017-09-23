package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.ChopDrive;
import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.shooter.ShooterClose;
import org.usfirst.frc.team2537.robot.shooter.ShooterCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BlueLowGoalDump extends CommandGroup {
	public BlueLowGoalDump() {
		this.addSequential(new ShooterCommand(), 3);
		this.addSequential(new ShooterClose());
		this.addSequential(new ChopDrive(-.6, -1, 2));
		this.addSequential(new DriveStraightCommand(-45));
	}
}
