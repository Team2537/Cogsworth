package org.usfirst.frc.team2537.robot;

//github.com/Team2537/Cogsworth.git
import org.usfirst.frc.team2537.robot.auto.AutoChooser;
import org.usfirst.frc.team2537.robot.auto.VisionRotate;
import org.usfirst.frc.team2537.robot.cameras.Cameras;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.input.HumanInput;
import org.usfirst.frc.team2537.robot.testing.*;
import org.usfirst.frc.team2537.robot.shooter.ShooterSubsystem;
import org.usfirst.frc.team2537.robot.vision.PISubsystem;
import org.usfirst.frc.team2537.robot.vision.RPiCalibration;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static DriveSubsystem driveSys;
	public static ClimberSubsystem climberSys;
	public static ShooterSubsystem shooterSys;
	public static PowerDistributionPanel pdp;
	public static Cameras cameras;
	public static PISubsystem piSys;
	
	private SendableChooser<Command> autoChooser;
	
	private TestFramework testSys;
	
	@Override
	public void robotInit() {
		driveSys = new DriveSubsystem();
		driveSys.initDefaultCommand();
		
		climberSys = new ClimberSubsystem();
		climberSys.registerButtons();
		
		cameras = new Cameras();
		cameras.start();
		
		shooterSys = new ShooterSubsystem();
		shooterSys.registerButtons();
		
		piSys = new PISubsystem();
		piSys.initDefaultCommand();
		
		pdp = new PowerDistributionPanel();
		
		autoChooser = new AutoChooser();
		SmartDashboard.putData("Auto Choices", autoChooser);
		SmartDashboard.putData("Reclibrate RPi", new RPiCalibration());
		SmartDashboard.putNumber("RPi Target Duty Cycle", VisionRotate.TARGET_DUTY_CYCLE);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().add(autoChooser.getSelected());
		driveSys.resetEncoders();
		driveSys.getAhrs().reset();
		System.out.println("Autonomous start");
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void autonomousPeriodic() {
		//System.out.println(Robot.driveSys.rencoder.getRaw());
		SmartDashboard.putNumber("RPi Current Duty Cycle", piSys.getDutyCycle());
		SmartDashboard.putNumber("NavX Angle", driveSys.getAhrs().getAngle());
		SmartDashboard.putNumber("Ultrasonic value: ", driveSys.ultraSanic.getRangeInches());
		Scheduler.getInstance().run();
	}


	/**
	 * 
	 */
	public void teleopInit(){
		Scheduler.getInstance().removeAll();
		System.out.println("Teleop init");

	}

	public void testInit() {
		testSys.finished = false;
		testSys.registerTestCommand(new WheelTest(true, false), 5000d, "Left Wheels Forwards Test");
		testSys.registerTestCommand(new WheelTest(false, false), 5000d, "Right Wheels Forwards Test");
		testSys.registerTestCommand(new WheelTest(true, true), 5000d, "Left Wheels Backwards Test");
		testSys.registerTestCommand(new WheelTest(false, true), 5000d, "Right Wheels Backwards Test");
		testSys.registerTestCommand(new ClimberTest(false), 5000d, "Slow Speed Climber Test");
		testSys.registerTestCommand(new ClimberTest(true), 5000d, "Fast Speed Backwards Test");
		testSys.registerTestCommand(new NavXTest(), HumanInput.cameraSwitchButton, "NavX Test");
		testSys.registerTestCommand(new RPiTest(), HumanInput.cameraSwitchButton, "RPi Test");
		testSys.registerTestCommand(new UltrasonicTest(), HumanInput.cameraSwitchButton, "Ultrasonic Test");
	}

	@Override
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		testSys.run();
		testSys.finish();
	}

	@Override
	public void disabledInit() {
		driveSys.getAhrs().reset();
		SmartDashboard.putNumber("RPi Current Duty Cycle", piSys.getDutyCycle());
		testSys.disabled();
	}
	
	@Override
	public void disabledPeriodic() {
		SmartDashboard.putNumber("RPi Current Duty Cycle", piSys.getDutyCycle());
		SmartDashboard.putNumber("RPi Target Duty Cycle", VisionRotate.TARGET_DUTY_CYCLE);
		SmartDashboard.putNumber("NavX Angle", driveSys.getAhrs().getAngle());
		SmartDashboard.putNumber("Ultrasonic value: ", driveSys.ultraSanic.getRangeInches());
		Scheduler.getInstance().run();
	}

}
