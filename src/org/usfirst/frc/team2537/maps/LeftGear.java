package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.Specs;
import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.RotateCommand;
import org.usfirst.frc.team2537.robot.auto.UltrasonicDrive;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftGear extends CommandGroup {
	public LeftGear() {
		addSequential(new DriveStraightCommand(70.5));
		addSequential(new RotateCommand(60));
		addSequential(new VisionRotate());
		addSequential(new UltrasonicDrive());
	}
}
