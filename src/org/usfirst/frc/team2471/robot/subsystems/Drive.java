package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	boolean bSpeedControl = true;
	
	WCDLeftPIDOutput wcdLeftPIDOutput;
	WCDRightPIDOutput wcdRightPIDOutput;
	
	public static PIDController leftController, rightController;
	
	class WCDLeftPIDSource implements PIDSource {
		public double pidGet(){
			return RobotMap.leftE.getRate();
		}
	}
	
	class WCDRightPIDSource implements PIDSource {
		public double pidGet(){
			return RobotMap.rightE.getRate();
		}
	}
	
	class WCDLeftPIDOutput implements PIDOutput {
		public double prevPower = 0;
		public void pidWrite(double output){
			prevPower += output;
			if(prevPower >=1.0 ){
				prevPower = 1.0;
			}
			else if(prevPower <= -1.0){
				prevPower = -1.0;
			}
			SetLeftPower( prevPower );
		}
	}
	
	class WCDRightPIDOutput implements PIDOutput {
		public double prevPower = 0;
		public void pidWrite(double output){
			prevPower += output;
			if(prevPower >=1.0 ){
				prevPower = 1.0;
			}
			else if(prevPower <= -1.0){
				prevPower = -1.0;
			}
			SetRightPower( prevPower );
		}
	}
	
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
		
		wcdLeftPIDOutput = new WCDLeftPIDOutput();
		wcdRightPIDOutput = new WCDRightPIDOutput();
		
		if (bSpeedControl)
		{
			leftController = new PIDController( 0.04, 0.0, 0.02, new WCDLeftPIDSource(), wcdLeftPIDOutput);
			rightController = new PIDController( 0.04, 0.0, 0.02, new WCDRightPIDSource(), wcdRightPIDOutput);
			
			SmartDashboard.putData("PID Left: ", leftController);
			SmartDashboard.putData("PID Right: ", rightController);
		
			leftController.enable();
			rightController.enable();
		}
	}

	public void onDisabled(){
		wcdLeftPIDOutput.prevPower = 0;
		wcdRightPIDOutput.prevPower = 0;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveLoop());
	}
	
	public void driveplz(double x, double y){
		SmartDashboard.putNumber("Left Encoder: ", x);
		SmartDashboard.putNumber("Right Encoder: ", y);
		
		double deadband = 0.05;
		if (x <= deadband && x >= -deadband){
			x = 0;
		}
		if(y <= deadband && y >= -deadband){
			y = 0;
		}
		
		double speed = Math.abs(RobotMap.leftE.getRate() + RobotMap.rightE.getRate())/2.0;
		
		if (gearAP == 0 && speed>8.0){  // feet per second
			gearAP++;
			shifter.set(true);
		}
		else if(gearAP == 1 && speed<7.0){
			gearAP--;
			shifter.set(false);
		}
		
		//RobotMap.ftop.set( RobotMap.leftE.getRate() / 240.0 );
		
		System.out.println("Encoder: "+ RobotMap.leftE.getRate());
		
		SetSpeed(0.35*x, y);  // diminish turning speed
	}
	
	void SetLeftPower( double power )
	{
		lDrive1.set(power);
		lDriveMiddle.set(power);
		lDrive2.set(power);		
	}
	
	void SetRightPower( double power )
	{
		rDrive1.set(-power);
		rDriveMiddle.set(-power);
		rDrive2.set(-power);
	}
	private void SetSpeed(double right, double forward){
		if (bSpeedControl)
		{
			leftController.setSetpoint(20.0*(forward+right));
			rightController.setSetpoint(20.0*(forward-right));
			
			//leftController.setSetpoint(5);
			//rightController.setSetpoint(5);
			
			//SetLeftPower( 0.25 );
			//SetRightPower( 0.25 );
		}
		else
		{
			SetLeftPower( forward + right );
			SetRightPower( forward - right );
		}
		
        //SmartDashboard.putNumber("Left Encoder: ", forward);
		//SmartDashboard.putNumber("Right Encoder: ", right);
	}
}
