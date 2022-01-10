/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Camera;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command that uses an example subsystem.
 */
public class Shoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final FlyWheel m_FlyWheel;
  private final Pneumatics m_Pneumatics;

  double lastPosition;
  double currentPosition;
  double lastTime;
  double currentTime;
  double timeDifference;
  double distance;
  double actualVelocity;
  double desiredVelocity;
  double lastVelocityDifference;
  double velocityDifference;
  double integral;
  double derivative;
  double power;
  NetworkTableEntry ty;

  double kP;// tune these values
  double kI;
  double kD;
  double kF;
  double cameraHeight = 3;//height from ground, ft
  double cameraAngle = 15;//degrees
  double targetHeight = 7.4792;//assume center of target, may be bottom edge, ft


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Shoot(FlyWheel FlyWheel, Camera Camera, Pneumatics Pneumatics) {
    m_FlyWheel = FlyWheel;
    m_Pneumatics = Pneumatics;
    ty = Camera.getTy();
    // Use requires() here to declare subsystem dependencies.
    requires(m_FlyWheel);
    requires(m_Pneumatics);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Pneumatics.c.disable();
    m_FlyWheel.elevator.set(.5);
    integral = 0;
    velocityDifference = 0;
    currentTime = Timer.getFPGATimestamp();
    currentPosition = m_FlyWheel.driveF.getSelectedSensorPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {lastPosition = currentPosition;
    currentPosition = m_FlyWheel.driveF.getSelectedSensorPosition();
    lastTime = currentTime;
    currentTime = Timer.getFPGATimestamp();

    if ((currentPosition > lastPosition - 75) && //gets rid of noise
        (currentPosition < lastPosition + 75)) {
      timeDifference = currentTime - lastTime;

      distance = (targetHeight - cameraHeight) / Math.tan(Math.toRadians(ty.getDouble(0.0) + cameraAngle));//distance in feet
      desiredVelocity = ((.338 * distance + 23.93) * 150) / 60;//((distance to projectile speed) to Wheel rpm) to sec
      actualVelocity = (currentPosition - lastPosition)/(3 * 12 * timeDifference); //change in position over ticks per wheel rev per sec

      lastVelocityDifference = velocityDifference;
      velocityDifference = desiredVelocity - actualVelocity;
      derivative = (velocityDifference - lastVelocityDifference) / (timeDifference);

      if (integral < -10000) {// anti integral windup
        integral = -10000;
      } 
      else if (integral > 10000) {
        integral = 10000;
      }
      else {
        integral += velocityDifference * timeDifference;
      }

      power = kP * velocityDifference + kI * integral + kD * derivative + kF * desiredVelocity;
    }
            
    m_FlyWheel.driveF.set(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end( ) {
    m_Pneumatics.c.enableDigital();
    m_FlyWheel.driveF.set(0);
    m_FlyWheel.elevator.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
