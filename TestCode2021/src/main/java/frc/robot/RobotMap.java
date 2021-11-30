// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public class RobotMap {
  // Set this to the port of the Xbox Controller, found in the Drive Station
  public static final int xbControllerPort = 0;
  // Not sure if we have a gyro. If we do it probably needs calibration before we can use it. Shouldn't break anything if we just leave it as is until thats figured out.
  public static final int gyroPort = 0;

  // Set these to the port number of each PWM
  public static final int frontLeftChannel = 0;
  public static final int rearLeftChannel = 1;
  public static final int frontRightChannel = 2;
  public static final int rearRightChannel = 3;
  public static final int intakeChannel = 0;
  public static final int elevatorChannel = 0;
  public static final int shooterChannel1 = 0;
  public static final int shooterChannel2 = 0;


  // These constants determine the base speed for running these actions
  public static final double baseIntakeSpeed = 0.5;
  public static final double baseForwardSpeed = 0.5;
  public static final double baseTurnSpeed = 0.5;
  public static final double baseElevatorSpeed = 0.5;
  // currently running both motors in the gearbox at this speed, purposely low so it wont break if one needs to be inverted
  public static final double baseShooterSpeed = 0.1;


 
}
