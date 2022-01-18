/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Camera extends SubsystemBase {
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry ty = table.getEntry("ty");

	public Camera() {
		/*
		 * Creates a new ExampleSubsystem.
		 */
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}

	public NetworkTableEntry getTy() {
		return ty;
	}
}
