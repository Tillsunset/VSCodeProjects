/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {
  WPI_VictorSPX intakeMotor = victorSPXConstructor(7);

  public Intake() {
  }

  @Override
  protected void initDefaultCommand() {
    
  }

  @Override
  public void periodic() {
  }

  public void in() {
    intakeMotor.set(.5);
  }

  public void out() {
    intakeMotor.set(-.5);
  }

  public void stop() {
    intakeMotor.set(0);
  }

  private WPI_VictorSPX victorSPXConstructor(int x){
    WPI_VictorSPX temp = new WPI_VictorSPX(x);

    temp.setNeutralMode(NeutralMode.Coast);

    return temp;
  }
}
