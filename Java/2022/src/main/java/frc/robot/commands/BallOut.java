/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command that uses an example subsystem.
 */
public class BallOut extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_Intake;
  private final Pneumatics m_Pneumatics;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public BallOut(Intake Intake, Pneumatics Pneumatics) {
    m_Intake = Intake;
    m_Pneumatics = Pneumatics;
    // Use requires() here to declare subsystem dependencies.
    requires(m_Intake);
    requires(m_Pneumatics);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Pneumatics.intakeOut();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Intake.out();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end( ) {
    m_Intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
