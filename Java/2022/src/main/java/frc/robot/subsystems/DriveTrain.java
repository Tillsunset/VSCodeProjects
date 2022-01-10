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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.kinematics.*;

public class DriveTrain extends Subsystem {
  // public static final double kaVoltSecondsSquaredPerMeter = 0;// tune these
  // public static final double kvVoltSecondsPerMeter = 0;
  // public static final double ksVolts = 0;
  // public static final double kPDriveVel = 0;
  // public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(1);// wheel base in meters

  public WPI_TalonSRX driveFR = talonSRXConstructor(1);
  public WPI_TalonSRX driveBR = talonSRXConstructor(2);
  public WPI_TalonSRX driveFL = talonSRXConstructor(3);
  public WPI_TalonSRX driveBL = talonSRXConstructor(4);

  public MotorControllerGroup left = new MotorControllerGroup(driveFL, driveBL);
  public MotorControllerGroup right = new MotorControllerGroup(driveFR, driveBR);
  public DifferentialDrive driveBase = new DifferentialDrive(left, right);
  
  
  double RATIO = 6*Math.PI*15/(1024*39.37*3*12);

  private ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
  // private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
  //     Rotation2d.fromDegrees(getHeading()));

  public DriveTrain() {
    driveFR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveFL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    gyro.calibrate();
  }

  @Override
  public void periodic() {
    //m_odometry.update(Rotation2d.fromDegrees(getHeading()), driveFL.getSelectedSensorPosition() * RATIO, driveFL.getSelectedSensorPosition() * RATIO);
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

  @Override
  protected void initDefaultCommand() {
  }



  // public double getHeading() {
  //   return Math.IEEEremainder(gyro.getAngle(), 360) * -1;
  // }
  
  // public Pose2d getPose() {
  //   return m_odometry.getPoseMeters();
  // }

  // public void tankDriveVolts(double leftVolts, double rightVolts) {
  //   driveFL.setVoltage(leftVolts);
  //   driveFR.setVoltage(-rightVolts);
  //   driveBase.feed();
  // }
  
  // public DifferentialDriveWheelSpeeds getWheelSpeeds() {
  //   return new DifferentialDriveWheelSpeeds(driveFL.getSelectedSensorPosition() * RATIO, driveFL.getSelectedSensorPosition() * RATIO);
  // }
}
