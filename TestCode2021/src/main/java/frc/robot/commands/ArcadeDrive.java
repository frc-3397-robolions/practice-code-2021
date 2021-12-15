// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends Command {
    private SlewRateLimiter ySpeedLimiter;
    private SlewRateLimiter xSpeedLimiter;
    private SlewRateLimiter zRotationLimiter;
    private double ySpeed;
    private double xSpeed;
    private double zRotation;
    private DriveTrain driveTrain;
  public ArcadeDrive() {
    this.driveTrain = Robot.getDriveTrain();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(driveTrain);
    ySpeedLimiter = new SlewRateLimiter(1);
    xSpeedLimiter = new SlewRateLimiter(1);
    zRotationLimiter = new SlewRateLimiter(1);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    OI oi = Robot.getOI();
    ySpeed = xSpeedLimiter.calculate(oi.getStickX()); 
    xSpeed =  -ySpeedLimiter.calculate(oi.getStickY());
    zRotation = zRotationLimiter.calculate(oi.getStickZ());
    // print stick values to console
    //System.out.printf("Y: %f   X: %f   Z: %f \n", oi.getStickY(), oi.getStickX(), oi.getStickZ());
    driveTrain.runMecanumDrive(ySpeed, xSpeed, zRotation);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
