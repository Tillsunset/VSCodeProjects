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
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command that uses an example subsystem.
 */
public class Shoot extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final FlyWheel m_FlyWheel;
  private final Pneumatics m_Pneumatics;

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
  double integralLimit = 10000.0;
  double cameraHeight = 3/3.281;// height from ground, meter
  double cameraAngle = 15;// degrees
  double targetHeight = 7.4792/3.281;// assume center of target, may be bottom edge, meter

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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distance = (targetHeight - cameraHeight) / Math.tan(Math.toRadians(ty.getDouble(0.0)) + cameraAngle); // distance in meter
    desiredVelocity = ((.338 * distance + 23.93) * 150) / 60; // ((distance to projectile speed) to Wheel rpm) to sec
    actualVelocity = (m_FlyWheel.getVel()) / (3 * 12); // change in position over ticks per wheel rev per sec

    lastVelocityDifference = velocityDifference;
    velocityDifference = desiredVelocity - actualVelocity;
    derivative = (velocityDifference - lastVelocityDifference) / m_FlyWheel.getTimeDif();

    if (Math.abs(power) < 1.0) {// anti integral windup
      if (integral < -integralLimit) {
        integral = -integralLimit;
      }
      else if (integral > integralLimit) {
        integral = integralLimit;
      }
      else {
        integral += velocityDifference * m_FlyWheel.getTimeDif();
      }
    }

    power = kP * velocityDifference + kI * integral + kD * derivative + kF * desiredVelocity;

    if(power > 1.0) {
      m_FlyWheel.motorGroup.set(1.0);
    }
    else if(power < -1.0) {
      m_FlyWheel.motorGroup.set(-1.0);
    }
    else {
      m_FlyWheel.motorGroup.set(power);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    m_Pneumatics.c.enableDigital();
    m_FlyWheel.motorGroup.set(0);
    m_FlyWheel.elevator.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
