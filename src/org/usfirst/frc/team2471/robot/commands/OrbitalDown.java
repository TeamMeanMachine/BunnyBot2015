package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.command.Command;

public class OrbitalDown extends Command{
	
	public OrbitalDown(){
		requires(Robot.sucker);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.sucker.orbitalDown();
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.lbottom.get();
	}

	@Override
	protected void end() {
		Robot.sucker.orbitalOff();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
