package org.usfirst.frc.team2537.robot.testing;

public abstract class TestCommand {
	public abstract void initialize();
	
	public abstract void execute();
	
	public abstract void end();
	
	public abstract Double getSensor();
}
