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
	public boolean bStayUp; 
	

	public Sucker(){
		fextend = RobotMap.fextend;
		lbottom = RobotMap.lbottom;
		ltop = RobotMap.ltop;
		fbottom = RobotMap.fbottom;
		ftop = RobotMap.ftop;
		orbital = RobotMap.orbital;
		bStayUp = ltop.get() == true;
		
	}
	
	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new StayUp());
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
		orbital.set(0.415);
		/*bUp=true;*/
	}
	
	public void setOrbUpState(boolean up){
		bStayUp = up;
		if (!up) {
			orbital.set(0.0);
		}
	}
	
	public void orbitalDown(){
		orbital.set(-0.1);
		/*bUp=false;*/
	}
	public void orbitalStayUp(){
		if(bStayUp == true)
		{
			if (ltop.get() == false){
				//orbital.set(0.415);
			}
			else{
				orbital.set(0.0);
			}
		}
	}
	
	public void orbitalOff(){
		orbital.set(0.0);
	}
		
}

