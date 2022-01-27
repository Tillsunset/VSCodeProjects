/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PDP extends SubsystemBase {
	private PowerDistribution powerDistribution = new PowerDistribution(0, ModuleType.kCTRE);

	public PDP() {
		/*
		 * Creates a new ExampleSubsystem.
		 */
	}

	public double getVoltage() {
		return powerDistribution.getVoltage();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
