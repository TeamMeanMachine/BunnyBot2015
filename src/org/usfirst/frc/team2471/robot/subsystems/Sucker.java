package org.usfirst.frc.team2471.robot.subsystems;

import org.usfirst.frc.team2471.robot.RobotMap;
import org.usfirst.frc.team2471.robot.commands.StayUp;
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
	public boolean bUp; 
	

	public Sucker(){
		fextend = RobotMap.fextend;
		lbottom = RobotMap.lbottom;
		ltop = RobotMap.ltop;
		fbottom = RobotMap.fbottom;
		ftop = RobotMap.ftop;
		orbital = RobotMap.orbital;
		bUp = false;
		
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new StayUp());
	}
	
											
	public void suckup(double powerbot, double powertop){
		fbottom.set(-powerbot);
		ftop.set(powertop);
	}
	
	public void topExtension(boolean direction){
		//false = in ; true = out
		fextend.set(direction);
	}
	
	public void orbitalUp(){
		while(ltop.get() == false){
			orbital.set(0.415);
		}
		bUp=true;
		orbital.set(0.0);
	}
	
	public void orbitalDown(){
		while(lbottom.get() == false){
			orbital.set(-0.1);
		}
		orbital.set(0.0);
		bUp=false;
	}
	public void orbitalStayUp(){
		if(bUp==true && ltop.get()==false){
			orbital.set(0.415);
		}
		else{
			orbital.set(0.0);
		}
	}
}

