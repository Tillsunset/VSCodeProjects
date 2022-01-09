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

public class Winch extends Subsystem {
  WPI_VictorSPX winchMotor = victorSPXConstructor(8);

  public Winch() {
  }

  @Override
  public void periodic() {
  }

  public void up() {
    winchMotor.set(1);
  }

  public void stop() {
    winchMotor.set(0);
  }

  private WPI_VictorSPX victorSPXConstructor(int x){
    WPI_VictorSPX temp = new WPI_VictorSPX(x);

    temp.setNeutralMode(NeutralMode.Coast);

    return temp;
  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub
    
  }
}
