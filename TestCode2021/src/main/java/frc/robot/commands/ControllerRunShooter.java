// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ControllerRunShooter extends Command {
  Shooter shooter = new Shooter();
  OI oi = new OI();
  Robot robot = new Robot();

  public ControllerRunShooter() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(oi.rBumperHeld){
    shooter.runShooter(robot.getShootSpeedChooser());
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
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
