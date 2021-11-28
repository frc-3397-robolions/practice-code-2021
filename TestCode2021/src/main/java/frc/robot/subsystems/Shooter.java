// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ControllerRunShooter;

/** Add your docs here. */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private PWMVictorSPX shooterController1;
  private PWMVictorSPX shooterController2;

  public Shooter() {
    shooterController1 = new PWMVictorSPX(RobotMap.shooterChannel1);
    addChild("Shooter Controller 1",shooterController1);
    shooterController1.setInverted(false);
    shooterController2 = new PWMVictorSPX(RobotMap.shooterChannel2);
    addChild("Shooter Controller 2",shooterController2);
    shooterController2.setInverted(false);
  }
  public void runShooter(double speed){
    shooterController1.set(speed);
    shooterController2.set(speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ControllerRunShooter());
  }
}
