/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	private XboxController xbox = new XboxController(0);

	private DriveTrain m_DriveTrain = new DriveTrain();

	private DriveWithJoystick m_DriveWithJoystick = new DriveWithJoystick(m_DriveTrain, xbox);
	private DriveForward m_DriveForward = new DriveForward(m_DriveTrain);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {

		// Configure the button bindings

		m_DriveTrain.setDefaultCommand(m_DriveWithJoystick);
	}

	public SequentialCommandGroup getAutonomousCommand() {
		return new SequentialCommandGroup(
				m_DriveForward);
	}
}
