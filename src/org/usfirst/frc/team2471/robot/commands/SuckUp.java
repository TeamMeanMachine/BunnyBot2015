package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.command.Command;

public class SuckUp extends Command{
	
	public SuckUp(){
		requires(Robot.sucker);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.sucker.setOrbUpState(false);
		Robot.sucker.topExtension(true);
		Robot.sucker.orbitalDown();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.sucker.suckup(-.5, 0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if (RobotMap.lbottom.get() == true){
			Robot.sucker.orbitalOff();
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
		Robot.sucker.suckup(0.0, 0.0);
		Robot.sucker.topExtension(true);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.sucker.suckup(0.0, 0.0);
		Robot.sucker.topExtension(true);
	}

}
