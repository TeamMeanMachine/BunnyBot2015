package org.usfirst.frc.team2471.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BunnyBotAuto extends CommandGroup {

	public BunnyBotAuto() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addParallel(new BunnyRelease());
		addSequential(new OrbitalDown());
		
		addParallel(new SuckUpOnly(), 0.6);
		addSequential(new DriveDistanceCommand(1.5, 0.0, 0.3)); // forward 2 foot

		addParallel(new OrbitalUp());
		addSequential(new BunnyGrab());

		addSequential(new DriveDistanceCommand(33.0, 0.0, 0.3), 10.0); // forward 30 feet
		addSequential(new RotateCommand(200.0, 0.3));
	}
}
