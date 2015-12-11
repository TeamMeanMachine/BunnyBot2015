package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.SuckUp;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Sucker extends Subsystem{
	
	public static CANTalon ftop;
	public static CANTalon fbottom;
	public CANTalon orbital;
	public DigitalInput ltop;
	public DigitalInput lbottom;
	public Solenoid fextend;
	

	public Sucker(){
		fextend = RobotMap.fextend;
		lbottom = RobotMap.lbottom;
		ltop = RobotMap.ltop;
		fbottom = RobotMap.fbottom;
		ftop = RobotMap.ftop;
		orbital = RobotMap.orbital;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new SuckUp());
	}
	
											
	public void suckup(double power){
		fbottom.set(-power);
	}
	
	public void topExtension(boolean direction){
		//false = in ; true = out
		fextend.set(direction);
	}
	
	public void orbitalUp(){
		while(ltop.get() == false){
			orbital.set(-0.5);
		}
		orbital.set(0.0);
	}
	
	public void orbitalDown(){
		while(lbottom.get() == false){
			orbital.set(0.5);
		}
	}
}
