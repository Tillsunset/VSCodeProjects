/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Index extends SubsystemBase {
  WPI_VictorSPX index = victorSPXConstructor(8);

  public Index() {
  }

  @Override
  public void periodic() {
  }

  public void spin() {
    index.set(.5);
  }

  public void stop() {
    index.set(0);
  }

  private WPI_VictorSPX victorSPXConstructor(int x){
    WPI_VictorSPX temp = new WPI_VictorSPX(x);

    temp.setNeutralMode(NeutralMode.Coast);

    return temp;
  }
}
