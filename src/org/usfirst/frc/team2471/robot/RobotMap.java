package org.usfirst.frc.team2471.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	/* -------------------- Drive Train --------------------------------*/
	public static CANTalon lDrive1;
	public static CANTalon lDriveMiddle;
	public static CANTalon lDrive2;
	
	public static CANTalon rDrive1;
	public static CANTalon rDriveMiddle;
	public static CANTalon rDrive2;
	
	public static Solenoid shifter;
	
	public static Solenoid lPTO;
	public static Solenoid rPTO;

	public static int gear;
	
	
	
/*_______________________Sucker stuff__________________________________*/
	public static CANTalon ftop;
	public static CANTalon fbottom;
	
	public static Solenoid fextend;
	
	public static DigitalInput ltop;
	public static DigitalInput lbottom;
	
	public static void init (){
		
		//Drivetrain
		lDrive1 = new CANTalon(0); // TODO: get ids when electrical is ready
		lDriveMiddle = new CANTalon(1);
		lDrive2 = new CANTalon(2);
		
		rDrive1 = new CANTalon(3);
		rDriveMiddle = new CANTalon(4);
		rDrive2 = new CANTalon(5);
		
		shifter = new Solenoid(0);
		
		gear = 0;
		
		lPTO = new Solenoid(1);
		rPTO = new Solenoid(3);
		
		//Intake/Outtake
		fbottom = new CANTalon(7);
		ftop = new CANTalon(8);
		fextend = new Solenoid(2);
		ltop = new DigitalInput(0);
		lbottom = new DigitalInput(1);
		
	}
}
