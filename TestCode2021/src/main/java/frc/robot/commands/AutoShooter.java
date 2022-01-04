// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;


public class AutoShooter extends Command {
  javax.swing.Timer timer = new Timer();
  Shooter shooter;
  double time;
  double power;

  public AutoShooter(double time, double power) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.shooter = Robot.getShooter();
    requires(shooter);
    this.time = time;
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    shooter.runShooter(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(timer.get()>time)
    return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    shooter.runShooter(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    shooter.runShooter(0);
  }
}
