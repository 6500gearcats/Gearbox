package frc.robot.utility;

import static edu.wpi.first.units.Units.Seconds;
import static edu.wpi.first.units.Units.Volts;

import java.lang.StackWalker.Option;
import java.util.Optional;

import com.ctre.phoenix6.SignalLogger;
import com.ctre.phoenix6.controls.VoltageOut;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Config;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Direction;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Mechanism;
import frc.robot.subsystems.Example.ExampleSubsystem;

public class SysIDUtil {
        private Optional<SysIdRoutine> routine = Optional.empty();
        private final VoltageOut m_voltReq = new VoltageOut(0.0);
        private Optional<ExampleSubsystem> m_example = Optional.empty();

        /*
         * Uses Singal Logger from CTRE
         */
        public SysIDUtil(ExampleSubsystem example) {
                m_example = Optional.of(example);
                if (m_example.isPresent()) {
                        routine = Optional.of(
                                        new SysIdRoutine(new Config(null, // Use default ramp rate (1 V/s)
                                                        Volts.of(4), // Reduce dynamic step voltage to 4 to prevent
                                                                     // brownout
                                                        null,
                                                        (state) -> SignalLogger.writeString("flywheel state", state
                                                                        .toString())), // Use default timeout (10 s)
                                                        new Mechanism((volts) -> m_example.get().setControl(
                                                                        m_voltReq.withOutput(volts.in(Volts))),
                                                                        null,
                                                                        m_example.get())));
                }
        }

        public SysIDUtil() {
        }

        public Command sysIdQuasistatic(Direction direction) {
                return routine.get().quasistatic(direction);
        }

        public Command sysIdDynamic(Direction direction) {
                return routine.get().dynamic(direction);
        }

        public Optional<SequentialCommandGroup> sysIdAll() {
                if (m_example.isPresent()) {
                        return Optional.of(new SequentialCommandGroup(
                                        Commands.runOnce(SignalLogger::start),

                                        new RunCommand(() -> m_example.get().setControl(m_voltReq.withOutput(0)),
                                                        m_example.get())
                                                        .until(() -> Math.abs(m_example.get().getSpeed()) < 50),

                                        sysIdDynamic(Direction.kForward),

                                        new RunCommand(() -> m_example.get().setControl(m_voltReq.withOutput(0)),
                                                        m_example.get())
                                                        .until(() -> Math.abs(m_example.get().getSpeed()) < 50),

                                        sysIdDynamic(Direction.kReverse),

                                        new RunCommand(() -> m_example.get().setControl(m_voltReq.withOutput(0)),
                                                        m_example.get())
                                                        .until(() -> Math.abs(m_example.get().getSpeed()) < 50),

                                        sysIdQuasistatic(Direction.kForward),

                                        new RunCommand(() -> m_example.get().setControl(m_voltReq.withOutput(0)),
                                                        m_example.get())
                                                        .until(() -> Math.abs(m_example.get().getSpeed()) < 50),

                                        sysIdQuasistatic(Direction.kReverse),

                                        new RunCommand(() -> m_example.get().setControl(m_voltReq.withOutput(0)),
                                                        m_example.get())
                                                        .until(() -> Math.abs(m_example.get().getSpeed()) < 50),

                                        Commands.runOnce(SignalLogger::stop)));
                } else {
                        return Optional.empty();
                }
        }

        public boolean isPresent() {
                if (m_example.isPresent()) {
                        return true;
                }
                return false;
        }
}
