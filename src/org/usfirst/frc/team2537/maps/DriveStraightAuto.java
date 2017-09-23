package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.DriveStraightCommand;
import org.usfirst.frc.team2537.robot.auto.UltrasonicDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveStraightAuto extends CommandGroup {
		public DriveStraightAuto() {
			this.addSequential(new DriveStraightCommand(45));
			this.addSequential(new WaitCommand(1));
			this.addSequential(new UltrasonicDrive());
		}

}
