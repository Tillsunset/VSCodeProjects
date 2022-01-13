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
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/*******************************************************************
 *********** used for pathplanning, no longer maintained ***********
 *******************************************************************/
// import java.io.IOException;
// import java.nio.file.Path;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.Filesystem;
// import edu.wpi.first.math.trajectory.*;
// import edu.wpi.first.math.controller.RamseteController;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.controller.SimpleMotorFeedforward;
// import edu.wpi.first.wpilibj2.command.RamseteCommand;

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

  private final ExampleSubsystem m_ExampleSubsystem = new ExampleSubsystem();
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Pneumatics m_Pneumatics = new Pneumatics();
  private final FlyWheel m_FlyWheel = new FlyWheel();
  private final Camera m_Camera = new Camera();
  private final Intake m_Intake = new Intake();
  private final Winch m_Winch = new Winch();
  private final Index m_Index = new Index();
  private final PDP m_PDP = new PDP();

  private final CompressorControl m_CompressorControl = new CompressorControl(m_Pneumatics, m_PDP);
  private final DriveWithJoystick m_DriveWithJoystick = new DriveWithJoystick(m_DriveTrain, xbox);
  private final ShifterControl m_ShifterControl = new ShifterControl(m_Pneumatics, m_PDP, m_DriveTrain);
  private final SpinIndex m_SpinIndex = new SpinIndex(m_Index);
  private final IntakeOut m_IntakeOut = new IntakeOut(m_Pneumatics);
  private final IntakeIn m_IntakeIn = new IntakeIn(m_Pneumatics);
  private final BallOut m_BallOut = new BallOut(m_Intake, m_Pneumatics);
  private final BallIn m_BallIn = new BallIn(m_Intake, m_Pneumatics);
  private final Shoot m_Shoot = new Shoot(m_FlyWheel, m_Camera, m_Pneumatics);
  private final Down m_Down = new Down(m_Winch);
  private final Up m_Up = new Up(m_Winch);

  private final Test m_autoCommand = new Test(m_ExampleSubsystem);

  SendableChooser<String> autoChooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    autoChooser.setDefaultOption("turn left", "path 1");
    autoChooser.addOption("turn left while shoot", "path 2");

    // Configure the button bindings
    button1.whileHeld(m_Shoot);
    button1.whileHeld(m_SpinIndex);
    button2.whileHeld(m_BallIn);
    button3.whileHeld(m_BallOut);
    button6.whileHeld(m_Up);
    button7.whileHeld(m_Down);
    button4.whenPressed(m_IntakeIn);
    button5.whenPressed(m_IntakeOut);

    m_DriveTrain.setDefaultCommand(m_DriveWithJoystick);
    m_Pneumatics.setDefaultCommand(m_ShifterControl);
    m_PDP.setDefaultCommand(m_CompressorControl);

    m_DriveTrain.register();
    m_FlyWheel.register();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public SequentialCommandGroup getAutonomousCommand() {
    SequentialCommandGroup temp = new SequentialCommandGroup();
    temp.addCommands(m_autoCommand);
    return temp;
  }

  /*******************************************************************
   ******* Old pathplanning auto in 2020, no longer maintained *******
   *******************************************************************/
  // public CommandGroup getAutonomousCommand() {

  //   // An example trajectory to follow. All units in meter.
  //   //String trajectoryJSON = "output/YourPath.wpilib.json";

  //   String path1Part1 = "output/path1Part1.wpilib.json";
  //   String path1Part2 = "output/path1Part2.wpilib.json";
  //   String unnamed = "output/unnamed.wpilib.json";

  //   try {
  //     switch(autoChooser.getSelected()) {
  //       case "path 1": {
  //         RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
  //         RamseteCommand part2 = simplfyRamseteCommand(path1Part2);
  //         CommandGroup temp;
  //         temp.addSequential(part1);

  //         return new CommandGroup(
  //           m_Test,
  //           part2.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)));
  //       }

  //       case "path 2": {
  //         RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
  //         RamseteCommand part2 = simplfyRamseteCommand(path1Part2);
  //         RamseteCommand part3 = simplfyRamseteCommand(unnamed);

  //         return new CommandGroup(
  //           part1.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)),
  //           new ParallelCommandGroup(
  //             m_Test,
  //             part2.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0))),
  //           part3.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)));
  //       }

  //       default: {
  //         RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
  //         CommandGroup temp;
  //         temp.addSequential(part1);

  //         return temp;
  //       }
  //     }
  //   } 
  //   catch (IOException ex) {
  //     DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());
  //     CommandGroup temp;
  //     temp.addSequential(m_autoCommand);

  //     return temp;
  //   }
  // }

  // public RamseteCommand simplfyRamseteCommand (String path) throws IOException {
  //   Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(path);
  //   Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

  //   return new RamseteCommand(
  //     trajectory,
  //     m_DriveTrain::getPose,
  //     new RamseteController(2.0, .7),
  //     new SimpleMotorFeedforward( DriveTrain.ksVolts,
  //                                 DriveTrain.kvVoltSecondsPerMeter,
  //                                 DriveTrain.kaVoltSecondsSquaredPerMeter),
  //     DriveTrain.kDriveKinematics, 
  //     m_DriveTrain::getWheelSpeeds,
  //     new PIDController(DriveTrain.kPDriveVel, 0, 0, period),
  //     new PIDController(DriveTrain.kPDriveVel, 0, 0, period),
  //     m_DriveTrain::tankDriveVolts,
  //     m_DriveTrain);


  // }
}
