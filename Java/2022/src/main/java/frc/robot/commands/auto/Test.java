/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class Test extends CommandBase {
	private final ExampleSubsystem m_FlyWheel;
	private Timer timer = new Timer();

	/**
	 * Creates a new test.
	 */
	public Test(ExampleSubsystem ExampleSubsystem) {
		m_FlyWheel = ExampleSubsystem;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(m_FlyWheel);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.reset();
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		timer.stop();
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return timer.hasElapsed(1);
	}
}
