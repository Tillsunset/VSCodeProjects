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

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;

public class DriveTrain extends SubsystemBase {

	private WPI_TalonSRX driveFR = talonSRXConstructor(1);
	private WPI_TalonSRX driveBR = talonSRXConstructor(2);
	private WPI_TalonSRX driveFL = talonSRXConstructor(3);
	private WPI_TalonSRX driveBL = talonSRXConstructor(4);

	private MotorControllerGroup left = new MotorControllerGroup(driveFL, driveBL);
	private MotorControllerGroup right = new MotorControllerGroup(driveFR, driveBR);

	public DifferentialDrive driveBase = new DifferentialDrive(left, right);

	double TicksToMeterRatio = 6 * Math.PI * 15 / (1024 * 39.37 * 3 * 12);

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

	private ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	private final DifferentialDriveOdometry m_odometry;

	// Must Tune
	public static final double ksVolts = 0.22;
	public static final double kvVoltSecondsPerMeter = 1.98;
	public static final double kaVoltSecondsSquaredPerMeter = 0.2;
	public static final double kPDriveVel = 8.5;
	public static final DifferentialDriveKinematics kDriveKinematics =
	new DifferentialDriveKinematics(1);// Wheel distance in meters

	public DriveTrain() {
		driveFR.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
		driveFL.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
		resetEncoders();

		right.setInverted(true);
		left.setInverted(false);

		gyro.calibrate();
		m_odometry = new DifferentialDriveOdometry(gyro.getRotation2d());
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

		if (timeDifference > 0.005) {
			if (Math.abs(leftPosDif) < 75) {// does not include noise
				leftVel = leftPosDif * TicksToMeterRatio / timeDifference;
			}

			if (Math.abs(rightPosDif) < 75) {// does not include noise
				rightVel = rightPosDif * TicksToMeterRatio / timeDifference;
			}
		}

		m_odometry.update(gyro.getRotation2d(),
			driveFL.getSelectedSensorPosition() * TicksToMeterRatio,
			driveFR.getSelectedSensorPosition() * TicksToMeterRatio);
	}

	public double getLeftVel() {
		return leftVel;
	}

	public double getRightVel() {
		return rightVel;
	}

	public double getTotalCurrent() {
		return driveFL.getStatorCurrent() + driveFR.getStatorCurrent() +
				driveBL.getStatorCurrent() + driveBR.getStatorCurrent();
	}

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}
	
	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		return new DifferentialDriveWheelSpeeds(leftVel, rightVel);
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		left.setVoltage(leftVolts);
		right.setVoltage(rightVolts);
		driveBase.feed();
	}

	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		m_odometry.resetPosition(pose, gyro.getRotation2d());
	}

	public void resetEncoders(){
		driveFR.setSelectedSensorPosition(0);
		driveFL.setSelectedSensorPosition(0);
	}

	private WPI_TalonSRX talonSRXConstructor(int x) {
		WPI_TalonSRX temp = new WPI_TalonSRX(x);

		temp.configContinuousCurrentLimit(40);// used standard play
		temp.configPeakCurrentLimit(44, 1000);// used for pushing, limit for stopping wheel spin
		temp.enableCurrentLimit(true);
		temp.configOpenloopRamp(.25);// fine tune for best responsiveness
		temp.configClosedloopRamp(0);// used for driving by encoders
		temp.setNeutralMode(NeutralMode.Brake);

		return temp;
	}
}
