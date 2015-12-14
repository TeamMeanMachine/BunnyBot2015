package org.usfirst.frc.team2471.robot.commands;

import java.awt.print.Book;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveLoop extends Command{
	
	public int gearAP;
	
	public DriveLoop(){
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double x = Robot.oi.driverStick.getRawAxis(2);
		double y = Robot.oi.driverStick.getRawAxis(1);
		
		Robot.drive.driveplz(x, y);
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
