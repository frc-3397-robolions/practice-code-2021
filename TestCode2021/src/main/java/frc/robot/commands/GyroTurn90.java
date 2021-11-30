// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class GyroTurn90 extends Command {
  DriveTrain driveTrain = new DriveTrain();
  Gyro gyro = new AnalogGyro(RobotMap.gyroPort);
  // Tweak this if the robot isn't completing the turn right
  double turnFactor = 0.03;
  double error;
  public GyroTurn90() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(driveTrain);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    error = 90 - gyro.getAngle();
    driveTrain.runMecanumDrive(0, 0, turnFactor*error);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(error > -1 && error < 1){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    driveTrain.runMecanumDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    driveTrain.runMecanumDrive(0, 0, 0);
  }
}
