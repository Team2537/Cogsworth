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
	
	public void initTestFramework(String SPSHostName, int SPSPortNum) throws UnknownHostException, IOException {
		statusSocket = new Socket(SPSHostName, SPSPortNum);
		statusWriter = new PrintWriter(statusSocket.getOutputStream(), true);
	}
	
	public void registerTestCommand(TestCommand c, Double time, Button b, String testName) {
		testCommands.add(new TestNode(c, time, b, testName));
	}
	
	private void sendMessage(String testName, Double sensorVal) {
		statusWriter.println("[" + sensorVal == null ? "2" : "3"  + "]" + testName + ":" + sensorVal == null ? sensorVal : "");
	}
	
	public void run() throws IllegalArgumentException, InterruptedException {
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
			Thread.sleep(1000);
		}
	}
	
	public void finish() throws IOException {
		statusSocket.close();
		statusWriter.close();
	}
}
