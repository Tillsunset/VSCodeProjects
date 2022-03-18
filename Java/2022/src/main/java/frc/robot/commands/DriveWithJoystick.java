/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DriveWithJoystick extends CommandBase {
	private final DriveTrain m_DriveTrain;
	private DoubleSupplier leftAxis;
	private DoubleSupplier rightAxis;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public DriveWithJoystick(DriveTrain DriveTrain, XboxController x) {
		m_DriveTrain = DriveTrain;
		leftAxis = x::getLeftY;
		leftAxis = x::getRightY;
		// Use requires() here to declare subsystem dependencies.
		addRequirements(m_DriveTrain);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		m_DriveTrain.driveBase.tankDrive(leftAxis.getAsDouble(), rightAxis.getAsDouble());
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
