// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.IntakeCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems and commands are defined here...
        private final DriveSubsystem driveSubsystem = new DriveSubsystem();
        private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

        // Replace with CommandPS4Controller or CommandJoystick if needed
        private final Joystick driverController = new Joystick(0);

        private final Joystick operatorController = new Joystick(1);

        // A chooser for autonomous commands
        SendableChooser<Command> chooser = new SendableChooser<>();

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the trigger bindings
                configureBindings();

                        driveSubsystem.setDefaultCommand(
                                new ArcadeDriveCommand(driveSubsystem,
                                                () -> -driverController.getRawAxis(1),
                                                (() -> -driverController.getRawAxis(4)
                                                               )));
                intakeSubsystem.setDefaultCommand(new IntakeCommand(intakeSubsystem, false));
        }

        private void configureBindings() {
                // Intake
                new JoystickButton(operatorController, 2)
                                .onTrue(new IntakeCommand(intakeSubsystem, false)); 
                new JoystickButton(operatorController, 1)
                                .onTrue(new IntakeCommand(intakeSubsystem, true));

        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // An example command will be run in autonomous
                return chooser.getSelected();
        }
}
