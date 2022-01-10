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

import edu.wpi.first.wpilibj.command.Subsystem;

public class FlyWheel extends Subsystem {
  public WPI_TalonSRX driveF = talonSRXConstructor(5);
  public WPI_TalonSRX driveB = talonSRXConstructor(6);
  public WPI_VictorSPX elevator = victorSPXConstructor(8);


  public FlyWheel() {
    driveF.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveB.follow(driveF);
  }

  @Override
  public void periodic() {
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

  @Override
  protected void initDefaultCommand() {
  }
}
