package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.command.Command;

public class OrbitalUp extends Command{
	
	public OrbitalUp(){
		requires(Robot.sucker);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.sucker.orbitalUp();
	}

	@Override
	protected boolean isFinished() {
		return RobotMap.ltop.get();
	}

	@Override
	protected void end() {
		Robot.sucker.orbitalOff();
		Robot.sucker.setOrbUpState(true);
	}

	@Override
	protected void interrupted() {
		end();
	}
}
