package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class SpitUpReverse extends Command{
	
	boolean stateOrbital = false;

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		stateOrbital = !RobotMap.ltop.get();
		Robot.sucker.setOrbUpState(false);
		Robot.sucker.topExtension(false);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(stateOrbital){
			Robot.sucker.orbitalUp();
		}
		else{
			Robot.sucker.suckup(0.5, 0.5);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(stateOrbital){
			return RobotMap.ltop.get();
		}
		else{
			return !Robot.oi.sMid.get();
		}
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.sucker.orbitalOff();
		Robot.sucker.setOrbUpState(true);
		Robot.sucker.suckup(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
	}

}
