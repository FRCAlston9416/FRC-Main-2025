package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Launcher;

public class Shoot extends SequentialCommandGroup {
    public Shoot(Launcher launcher) {
        this.addCommands(
                new ParallelRaceGroup(new RunCommand(() -> {
                    launcher.setlaunchMotor(1, 0);
                    launcher.setsecondaryMotor(1, 0); System.err.println("stringed");
                }, launcher), new WaitCommand(2)), new InstantCommand(() -> {
                    launcher.setlaunchMotor(0, 0);
                    launcher.setsecondaryMotor(0, 0);
                }, launcher));
                addRequirements(launcher);
    }
}