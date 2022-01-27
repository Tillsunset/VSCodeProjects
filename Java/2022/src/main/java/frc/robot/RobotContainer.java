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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import java.io.IOException;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.math.trajectory.*;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

	private XboxController xbox = new XboxController(0);
	private JoystickButton button1 = new JoystickButton(xbox, 1);
	private JoystickButton button2 = new JoystickButton(xbox, 2);
	private JoystickButton button3 = new JoystickButton(xbox, 3);
	private JoystickButton button4 = new JoystickButton(xbox, 4);
	private JoystickButton button5 = new JoystickButton(xbox, 5);
	private JoystickButton button6 = new JoystickButton(xbox, 6);
	private JoystickButton button7 = new JoystickButton(xbox, 7);

	protected final ExampleSubsystem m_ExampleSubsystem = new ExampleSubsystem();
	protected final Pneumatics m_Pneumatics = new Pneumatics();
	protected final DriveTrain m_DriveTrain = new DriveTrain();
	protected final FlyWheel m_FlyWheel = new FlyWheel();
	protected final Camera m_Camera = new Camera();
	protected final Intake m_Intake = new Intake();
	protected final Winch m_Winch = new Winch();
	protected final Index m_Index = new Index();
	protected final PDP m_PDP = new PDP();

	protected final CompressorControl m_CompressorControl = new CompressorControl(m_Pneumatics, m_PDP);
	protected final DriveWithJoystick m_DriveWithJoystick = new DriveWithJoystick(m_DriveTrain, xbox);
	protected final ShifterControl m_ShifterControl = new ShifterControl(m_Pneumatics, m_PDP, m_DriveTrain);
	protected final IntakeOut m_IntakeOut = new IntakeOut(m_Pneumatics);
	protected final SpinIndex m_SpinIndex = new SpinIndex(m_Index);
	protected final IntakeIn m_IntakeIn = new IntakeIn(m_Pneumatics);
	protected final BallOut m_BallOut = new BallOut(m_Intake, m_Pneumatics);
	protected final BallIn m_BallIn = new BallIn(m_Intake, m_Pneumatics);
	protected final Shoot m_Shoot = new Shoot(m_FlyWheel, m_Camera, m_Pneumatics);
	protected final Down m_Down = new Down(m_Winch);
	protected final Up m_Up = new Up(m_Winch);

	protected final StopDriveTrain m_StopDriveTrain = new StopDriveTrain(m_DriveTrain);
	protected final Test m_Test = new Test(m_ExampleSubsystem);

	private SendableChooser<String> autoChooser;

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		autoChooser.setDefaultOption("turn left", "path 1");
		autoChooser.addOption("turn left while shoot", "path 2");

		m_DriveTrain.setDefaultCommand(m_DriveWithJoystick);
		m_Pneumatics.setDefaultCommand(m_ShifterControl);
		m_PDP.setDefaultCommand(m_CompressorControl);

		m_DriveTrain.register();
		m_FlyWheel.register();

		configureButtonBindings();
	}

	private void configureButtonBindings() {
		// Configure the button bindings
		button1.whileHeld(m_Shoot);
		button1.whileHeld(m_SpinIndex);
		button2.whileHeld(m_BallIn);
		button3.whileHeld(m_BallOut);
		button6.whileHeld(m_Up);
		button7.whileHeld(m_Down);
		button4.whenPressed(m_IntakeIn);
		button5.whenPressed(m_IntakeOut);
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */

	// public SequentialCommandGroup getAutonomousCommand2() {
	// 	return new SequentialCommandGroup(
	// 			m_StopDriveTrain);
	// }

	public SequentialCommandGroup getAutonomousCommand() {

		// An example trajectory to follow. All units in meter.
		// String trajectoryJSON = "output/YourPath.wpilib.json";

		String path1Part1 = "output/path1Part1.wpilib.json";
		String path1Part2 = "output/path1Part2.wpilib.json";
		String unnamed = "output/unnamed.wpilib.json";

		try {
			switch (autoChooser.getSelected()) {
				case "path 1": {
					resetOdometry(path1Part1);
					RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
					RamseteCommand part2 = simplfyRamseteCommand(path1Part2);

					return new SequentialCommandGroup(
							part1,
							m_Test,
							part2,
							m_StopDriveTrain);
				}

				case "path 2": {
					resetOdometry(path1Part1);
					RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
					RamseteCommand part2 = simplfyRamseteCommand(path1Part2);
					RamseteCommand part3 = simplfyRamseteCommand(unnamed);
					
					return new SequentialCommandGroup(
							part1,
							new ParallelCommandGroup(
									m_Test,
									part2),
							part3,
							m_StopDriveTrain);
				}

				default: {
					resetOdometry(unnamed);
					RamseteCommand part1 = simplfyRamseteCommand(unnamed);

					return new SequentialCommandGroup(
							part1,
							m_StopDriveTrain);
				}
			}
		} catch (IOException ex) {
			DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());

			return new SequentialCommandGroup(
					m_StopDriveTrain);
		}
	}

	public void resetOdometry(String path) throws IOException {
		Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(
				Filesystem.getDeployDirectory().toPath().resolve(path));
		m_DriveTrain.resetOdometry(trajectory.getInitialPose());
	}

	public RamseteCommand simplfyRamseteCommand(String path) throws IOException {
		Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(
				Filesystem.getDeployDirectory().toPath().resolve(path));

		return new RamseteCommand(
				trajectory,
				m_DriveTrain::getPose,
				new RamseteController(2.0, .7),
				new SimpleMotorFeedforward(DriveTrain.ksVolts,
						DriveTrain.kvVoltSecondsPerMeter,
						DriveTrain.kaVoltSecondsSquaredPerMeter),
				DriveTrain.kDriveKinematics,
				m_DriveTrain::getWheelSpeeds,
				new PIDController(DriveTrain.kPDriveVel, 0, 0),
				new PIDController(DriveTrain.kPDriveVel, 0, 0),
				m_DriveTrain::tankDriveVolts,
				m_DriveTrain);
	}
}
