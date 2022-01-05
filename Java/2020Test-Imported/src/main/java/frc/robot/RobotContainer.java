/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import frc.robot.commands.*;
import frc.robot.commands.auto.*;
import frc.robot.subsystems.*;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private PowerDistributionPanel PDP = new PowerDistributionPanel(0);

  private XboxController xbox = new XboxController(0);
  private JoystickButton button1 = new JoystickButton(xbox, 1);
  private JoystickButton button2 = new JoystickButton(xbox, 2);
  private JoystickButton button3 = new JoystickButton(xbox, 3);
  private JoystickButton button4 = new JoystickButton(xbox, 4);
  private JoystickButton button5 = new JoystickButton(xbox, 5);
  private JoystickButton button6 = new JoystickButton(xbox, 6);

  private final ExampleSubsystem m_ExampleSubsystem = new ExampleSubsystem();
  private final DriveTrain m_DriveTrain = new DriveTrain(xbox);
  private final Pneumatics m_Pneumatics = new Pneumatics();
  private final FlyWheel m_FlyWheel = new FlyWheel();
  private final Intake m_Intake = new Intake();
  private final Winch m_Winch = new Winch();
  private final Index m_Index = new Index();

  private final DriveWithJoystick m_DriveWithJoystick = new DriveWithJoystick(m_DriveTrain);
  private final BallOut m_BallOut = new BallOut(m_Intake, m_Pneumatics);
  private final BallIn m_BallIn = new BallIn(m_Intake, m_Pneumatics);
  private final IntakeOut m_IntakeOut = new IntakeOut(m_Pneumatics);
  private final IntakeIn m_IntakeIn = new IntakeIn(m_Pneumatics);
  private final ShiftDown m_ShiftDown = new ShiftDown(m_Pneumatics);
  private final ShiftUp m_ShiftUp = new ShiftUp(m_Pneumatics);
  private final Shoot m_Shoot = new Shoot(m_FlyWheel, m_Index);
  private final Up m_Up = new Up(m_Winch);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_ExampleSubsystem);
  private final Test m_Test = new Test(m_FlyWheel);

  SendableChooser<String> autoChooser;
  double period = .01;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    autoChooser.setDefaultOption("turn left", "path 1");
    autoChooser.addOption("turn left while shoot", "path 2");

    // Configure the button bindings
    button1.whileHeld(m_Shoot);
    button2.whileHeld(m_BallIn);
    button3.whileHeld(m_BallOut);
    button6.whileHeld(m_Up);
    button4.whenPressed(m_IntakeIn);
    button5.whenPressed(m_IntakeOut);

    m_DriveTrain.setDefaultCommand(m_DriveWithJoystick);
  }

  public void sendPeriod(double x) {
    period = x;
  }

  public void sendVeticalOffset(NetworkTableEntry x) {
    m_FlyWheel.sendVeticalOffset(x);
  }

  public void shift() {
    if (Math.abs(m_DriveTrain.getWheelSpeeds().leftMetersPerSecond) > 4 && 
        Math.abs(m_DriveTrain.getWheelSpeeds().rightMetersPerSecond) > 4) {
      m_ShiftUp.initialize();
    } 
    else if ((PDP.getVoltage() < 8) || 
             (Math.abs(m_DriveTrain.getWheelSpeeds().leftMetersPerSecond) < 4 && 
              Math.abs(m_DriveTrain.getWheelSpeeds().rightMetersPerSecond) < 4) || 
             (m_DriveTrain.driveFL.getStatorCurrent() > 20 && m_DriveTrain.driveFR.getStatorCurrent() > 20)) {
      m_ShiftDown.initialize();
    }
  }

  public void votlageCompressorControl() {
    if (PDP.getVoltage() < 10 ||
        m_Shoot.isScheduled()) {
      m_Pneumatics.c.setClosedLoopControl(false);
    } 
    else {
      m_Pneumatics.c.setClosedLoopControl(true);
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public SequentialCommandGroup getAutonomousCommand() {

    // An example trajectory to follow. All units in meter.
    //String trajectoryJSON = "output/YourPath.wpilib.json";

    String path1Part1 = "output/path1Part1.wpilib.json";
    String path1Part2 = "output/path1Part2.wpilib.json";
    String unnamed = "output/unnamed.wpilib.json";

    try {
      switch(autoChooser.getSelected()) {
        case "path 1": {
          RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
          RamseteCommand part2 = simplfyRamseteCommand(path1Part2);

          return new SequentialCommandGroup(
            part1.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)),
            m_Test,
            part2.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)));
        }

        case "path 2": {
          RamseteCommand part1 = simplfyRamseteCommand(path1Part1);
          RamseteCommand part2 = simplfyRamseteCommand(path1Part2);
          RamseteCommand part3 = simplfyRamseteCommand(unnamed);

          return new SequentialCommandGroup(
            part1.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)),
            new ParallelCommandGroup(
              m_Test,
              part2.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0))),
            part3.andThen(() -> m_DriveTrain.tankDriveVolts(0, 0)));
        }

        default: {
          RamseteCommand part1 = simplfyRamseteCommand(path1Part1);

          return new SequentialCommandGroup(part1);
        }
      }
    } 
    catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory", ex.getStackTrace());

      return new SequentialCommandGroup(m_autoCommand);
    }
  }

  public RamseteCommand simplfyRamseteCommand (String path) throws IOException {
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(path);
    Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

    return new RamseteCommand(
      trajectory,
      m_DriveTrain::getPose,
      new RamseteController(2.0, .7),
      new SimpleMotorFeedforward(
        DriveTrain.ksVolts,
        DriveTrain.kvVoltSecondsPerMeter,
        DriveTrain.kaVoltSecondsSquaredPerMeter),
      DriveTrain.kDriveKinematics, 
      m_DriveTrain::getWheelSpeeds,
      new PIDController(DriveTrain.kPDriveVel, 0, 0, period),
      new PIDController(DriveTrain.kPDriveVel, 0, 0, period),
      m_DriveTrain::tankDriveVolts,
      m_DriveTrain);
  }
}
