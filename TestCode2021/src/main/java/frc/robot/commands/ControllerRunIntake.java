// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Intake;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ControllerRunIntake extends Command {
  Intake intake = Robot.getIntake();
  OI oi = Robot.getOI();
  public ControllerRunIntake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    intake.runIntake(oi.getTriggerTotal()*RobotMap.baseIntakeSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    intake.runIntake(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    intake.runIntake(0);
  }
}
