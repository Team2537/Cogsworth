package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.maps.DriveForward;
import org.usfirst.frc.team2537.maps.Left;
import org.usfirst.frc.team2537.maps.MidRedLowGear;
import org.usfirst.frc.team2537.maps.PerfectStraightLine;
import org.usfirst.frc.team2537.maps.Right;
import org.usfirst.frc.team2537.maps.SimpleGearPlacement;
import org.usfirst.frc.team2537.maps.Square;
import org.usfirst.frc.team2537.maps.Straight;
import org.usfirst.frc.team2537.maps.UltrasonicTest;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoChooser extends SendableChooser<Command> {
	public AutoChooser() {
		addDefault("DriveForward", new DriveForward());
		addObject("Left", new Left());
		addObject("MidRedLowGear", new MidRedLowGear());
		addObject("PerfectStraightLine", new PerfectStraightLine());
		addObject("Right", new Right());
		addObject("SimpleGearPlacement", new SimpleGearPlacement());
		addObject("Square", new Square());
		addObject("Straight", new Straight());
		addObject("UltrasonicTest", new UltrasonicTest());
	}
}