package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem{
	
	public static CANTalon lDrive1;
	public static CANTalon lDrive2;
	public static CANTalon lDrive3;
	
	public static CANTalon rDrive1;
	public static CANTalon rDrive2;
	public static CANTalon rDrive3;
	
	public static Solenoid lShifter;
	public static Solenoid lPTO;
	
	public static Solenoid rShifter;
	public static Solenoid rPTO;
	
	public static int gearAP;
	
	public Drive(){
		lDrive1 = RobotMap.lDrive1;
		lDrive2 = RobotMap.lDrive2;
		lDrive3 = RobotMap.lDrive3;
		
		rDrive1 = RobotMap.lDrive1;
		rDrive2 = RobotMap.lDrive2;
		rDrive3 = RobotMap.lDrive3;
		
		lPTO = RobotMap.lPTO;
		rPTO = RobotMap.rPTO;
		
		rShifter = RobotMap.rShifter;
		lShifter = RobotMap.lShifter;
		
		gearAP = RobotMap.gear;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new DriveLoop());
	}
	
	public void driveplz(double x, double y){
		if (x <= .05){
			x = 0;
		}else if(y <= 0.05){
			y = 0;
		}
		
		if ((gearAP == 0 && x > .5)){
			gearAP++;
			RobotMap.lShifter.set(true);
		}else if(gearAP == 1 && x < .5){
			gearAP--;
			RobotMap.rShifter.set(false);
		}
			
		SetSpeed(x, y);
	}
	
	private void SetSpeed(double forward, double right){
		
		RobotMap.lDrive1.set(forward + right);
		RobotMap.lDrive2.set(forward + right);
		RobotMap.lDrive3.set(forward + right);
		
		RobotMap.rDrive1.set(forward - right);
		RobotMap.rDrive2.set(forward - right);
		RobotMap.rDrive3.set(forward - right);
	}

}
