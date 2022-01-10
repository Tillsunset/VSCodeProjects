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
import edu.wpi.first.wpilibj.Timer;

public class DriveTrain extends Subsystem {

  public WPI_TalonSRX driveFR = talonSRXConstructor(1);
  public WPI_TalonSRX driveBR = talonSRXConstructor(2);
  public WPI_TalonSRX driveFL = talonSRXConstructor(3);
  public WPI_TalonSRX driveBL = talonSRXConstructor(4);

  public MotorControllerGroup left = new MotorControllerGroup(driveFL, driveBL);
  public MotorControllerGroup right = new MotorControllerGroup(driveFR, driveBR);
  public DifferentialDrive driveBase = new DifferentialDrive(left, right);
  
  double TicksToMeterRatio = 6*Math.PI*15/(1024*39.37*3*12);

  double lastTime;
  double currentTime = Timer.getFPGATimestamp();
  double timeDifference;

  double lastLeftPos;
  double currentLeftPos = 0;
  double leftPosDif;

  double lastRightPos;
  double currentRightPos = 0;
  double rightPosDif;

  double leftVel = 0;
  double rightVel = 0;
  
  //private ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);

  public DriveTrain() {
    driveFR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveFR.setSelectedSensorPosition(currentRightPos);
    driveFL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveFL.setSelectedSensorPosition(currentLeftPos);

    right.setInverted(true);
    left.setInverted(false);

    //gyro.calibrate();
  }

  @Override
  public void periodic() {
    lastTime = currentTime;
    currentTime = Timer.getFPGATimestamp();
    timeDifference = currentTime - lastTime;

    lastLeftPos = currentLeftPos;
    currentLeftPos = driveFL.getSelectedSensorPosition();
    leftPosDif = currentLeftPos - lastLeftPos;

    lastRightPos = currentRightPos;
    currentRightPos = driveFR.getSelectedSensorPosition();
    rightPosDif = currentRightPos - lastRightPos;
    
    if (timeDifference > 0.005){
      if (Math.abs(leftPosDif) < 75){// does not include noise
        leftVel = leftPosDif * TicksToMeterRatio / timeDifference;
      }

      if (Math.abs(rightPosDif) < 75) {// does not include noise
        rightVel = rightPosDif * TicksToMeterRatio / timeDifference;
      }
    }

    //m_odometry.update(Rotation2d.fromDegrees(getHeading()), driveFL.getSelectedSensorPosition() * RATIO, driveFL.getSelectedSensorPosition() * RATIO);
  }

  public double getLeftVel(){
    return leftVel;
  }
  
  public double getRightVel(){
    return rightVel;
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
}
