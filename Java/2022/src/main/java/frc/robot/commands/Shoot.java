/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Index;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command that uses an example subsystem.
 */
public class Shoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final FlyWheel m_FlyWheel;
  private final Index m_Index;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shoot(FlyWheel FlyWheel, Index Index) {
    m_FlyWheel = FlyWheel;
    m_Index = Index;
    // Use requires() here to declare subsystem dependencies.
    requires(m_FlyWheel);
    requires(m_Index);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_FlyWheel.start();
    m_Index.spin();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_FlyWheel.shoot();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end( ) {
    m_FlyWheel.stop();
    m_Index.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
