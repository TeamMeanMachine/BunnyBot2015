package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateCommand extends Command {

	double distance;
    boolean started = false;
    double startLeft, startRight;
    double speed;
    
    public RotateCommand(double angle, double speedZeroToOne ) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
        distance = angle/360.0*27.0/12.0*Math.PI;
        speed = speedZeroToOne;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!started) {
        	startLeft = RobotMap.leftE.getDistance();
        	startRight = RobotMap.rightE.getDistance();
            started = true;
            Robot.drive.SetSpeed( speed, 0 );
//			Robot.drive.leftController.setSetpoint(-Math.signum(distance) * speed);
//    		Robot.drive.rightController.setSetpoint(Math.signum(distance) * speed);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(startLeft - RobotMap.leftE.getDistance())> distance){
    		return true;
    	}
    	if (Math.abs(startRight - RobotMap.rightE.getDistance())> distance){
    		return true;
    	}
    	if (isTimedOut())
    		return true;

        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        started = false;
        Robot.drive.SetSpeed( 0, 0 );
//		Robot.drive.leftController.setSetpoint(0);
//		Robot.drive.rightController.setSetpoint(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
