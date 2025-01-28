package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.AutonomousDriveForward;
import frc.robot.subsystems.drivetrain.commands.Autonomousturn;

public class Auto extends SequentialCommandGroup {
    public Auto(Drivetrain drivetrain){
        addCommands(
            new AutonomousDriveForward(drivetrain, 1),
            new AutonomousDriveForward(drivetrain, 1)

        );
    }
}
