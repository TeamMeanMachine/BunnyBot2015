package org.usfirst.frc.team2471.robot.commands;

import java.awt.print.Book;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveLoop extends Command{
	
	public int gearAP;
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		RobotMap.gear = gearAP;
		double x = Robot.oi.driverStick.getRawAxis(0);
		double y = Robot.oi.driverStick.getRawAxis(1);
		
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

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
