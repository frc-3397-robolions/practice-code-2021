// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.Elevator;

public class ControllerRunElevator extends Command {
  Elevator elevator = new Elevator();
  OI oi = new OI();
  double baseElevatorSpeed = RobotMap.baseElevatorSpeed;
  public ControllerRunElevator() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(oi.aHeld){
      elevator.runElevator(baseElevatorSpeed);
    }
    else if(oi.bHeld){
      elevator.runElevator(baseElevatorSpeed);
    }
    else{
      elevator.runElevator(0);
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
    elevator.runElevator(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    elevator.runElevator(0);
  }
}
