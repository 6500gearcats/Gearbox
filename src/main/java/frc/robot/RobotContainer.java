// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.photonvision.simulation.SimCameraProperties;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Example.Drivetrain;
import frc.robot.subsystems.vision.Vision;
import frc.robot.subsystems.vision.limelight.LimelightIO;
import frc.robot.subsystems.vision.photonvision.PhotonVisionIO;
import frc.robot.subsystems.vision.photonvision.PhotonVisionSimIO;

public class RobotContainer {

  private final Drivetrain drivetrain = new Drivetrain();
  private Vision m_vision;

  public RobotContainer() {

    // * These values are examples and should be determined based on your robot

    if (Robot.isReal()) {
      PhotonVisionIO m_photonVisionIO = new PhotonVisionIO("Thrifty_cam", false,
          new Translation3d(0.254, 0.254, 0.2032),
          new Rotation3d(0, Math.toRadians(62), Math.toRadians(42)));

      LimelightIO m_ll = new LimelightIO("limelight-gcd",
          true,
          drivetrain.rotationSupplier(),
          drivetrain.getAngularVel(),
          false);

      // * Can have any type and any number of vision sources

      m_vision = new Vision(
          drivetrain.rotationSupplier(),
          drivetrain.modulePositionsSupplier(),
          drivetrain.poseSupplier(),
          m_photonVisionIO,
          m_ll);

    } else if (Robot.isSimulation()) {
      SimCameraProperties cameraProp = new SimCameraProperties();
      cameraProp.setCalibration(640, 480, Rotation2d.fromDegrees(100));
      // Approximate detection noise with average and standard deviation error in
      // pixels.
      cameraProp.setCalibError(0.25, 0.08);
      cameraProp.setFPS(60);
      cameraProp.setAvgLatencyMs(35);
      cameraProp.setLatencyStdDevMs(5);
      PhotonVisionSimIO camSim = new PhotonVisionSimIO("photonvision", false, cameraProp,
          new Translation3d(0.1, 0, 0.5),
          new Rotation3d(0, Math.toRadians(-15), 0));
      m_vision = new Vision(
          drivetrain.rotationSupplier(),
          drivetrain.modulePositionsSupplier(),
          drivetrain.poseSupplier(),
          camSim);
    }

    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
