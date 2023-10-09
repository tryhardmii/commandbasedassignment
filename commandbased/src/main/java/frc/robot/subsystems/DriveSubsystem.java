// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;


public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private final WPI_VictorSPX driveFrontLeft = new WPI_VictorSPX(1);
  private final WPI_TalonSRX driveFrontRight = new WPI_TalonSRX(3);
  private final CANSparkMax driveBackLeft = new CANSparkMax(2, MotorType.kBrushed);
  private final WPI_VictorSPX driveBackRight = new WPI_VictorSPX(4);

  private final MotorControllerGroup driveLeft = new MotorControllerGroup(driveFrontLeft, driveBackLeft);
  private final MotorControllerGroup driveRight = new MotorControllerGroup(driveFrontRight, driveBackRight);
  private final DifferentialDrive driveRobot = new DifferentialDrive(driveLeft, driveRight);


  private final AHRS gyro = new AHRS(SPI.Port.kMXP);
  private final Field2d field = new Field2d();

  public double getGyroYaw() {
    return (gyro.getYaw());
  }

  public double getGyroPitch() {
    return (gyro.getPitch());
  }

  public void resetGyro() {
    gyro.reset();
  }

  public double getGyroRoll() {
    return (gyro.getRoll());
  }

  public DriveSubsystem() {
    driveFrontLeft.setNeutralMode(NeutralMode.Brake);
    driveFrontRight.setNeutralMode(NeutralMode.Brake);
    driveBackRight.setNeutralMode(NeutralMode.Brake);
    driveBackLeft.setIdleMode(CANSparkMax.IdleMode.kBrake);
    driveRight.setInverted(true);
    SmartDashboard.putData("Field", field);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Roll", getGyroRoll());
    SmartDashboard.putNumber("Pitch", getGyroPitch());
    SmartDashboard.putNumber("Yaw", getGyroYaw());
  }

  public void setMotor(double forwardSpeed, double turnSpeed) {
    driveRobot.arcadeDrive(forwardSpeed, turnSpeed);
  }
}
