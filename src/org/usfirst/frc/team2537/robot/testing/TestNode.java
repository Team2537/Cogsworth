package org.usfirst.frc.team2537.robot.testing;

import edu.wpi.first.wpilibj.buttons.Button;

public class TestNode {
	public TestCommand c;
	public Double time;
	public Button b;
	public String testName;
	
	public TestNode(TestCommand c, Button b, String testName) {
		this.c = c;
		this.time = null;
		this.b = b;
		this.testName = testName;
	}
	
	public TestNode(TestCommand c, Double time, String testName) {
		this.c = c;
		this.time = time;
		this.b = null;
		this.testName = testName;
	}
}
