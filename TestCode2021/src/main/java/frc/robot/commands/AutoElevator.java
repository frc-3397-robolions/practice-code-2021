// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;


public class AutoElevator extends Command {
  javax.swing.Timer timer = new Timer();
  Elevator elevator;
  double time;

  public AutoElevator(double time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.elevator = Robot.getElevator();
    requires(elevator);
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
    shooter.runElevator(RobotMap.baseElevatorSpeed);
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
    elevator.runElevator(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    elevator.runElevator(0);
  }
}
