package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem{
	
	public static CANTalon lDrive1;
	public static CANTalon lDriveMiddle;
	public static CANTalon lDrive2;
	
	public static CANTalon rDrive1;
	public static CANTalon rDriveMiddle;
	public static CANTalon rDrive2;
	
	public static Solenoid shifter;
	
	public static Solenoid lPTO;
	public static Solenoid rPTO;
	
	public static int gearAP;
	
	public Drive(){
		lDrive1 = RobotMap.lDrive1;
		lDriveMiddle = RobotMap.lDriveMiddle;
		lDrive2 = RobotMap.lDrive2;
		
		rDrive1 = RobotMap.rDrive1;
		rDriveMiddle = RobotMap.rDriveMiddle;
		rDrive2 = RobotMap.rDrive2;
		
		lPTO = RobotMap.lPTO;
		rPTO = RobotMap.rPTO;
		
		shifter = RobotMap.shifter;
		
		gearAP = RobotMap.gear;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveLoop());
	}
	
	public void driveplz(double x, double y){
		if (x <= .1 && x >= -.1){
			x = 0;
		}else if(y <= 0.1 && x >= -.1){
			y = 0;
		}
		
		if (((gearAP == 0 && x > .5)/* || (gearAP == 0 && x < -.5)*/)){
			gearAP++;
			shifter.set(true);
		}else if((gearAP == 1 && x < .5)/*|| (gearAP == 1 && x > -.5)*/){
			gearAP--;
			shifter.set(false);
		}
			
		SetSpeed(x, y);
	}
	
	private void SetSpeed(double forward, double right){
		
		lDrive1.set(-(forward - right));
		lDriveMiddle.set(-(forward - right));
		lDrive2.set(-(forward - right));
		
		rDrive1.set((forward + right));
		rDriveMiddle.set((forward + right));
		rDrive2.set((forward + right));
	}

}
