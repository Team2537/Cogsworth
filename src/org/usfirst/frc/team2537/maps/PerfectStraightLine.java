package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PerfectStraightLine extends CommandGroup {
	public PerfectStraightLine() {
		addSequential(new CourseCorrect(1000));
	}
}