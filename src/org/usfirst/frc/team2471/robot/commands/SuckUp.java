package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.command.Command;

public class SuckUp extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Sucker.suckIt(Robot.oi.suckMe.get());
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		RobotMap.suck1.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		RobotMap.suck1.set(0);
	}

}
