package org.usfirst.frc.team2471.robot;

import org.usfirst.frc.team2471.robot.commands.SpitDown;
import org.usfirst.frc.team2471.robot.commands.SpitMiddeUp;
import org.usfirst.frc.team2471.robot.commands.SpitUpReverse;
import org.usfirst.frc.team2471.robot.commands.SpitUp;
import org.usfirst.frc.team2471.robot.commands.SuckUp;
import org.usfirst.frc.team2471.robot.commands.BunnyGrab;
import org.usfirst.frc.team2471.robot.commands.BunnyRelease;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public Joystick driverStick;
	public Joystick coStick;
	public JoystickButton shift;
	public JoystickButton suckMe;
	public JoystickButton sTop;
	public JoystickButton sMid;
	public JoystickButton sBot;
	public JoystickButton bunnyGrab;
	public JoystickButton bunnyRelease;
	public JoystickButton spitMiddleUp;
	
	public OI(){
		driverStick = new Joystick(0);
		coStick = new Joystick(1);
		
		shift = new JoystickButton(driverStick, 1);
		suckMe = new JoystickButton(coStick, 1);
		sTop = new JoystickButton(coStick, 2);
		sMid = new JoystickButton(coStick, 4);
		sBot = new JoystickButton(coStick, 3);
		bunnyGrab = new JoystickButton (coStick, 5);
		bunnyRelease = new JoystickButton (coStick, 6);		
		spitMiddleUp = new JoystickButton(coStick, 7);
		
		suckMe.whileHeld(new SuckUp());
		sTop.whileHeld(new SpitUp());
		sMid.whileHeld(new SpitUpReverse());
		sBot.whileHeld(new SpitDown());
		spitMiddleUp.whileHeld(new SpitMiddeUp());
		bunnyGrab.whenPressed(new BunnyGrab());
		bunnyRelease.whenPressed(new BunnyRelease());
		// example shift.whenPressed(new DriveLoop());
	}
}

