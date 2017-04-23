package org.usfirst.frc.team2537.robot.testing;

import java.util.LinkedList;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class TestFramework {
	
	NetworkTable table;
	
	LinkedList<TestNode> testCommands = new LinkedList<TestNode>();
	
	public boolean finished = false;
	
	public TestFramework() {
		table = NetworkTable.getTable("SmartPitSystem");
		table.putString("Test Name", "Connected");
		table.putString("Sensor Value", "Connected");
		table.putString("Remaining", "Connected");
	}
	
	public void registerTestCommand(TestCommand c, Button b, String testName) {
		if (finished) return;
		testCommands.add(new TestNode(c, b, testName));
	}
	
	public void registerTestCommand(TestCommand c, Double time, String testName) {
		if (finished) return;
		testCommands.add(new TestNode(c, time, testName));
	}
	
	private void sendMessage(String testName, String sensorVal, String remainingCondition) {
		table.putString("Test Name", testName);
		table.putString("Sensor Value", sensorVal);
		table.putString("Remaining", remainingCondition);
		if (finished) return;

	}
	
	public void run() {
		if (finished) return;
		for (TestNode tn : testCommands) {
			tn.c.initialize();
			if (tn.time != null) {
				double startTime = System.currentTimeMillis();
				while (System.currentTimeMillis() < startTime + tn.time * 1000) {
					tn.c.execute();
					sendMessage(tn.testName, tn.c.getSensor(), "Time remaining: " + Double.toString(((startTime + tn.time * 1000) - System.currentTimeMillis())/1000));
				}
				tn.c.end();
			} else if (tn.b != null) {
				while (!tn.b.get()) {
					tn.c.execute();
					sendMessage(tn.testName, tn.c.getSensor(), "Press button to continue");
				}
				tn.c.end();
			} else {
				throw new IllegalArgumentException("Test Command " + tn.testName + " has neither a time limit or a finish button.");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void finish() {
		table.putString("Test Name", "Testing finished!");
		table.putString("Sensor Value", "No Sensor");
		table.putString("Remaining", "Testing finished!");
		finished = true;
	}
	
	public void disabled() {
		table.putString("Test Name", "Connected");
		table.putString("Sensor Value", "Connected");
		table.putString("Remaining", "Connected");
	}
}
