package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.command.Command;

public class SuckUp extends Command{
	
	boolean stateOrbital = false;
	
	public SuckUp(){
		requires(Robot.sucker);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		stateOrbital = !RobotMap.lbottom.get();
		Robot.sucker.setOrbUpState(false);
		Robot.sucker.topExtension(false);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(stateOrbital){
			Robot.sucker.orbitalDown();
		}
		else{
			Robot.sucker.suckup(-0.5, 0.0);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(stateOrbital){
			return RobotMap.lbottom.get();
		}
		else{
			return !Robot.oi.suckMe.get();
		}
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.sucker.orbitalOff();
		Robot.sucker.setOrbUpState(false);
		Robot.sucker.suckup(0.0, 0.0);
		Robot.sucker.topExtension(false);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
