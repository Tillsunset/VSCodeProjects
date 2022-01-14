/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PDP;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ShifterControl extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Pneumatics m_Pneumatics;
  private final PDP m_PDP;
  private final DriveTrain m_DriveTrain;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShifterControl(Pneumatics Pneumatics, PDP PDP, DriveTrain DriveTrain) {
    m_Pneumatics = Pneumatics;
    m_PDP = PDP;
    m_DriveTrain = DriveTrain;
    // Use requires() here to declare subsystem dependencies.
    addRequirements(m_Pneumatics);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(m_DriveTrain.getLeftVel()) > 4 && 
        Math.abs(m_DriveTrain.getRightVel()) > 4) {
      m_Pneumatics.shifters.set(Value.kReverse);
    } 
    else if ((m_PDP.getVoltage() < 8) || 
             (Math.abs(m_DriveTrain.getLeftVel()) < 4 && Math.abs(m_DriveTrain.getRightVel()) < 4) || 
             (m_DriveTrain.getTotalCurrent() > 60)) {
      m_Pneumatics.shifters.set(Value.kForward);
    }
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
