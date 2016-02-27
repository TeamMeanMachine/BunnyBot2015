package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		//double x = Robot.oi.driverStick.getRawAxis(2);
		//double y = Robot.oi.driverStick.getRawAxis(1);
		
		//2016 test debug
		/*double x = Robot.oi.driverStick.getRawAxis(1);
		double y = Robot.oi.driverStick.getRawAxis(3);*/
		
		//x = x * x * x;
		//y = y * y * y;
		
		//Robot.drive.driveplz(x, y);

		Robot.drive.drivetestplz(SmartDashboard.getNumber("Top"), SmartDashboard.getNumber("Bottom"));
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
