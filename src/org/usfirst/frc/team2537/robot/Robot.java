
package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.cameras.Cameras;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.shooter.ShooterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveSys = new DriveSubsystem();
		driveSys.initDefaultCommand();
		
		climberSys = new ClimberSubsystem();
		climberSys.registerButtons();

//		cams = new Cameras();

		shooterSys = new ShooterSubsystem();
		shooterSys.registerButtons();
		
		pdp = new PowerDistributionPanel();
		
		cameras = new Cameras();
		cameras.start();
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	 
	/**
	 * This autonomous (along with the chooser code above) shows how to select  
	 * between different autonomous modes using the dashboard. The sendable 
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (cameras.lastRan + 3000 < System.currentTimeMillis() && !cameras.switchTried) {
			System.out.println("ded");
			cameras.switchTried = true;
			cameras.switchCameras();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}                           
