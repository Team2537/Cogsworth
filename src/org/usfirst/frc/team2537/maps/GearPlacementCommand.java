package org.usfirst.frc.team2537.maps;

import org.usfirst.frc.team2537.robot.auto.AutoRotateCommand;
import org.usfirst.frc.team2537.robot.auto.CourseCorrect;
import org.usfirst.frc.team2537.robot.auto.UltraSonicCourseCorrect;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearPlacementCommand extends CommandGroup {
	public GearPlacementCommand(){
		addSequential(new CourseCorrect(55.00));
		addSequential(new AutoRotateCommand(60.00));
		addSequential(new UltraSonicCourseCorrect());
	}
}
