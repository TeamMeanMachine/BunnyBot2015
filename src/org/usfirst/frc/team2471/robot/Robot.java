
package org.usfirst.frc.team2471.robot;


import org.usfirst.frc.team2471.robot.subsystems.Drive;
import org.usfirst.frc.team2471.robot.subsystems.Sucker;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drive drive;
	public static Sucker sucker;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
		drive = new Drive();
		sucker = new Sucker();
		oi = new OI();
        // instantiate the command used for the autonomous period
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		/*System.out.println("Right Encoder: " + RobotMap.rightE.get());
		System.out.println("Left Encoder: " + RobotMap.leftE.get());
		System.out.println("Limit Top: " + RobotMap.ltop.get());
		System.out.println("Limit Bot: " + RobotMap.lbottom.get());*/
		
		SmartDashboard.putNumber("Right Encoder: ", RobotMap.rightE.getDistance());
        SmartDashboard.putNumber("Left Encoder: ", RobotMap.leftE.getDistance());
        SmartDashboard.putBoolean("Limit Top: ", RobotMap.ltop.get());
        SmartDashboard.putBoolean("Limit Bot: ", RobotMap.lbottom.get());
		drive.onDisabled();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        //System.out.println("Hello world");
        
        /*System.out.println("Right Encoder: " + RobotMap.rightE.get());
		System.out.println("Left Encoder: " + );
		System.out.println("Limit Top: " + );
		System.out.println("Limit Bot: " + RobotMap.lbottom.get());*/
        
		//SmartDashboard.putNumber("Right Encoder: ", RobotMap.rightE.getRate());
        //SmartDashboard.putNumber("Left Encoder: ", RobotMap.leftE.getRate());
        SmartDashboard.putBoolean("Limit Top: ", RobotMap.ltop.get());
        SmartDashboard.putBoolean("Limit Bot: ", RobotMap.lbottom.get());

    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
