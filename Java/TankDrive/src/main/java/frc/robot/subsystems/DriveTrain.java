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

// import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class DriveTrain extends SubsystemBase {

  public WPI_TalonSRX driveFR = talonSRXConstructor(1);
  public WPI_TalonSRX driveBR = talonSRXConstructor(2);
  public WPI_TalonSRX driveFL = talonSRXConstructor(3);
  public WPI_TalonSRX driveBL = talonSRXConstructor(4);

  public MotorControllerGroup left = new MotorControllerGroup(driveFL, driveBL);
  public MotorControllerGroup right = new MotorControllerGroup(driveFR, driveBR);
  public DifferentialDrive driveBase = new DifferentialDrive(left, right);


  public DriveTrain() {
    driveFR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveFR.setSelectedSensorPosition(currentRightPos);
    driveFL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveFL.setSelectedSensorPosition(currentLeftPos);

    right.setInverted(true);
    left.setInverted(false);
  }

  @Override
  public void periodic() {
  }

  private WPI_TalonSRX talonSRXConstructor(int x){
    WPI_TalonSRX temp = new WPI_TalonSRX(x);

    temp.configContinuousCurrentLimit(40);//used standard play
    temp.configPeakCurrentLimit(44, 1000);//used for pushing, limit for stopping wheel spin
    temp.enableCurrentLimit(true);
    temp.configOpenloopRamp(.25);//fine tune for best responsiveness
    temp.configClosedloopRamp(0);//used for driving by encoders
    temp.setNeutralMode(NeutralMode.Brake);

    return temp;
  }
}
