package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;

public class Constants {
    public static final class DriveConstants {
        // Chassis configuration
        // Distance between centers of right and left wheels on robot
        public static final double kTrackWidth = Units.inchesToMeters(25.5);
        // Distance between front and back wheels on robot
        public static final double kWheelBase = Units.inchesToMeters(25.5);

        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));
    }

    public static final class VisionConstants {
        public static final String kCameraNameTag = "Microsoft_LifeCam_HD-3000";
        public static final String kCameraNameNote = "Microsoft_LifeCam_VX-5000";
        public static final String kCameraNameGlobal = "Global_Shutter_Camera";
        // Cam mounted facing forward, half a meter forward of center, half a meter up
        // from center.
        public static final Transform3d kRobotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5),
                new Rotation3d(0, 0, 180));

        // The standard deviations of our vision estimated poses, which affect
        // correction rate
        // ! (Fake values. Experiment and determine estimation noise on an actual
        // robot.)
        public static final Matrix<N3, N1> kSingleTagStdDevs = VecBuilder.fill(.5, .5, Units.degreesToRadians(20));
        public static final Matrix<N3, N1> kMultiTagStdDevs = VecBuilder.fill(.5, .5, Units.degreesToRadians(5));
        public static final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(27);

        public static final double TARGET_HEIGHT_METERS = Units.feetToMeters(5);
        // Angle between horizontal and the camera.
        public static final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(-28);

        // How far from the target we want to be
        public static final double GOAL_RANGE_METERS = Units.feetToMeters(3);

        public static final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout
                .loadField(AprilTagFields.k2026RebuiltAndymark);
    }
}