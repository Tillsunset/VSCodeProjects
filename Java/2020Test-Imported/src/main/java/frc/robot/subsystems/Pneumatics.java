/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {

  public Compressor c = new Compressor();

  public DoubleSolenoid intake = new DoubleSolenoid(0, 1);
  public DoubleSolenoid shifters = new DoubleSolenoid(2, 3);
  //Solenoid test = new Solenoid(6);

  public Pneumatics() {
  }

  public void intakeOut() {
    intake.set(Value.kForward);
  }

  public void intakeIn() {
    intake.set(Value.kReverse);
  }

  public void shiftUp() {
    shifters.set(Value.kReverse);
  }
  
  public void shiftDown() {
    shifters.set(Value.kForward);
  }

  @Override
  public void periodic() {
    
  }
}
