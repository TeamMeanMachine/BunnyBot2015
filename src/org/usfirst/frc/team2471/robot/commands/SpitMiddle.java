package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpitMiddle extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.sucker.topExtension(false);
		Robot.sucker.orbitalUp();
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.sucker.suckup(0.5, 0.5);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.sucker.suckup(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.sucker.suckup(0.0, 0.0);
	}

}
