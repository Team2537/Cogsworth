package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.maps.BlueLowGoalDump;
import org.usfirst.frc.team2537.maps.DriveStraightAuto;
import org.usfirst.frc.team2537.maps.LeftGear;
import org.usfirst.frc.team2537.maps.MiddleGear;
import org.usfirst.frc.team2537.maps.RedLowGoalDump;
import org.usfirst.frc.team2537.maps.RightGear;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	public AutoChooser() {
		addDefault("Middle Gear", new MiddleGear());
		addObject("Left Gear", new LeftGear());
		addObject("Right Gear", new RightGear());
		addObject("Rotate Test", new RotateCommand(-60));
		addObject("Red LOW GOAL", new RedLowGoalDump());
		addObject("Blue LOW GOAL", new BlueLowGoalDump());
		addObject("Drive Straight Command", new DriveStraightAuto());
//		addObject("Vision test", new VisionRotate());
	}
}