package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sucker extends Subsystem{
	
	public static CANTalon ftop = RobotMap.ftop;
	public static CANTalon fbottom = RobotMap.fbottom;
	public DigitalInput ltop = RobotMap.ltop;
	public DigitalInput lbottom = RobotMap.lbottom;
	public Solenoid fextend = RobotMap.fextend;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
											
	public static void suckup(double power){
		ftop.set(power);
		fbottom.set(power);
	}
}
