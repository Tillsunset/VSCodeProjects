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

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Timer;

public class FlyWheel extends SubsystemBase {
  private WPI_TalonSRX driveF = talonSRXConstructor(5);
  private WPI_TalonSRX driveB = talonSRXConstructor(6);
  public WPI_VictorSPX elevator = victorSPXConstructor(8);

  public MotorControllerGroup motorGroup = new MotorControllerGroup(driveF, driveB);

  double lastTime;
  double currentTime = Timer.getFPGATimestamp();
  double timeDifference = 0;

  double lastPos;
  double currentPos = 0;
  double posDif;
  
  double currentVel = 0;

  public FlyWheel() {
    driveF.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    driveF.setSelectedSensorPosition(currentPos);
  }

  @Override
  public void periodic() {
    lastPos = currentPos;
    currentPos = driveF.getSelectedSensorPosition();
    posDif = currentPos - lastPos;

    lastTime = currentTime;
    currentTime = Timer.getFPGATimestamp();
    timeDifference = currentTime - lastTime;

    if ((Math.abs(posDif) < 75)&&
        (timeDifference > 0.005)) {// does not include noise
      currentVel = (posDif)/timeDifference;
    }
  }

  public double getVel(){
    return currentVel;
  }
  
  public double getTimeDif(){
    return timeDifference;
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
