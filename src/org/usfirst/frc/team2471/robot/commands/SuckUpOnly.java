package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.command.Command;

public class SuckUpOnly extends Command{
	
	public SuckUpOnly(){
		requires(Robot.sucker);
	}

	protected void initialize() {
		Robot.sucker.topExtension(false);
	}

	protected void execute() {
		Robot.sucker.suckup(-0.5, 0.0);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.sucker.suckup(0.0, 0.0);
	}

	protected void interrupted() {
		end();
	}
}
