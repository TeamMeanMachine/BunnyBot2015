package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.commands.DriveLoop;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveLoop());
	}

}
