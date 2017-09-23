package org.usfirst.frc.team2537.robot.auto;

import org.usfirst.frc.team2537.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class VisionRotate extends Command {
	/* TODO: when someone has the time and energy, make a function so that voltage determines the min and max speed:
	 * if the voltage is too low, the bot will not turn
	 * if the voltage is too high, the bot will overshoot
	 * therefore, instead of making a single constant, using a function would handle battery drainage situations
	 * 
	 * this is not so much a problem at competitions because we should be using a new battery every match,
	 * but for testing, we cannot keep the battery fresh to find an optimal speed constant.
	 */
	private double voltage;
	
	/* when the pi cannot see the target, we spin faster to try to find the target */
	private static final double FAST_SPEED = 0.75;
	/* we spin slower when the pi can see the target so that we do not overshoot */
	
	/* worlds value: 0.58 */
	private static final double SLOW_SPEED = 0.52;
	/* if the pi does not see a target, it outputs this duty cycle or less when right side up.  When upside down, it outputs 1 - @code{NO_TARGET_DUTY_CYCLE} */
	private static final double NO_TARGET_DUTY_CYCLE = 0.985;
	/* we are perfectly centered at @code{TARGET_DUTY_CYCLE}.  When mounted right side up, duty cycles greater than
	 * @code{TARGET_DUTY_CYCLE} represents a target to the right of the center of the camera, with offset given by the (the duty cycle - @code{TARGET_DUTY_CYCLE})
	 * likewise, duty cycles less than @code{TARGET_DUTY_CYCLE} represents a target to the left of the center of the camera.
	 * 
	 * the greater the distance of the target is from the center of the camera, the duty cycles gets farther from the @code{TARGET_DUTY_CYCLE}
	 */
	/*updated 9/19/2017: 0.56 -> 0.46 */
	/*updated 9/19/2017: 0.46 -> 0.62 */
	/*updated 9/19/2017: 0.62 -> 0.50 */
	/*updated 9/19/2017: 0.50 -> 0.56 */
	/*updated 9/19/2017: 0.56 -> 0.53 */
	/*updated 9/19/2017: 0.53 -> 0.56 */
	public static double TARGET_DUTY_CYCLE = 0.56;
	/* we stop this command when the pi duty cycle is within @code{TARGET_DUTY_CYCLE} +/- @code{DUTY_CYCLE_TOLERANCE} */
	private static final double DUTY_CYCLE_TOLERANCE = 0.02;
		
//	private static final double KICK_BACK_DUTY_CYCLE = 0.00;
	
	public VisionRotate(){
		requires(Robot.driveSys);
	}

	@Override
	protected void initialize() {
//		double deltaDutyCycle = Robot.piSys.getDutyCycle() - TARGET_DUTY_CYCLE;
//		if(Math.abs(deltaDutyCycle) > DUTY_CYCLE_TOLERANCE){
//			TARGET_DUTY_CYCLE += (deltaDutyCycle < 0) ? KICK_BACK_DUTY_CYCLE : -KICK_BACK_DUTY_CYCLE;
//		}
		System.out.println("[VisionRotate] Target Duty Cycle: " + TARGET_DUTY_CYCLE);
	}

	@Override
	protected void execute() {
		/* if the pi is not connected or there is a malfunction, we receive infinity, so we end this command */
		if(Robot.piSys.getDutyCycle() == Double.POSITIVE_INFINITY){
			this.cancel();
		}
		boolean targetInSight = (Robot.piSys.getDutyCycle() < NO_TARGET_DUTY_CYCLE);
		
		/* if we cannot see the target, we spin faster */
		if(!targetInSight){
			Robot.driveSys.setDriveMotors(FAST_SPEED, -FAST_SPEED);
		} else{
			double deltaDutyCycle = Robot.piSys.getDutyCycle() - TARGET_DUTY_CYCLE;
			if(deltaDutyCycle > 0){
				Robot.driveSys.setDriveMotors(SLOW_SPEED, -SLOW_SPEED);
			} else {
				Robot.driveSys.setDriveMotors(-SLOW_SPEED, SLOW_SPEED);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		System.out.println("[VisionRotate] duty cycle: " + Robot.piSys.getDutyCycle());
		return (Math.abs(Robot.piSys.getDutyCycle() - TARGET_DUTY_CYCLE) <= DUTY_CYCLE_TOLERANCE);
	}

	@Override
	protected void end() {
		Robot.driveSys.setDriveMotors(0);
	}

	@Override
	protected void interrupted() {
		System.out.println("disabled");
		Robot.driveSys.setDriveMotors(0);
	}
}
