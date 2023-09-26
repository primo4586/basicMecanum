// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/** This is a demo program showing how to use Mecanum control with the MecanumDrive class. */
public class Robot extends TimedRobot {
  private static final int frontLeftTalonID = 0;
  private static final int frontLeftVictorID = 1;
  
  private static final int frontRightTalonID = 2;
  private static final int frontRightVictorID = 3;

  private static final int backRightTalonID = 4;
  private static final int backRightVictorID = 5;

  private static final int backLeftTalonID = 6;
  private static final int backLeftVictorID = 7;
  

  private static final int kJoystickChannel = 0;

  private MecanumDrive m_robotDrive;
  private Joystick m_stick;

  @Override
  public void robotInit() {
    WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(frontLeftTalonID);
    WPI_VictorSPX frontLeftVictor = new WPI_VictorSPX(frontLeftVictorID);
    MotorControllerGroup frontLeft = new MotorControllerGroup(frontLeftTalon, frontLeftVictor);
    
    WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(frontRightTalonID);
    WPI_VictorSPX frontRightVictor = new WPI_VictorSPX(frontRightVictorID);
    MotorControllerGroup frontRight = new MotorControllerGroup(frontRightTalon, frontRightVictor);

    WPI_TalonSRX backRightTalon = new WPI_TalonSRX(backRightTalonID);
    WPI_VictorSPX backRightVictor = new WPI_VictorSPX(backRightVictorID);
    MotorControllerGroup backRight = new MotorControllerGroup(backRightTalon, backRightVictor);

    WPI_TalonSRX backLeftTalon = new WPI_TalonSRX(backLeftTalonID);
    WPI_VictorSPX backLeftVictor = new WPI_VictorSPX(backLeftVictorID);
    MotorControllerGroup backLeft = new MotorControllerGroup(backLeftTalon, backLeftVictor);

    // Invert the right side motors.
    // You may need to change or remove this to match your robot.
    frontRight.setInverted(true);
    backRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    m_stick = new Joystick(kJoystickChannel);
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick Y axis for forward movement, X axis for lateral
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(-m_stick.getY(), -m_stick.getX(), -m_stick.getZ());
  }
}
