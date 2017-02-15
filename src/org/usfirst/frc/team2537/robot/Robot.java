
package org.usfirst.frc.team2537.robot;

import org.usfirst.frc.team2537.robot.cameras.Cameras;
import org.usfirst.frc.team2537.robot.climber.ClimberSubsystem;
import org.usfirst.frc.team2537.robot.drive.DriveSubsystem;
import org.usfirst.frc.team2537.robot.shooter.ShooterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	public static Cameras cams;
	public static ClimberSubsystem climberSys;
	public static ShooterSubsystem shooterSys;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		driveSys = new DriveSubsystem();
		driveSys.initDefaultCommand();
		
		shooterSys = new ShooterSubsystem();
		shooterSys.registerButtons();
		
		climberSys = new ClimberSubsystem();
		climberSys.registerButtons();
		
		cams = new Cameras();
		cams.registerButtons();
	}

/*		new Thread(() -> {
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture("cam1", 1);
			camera1.setResolution(640, 480);

			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("cam1", 640, 480);

			Mat source = new Mat();
			Mat output = new Mat();

			while (!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				Imgproc.line(source, new Point(output.cols() / 2, 0), new Point(output.cols() / 2, output.rows()), new Scalar(255, 35, 0), 1);
				Imgproc.line(source, new Point(0, output.rows() / 2), new Point(output.cols(), output.rows() / 2), new Scalar(255, 35, 0), 1);
				outputStream.putFrame(source);  
			}
			
		}).start();*/
	 
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
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}
}                           
