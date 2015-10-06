package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveLoop extends Command{
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		double x = Robot.oi.driverStick.getRawAxis(0);
		double y = Robot.oi.driverStick.getRawAxis(1);
		Robot.oi.driverStick.getAxis(Joystick.AxisType.kX);
		
		SetSpeed(x, y);
		
	}
	
	private void SetSpeed(double forward, double right){
		
		if (forward > 0.1 || right > 0.1){
		
		RobotMap.lDrive1.set(forward);
		RobotMap.lDrive2.set(forward);
		RobotMap.lDrive3.set(forward);
		
		RobotMap.rDrive1.set(forward);
		RobotMap.rDrive2.set(forward);
		RobotMap.rDrive3.set(forward);
		}
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
