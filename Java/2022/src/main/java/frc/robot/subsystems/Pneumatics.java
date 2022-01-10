/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
  public Compressor c = new Compressor(0, PneumaticsModuleType.CTREPCM);

  public DoubleSolenoid intake = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  public DoubleSolenoid shifters = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

  public Pneumatics() {
  }
  
  @Override
  public void periodic() {
  }

  @Override
  protected void initDefaultCommand() {
  }
}
