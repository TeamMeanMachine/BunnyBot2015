package org.usfirst.frc.team2471.robot.commands;

import org.usfirst.frc.team2471.robot.Robot;
import org.usfirst.frc.team2471.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 * @author FIRST
 */
public class DriveDistanceCommand extends Command {
    double distance;
    double x;
    double y;
    boolean started = false;
    double startDistance;
    
    public DriveDistanceCommand( double _distance, double _x, double _y) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
        
        x = _x;
        y = _y;
        distance = _distance;
        System.out.println("drive distance constructed.");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!started) {
        	startDistance = (Math.abs(RobotMap.leftE.getDistance()) + Math.abs(RobotMap.rightE.getDistance()))/2;
            started = true;
        }
        Robot.drive.driveplz(x, y);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (((Math.abs(RobotMap.leftE.getDistance()) + Math.abs(RobotMap.rightE.getDistance()))/2) - startDistance > distance) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.driveplz(x, y);
        started = false;
        //new ResetDriveEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
