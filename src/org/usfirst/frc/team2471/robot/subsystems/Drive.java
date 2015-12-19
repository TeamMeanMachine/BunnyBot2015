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
	
	boolean bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);  // should read from prefs and save to prefs on disabled, find TMMSmartDashboard from 2015 Robot code.
	
	WCDLeftPIDOutput wcdLeftPIDOutput;
	WCDRightPIDOutput wcdRightPIDOutput;
	public double requestedPowerLeft, requestedPowerRight;
	
	public PIDController leftController, rightController;
	
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
		public double prevScale = 1.0;
		public void pidWrite(double output){
			output *= Math.signum( requestedPowerLeft );
			prevScale += output;
			if(prevScale >=10.0 ){
				prevScale = 10.0;
			}
			else if(prevScale <= 0.01){
				prevScale = 0.01;
			}
			SetLeftPower( prevScale * requestedPowerLeft );
		}
	}
	
	class WCDRightPIDOutput implements PIDOutput {
		public double prevScale = 1.0;
		public void pidWrite(double output){
			output *= Math.signum( requestedPowerRight );
			prevScale += output;
			if(prevScale >=10.0 ){
				prevScale = 10.0;
			}
			else if(prevScale <= 0.01){
				prevScale = 0.01;
			}
			SetRightPower( prevScale * requestedPowerRight );
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
		
		leftController = new PIDController( 0.01, 0.0, 0.03, new WCDLeftPIDSource(), wcdLeftPIDOutput);
		rightController = new PIDController( 0.01, 0.0, 0.03, new WCDRightPIDSource(), wcdRightPIDOutput);
			
		SmartDashboard.putData("PID Left: ", leftController);
		SmartDashboard.putData("PID Right: ", rightController);
		SmartDashboard.putBoolean("Speed Control", bSpeedControl);
	}

	public void onDisabled(){
		wcdLeftPIDOutput.prevScale = 1.0;
		wcdRightPIDOutput.prevScale = 1.0;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveLoop());
	}
	
	public void driveplz(double x, double y){
		SmartDashboard.putNumber("Left Encoder: ", RobotMap.leftE.getRate());
		SmartDashboard.putNumber("Right Encoder: ", RobotMap.rightE.getRate());
		
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
		
		if (gearAP == 0)
			SetSpeed(0.75*x, y);  // diminish turning speed
		else
			SetSpeed(1*x, y);  // diminish turning speed
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
	public void SetSpeed(double right, double forward){
		
		bSpeedControl = SmartDashboard.getBoolean("Speed Control", true);

		if (bSpeedControl)
		{
			leftController.enable();
			rightController.enable();
		}
		else {
			leftController.disable();
			rightController.disable();
		}

		if (bSpeedControl)
		{
			double a = forward + right;
			double b = forward - right;
			double maxPower = Math.max( Math.abs(a) , Math.abs(b) );
			if (maxPower > 1.0){
				a /= maxPower;
				b /= maxPower;
			}
			
			if (a==0) {
				wcdLeftPIDOutput.prevScale = 1.0;
			}
			if (b==0) {
				wcdRightPIDOutput.prevScale = 1.0;
			}
			
			requestedPowerLeft = a;
			requestedPowerRight = b;
			leftController.setSetpoint( 20.0 * a );
			rightController.setSetpoint( 20.0 * b );
		}
		else
		{
			SetLeftPower( forward + right );
			SetRightPower( forward - right );
		}
	}
}
