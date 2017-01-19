package org.usfirst.frc.team2537.robot.input;

import org.usfirst.frc.team2537.robot.Ports;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class HumanInput {

	public static Joystick leftJoystick = new Joystick(Ports.LEFT_JOYSTICK);
	public static Joystick rightJoystick = new Joystick(Ports.RIGHT_JOYSTICK);
	public static Joystick xboxController = new Joystick(Ports.XBOX);
	public static Button shooterFire = new JoystickButton(xboxController, Ports.FIRE_BUTTON);
	public static Button driveModeButton = new JoystickButton(rightJoystick, Ports.DRIVE_SWITCH_BUTTON);
	public static Button driveUltrasonicButton = new JoystickButton(rightJoystick, Ports.DRIVE_ULTRASONIC_BUTTON);
	public static Button limitSwitchButton = new JoystickButton(rightJoystick, Ports.LIMIT_SWITCH_ACTIVATOR);
	public static Button limitSwitchOffButton = new JoystickButton(rightJoystick, Ports.LIMIT_SWITCH_MODE_OFF);
	public static Button shooterOff = new JoystickButton(xboxController, Ports.STOP_FIRING_BUTTON);
	public static Button manShooter = new JoystickButton(xboxController, Ports.MAN_SHOOTER);
	public static void registerWhenPressedCommand(Button b, Command c){
		b.whenPressed(c);
	}
	
}
