// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Example;

import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  TalonFX m_motor = new TalonFX(6500);

  public ExampleSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("THIS IS AN EXAMPLE SUBSYSTEM! REPLACE THIS WITH YOUR OWN SUBSYSTEM!");
  }

  public void setControl(ControlRequest control) {
    // Set voltage to motor here
    m_motor.setControl(control);
  }

  public double getSpeed() {
    return m_motor.get();
  }
}
