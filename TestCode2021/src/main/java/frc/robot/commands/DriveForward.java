// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;

public class DriveForward extends Command {
  double time;
  Timer timer = new Timer();
  DriveTrain driveTrain;
  public DriveForward(double time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.driveTrain = Robot.getDriveTrain();
    requires(driveTrain);
    this.time = time;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      driveTrain.runMecanumDrive(RobotMap.baseForwardSpeed, 0, 0);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(timer.get()>time){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    timer.stop();
    driveTrain.runMecanumDrive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    timer.stop();
    driveTrain.runMecanumDrive(0, 0, 0);
  }
}
