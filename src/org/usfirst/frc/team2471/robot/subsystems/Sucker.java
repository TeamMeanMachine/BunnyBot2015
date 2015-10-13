package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.SuckUp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sucker extends Subsystem{

	public static CANTalon suck1 = RobotMap.suck1;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new SuckUp());
	}
	
	public static void suckIt(boolean direction){
		
		if (direction == false){
			suck1.set(-1);
		}
		else if(direction == true){
			suck1.set(1);
		}else{
			suck1.set(0);
		}
	}

}
