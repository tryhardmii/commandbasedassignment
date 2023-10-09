package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Timer;

public class Auto extends ParallelCommandGroup{
    public Auto (DriveSubsystem driveSubsystem, IntakeSubsystem intakeSubsystem, Timer timer){
        new ParallelCommandGroup(
            new IntakeCommand(intakeSubsystem, true).withTimeout(2),
            new ArcadeDriveCommand(driveSubsystem, () -> -0.7, () -> 0.8).withTimeout(2)
        );

    }
    
}
