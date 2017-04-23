package org.usfirst.frc.team2537.robot.testing;

import edu.wpi.first.wpilibj.command.Command;

public abstract class TestCommand extends Command {
	public abstract void initialize();
	
	public abstract void execute();
	
	public abstract void end();
	
	public abstract String getSensor();
	
	public boolean isFinished() {
		return true;
	}
	
	public void interrupted() {
		
	}
}
