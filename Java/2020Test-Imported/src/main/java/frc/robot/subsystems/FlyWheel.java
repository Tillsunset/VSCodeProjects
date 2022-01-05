/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheel extends SubsystemBase {
  WPI_TalonSRX driveF = talonSRXConstructor(5);
  WPI_TalonSRX driveB = talonSRXConstructor(6);
  WPI_VictorSPX elevator = victorSPXConstructor(8);

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

  public FlyWheel() {
    driveF.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveB.follow(driveF);
  }

  @Override
  public void periodic() {
  }

  public void sendVeticalOffset(NetworkTableEntry x) {
    ty = x;
  }

  public void start() {
    elevator.set(.5);
    integral = 0;
    velocityDifference = 0;
    currentTime = Timer.getFPGATimestamp();
    currentPosition = driveF.getSelectedSensorPosition();
  }

  public void shoot() {
    lastPosition = currentPosition;
    currentPosition = driveF.getSelectedSensorPosition();
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
            
    driveF.set(power);
  }

  public void stop() {
    driveF.set(0);
    elevator.set(0);
  }

  private WPI_TalonSRX talonSRXConstructor(int x) {
    WPI_TalonSRX temp = new WPI_TalonSRX(x);

    temp.setNeutralMode(NeutralMode.Coast);

    return temp;
  }

  private WPI_VictorSPX victorSPXConstructor(int x) {
    WPI_VictorSPX temp = new WPI_VictorSPX(x);

    temp.setNeutralMode(NeutralMode.Coast);

    return temp;
  }
}
