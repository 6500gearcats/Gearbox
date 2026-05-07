// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Example;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /** Creates a new Drivetrain. */
  public Drivetrain() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("THIS IS NOT A REAL DRIVETRAIN. THIS IS A PLACEHOLDER FOR VISION CODE.");
  }

  public Supplier<Double> getAngularVel() {
    return () -> 0.0;
  }

  public Supplier<SwerveModulePosition[]> modulePositionsSupplier() {
    return () -> new SwerveModulePosition[4];
  }

  public Supplier<Rotation2d> rotationSupplier() {
    return () -> new Rotation2d(0);
  }

  public Supplier<Pose2d> poseSupplier() {
    return () -> new Pose2d();
  }
}
