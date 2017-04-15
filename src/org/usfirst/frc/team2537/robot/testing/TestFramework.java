package org.usfirst.frc.team2537.robot.testing;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import edu.wpi.first.wpilibj.buttons.Button;

public class TestFramework {
	
	private Socket statusSocket;
	private PrintWriter statusWriter;
	
	LinkedList<TestNode> testCommands = new LinkedList<TestNode>();
	
	public void initTestFramework(String SPSHostName, int SPSPortNum) {
		try {
			statusSocket = new Socket(SPSHostName, SPSPortNum);
		} catch (UnknownHostException e) {
			System.out.println("Smart pit system hostname not found!");
		} catch (IOException e) {
			System.out.println("Error connecting to the smart pit system");
		}
		try {
			statusWriter = new PrintWriter(statusSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace(); // This should never happen but
		}
	}
	
	public void registerTestCommand(TestCommand c, Button b, String testName) {
		testCommands.add(new TestNode(c, b, testName));
	}
	
	public void registerTestCommand(TestCommand c, Double time, String testName) {
		testCommands.add(new TestNode(c, time, testName));
	}
	
	private void sendMessage(String testName, Double sensorVal) {
		statusWriter.println("[" + sensorVal == null ? "1" : "2"  + "]" + 
				testName + 
				":" + sensorVal == null ? sensorVal : "" );
	}
	
	public void run() {
		for (TestNode tn : testCommands) {
			tn.c.initialize();
			if (tn.time != null) {
				double startTime = System.currentTimeMillis();
				while (System.currentTimeMillis() < startTime + tn.time * 1000) {
					tn.c.execute();
					sendMessage(tn.testName, tn.c.getSensor());
				}
				tn.c.end();
			} else if (tn.b != null) {
				while (!tn.b.get()) {
					tn.c.execute();
					sendMessage(tn.testName, tn.c.getSensor());
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
		try {
			statusSocket.close();
		} catch (IOException e) {
			e.printStackTrace(); // This shouldn't really happen either but lol
		}
		statusWriter.close();
	}
}
