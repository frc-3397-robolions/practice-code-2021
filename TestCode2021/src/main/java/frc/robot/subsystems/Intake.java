// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ControllerRunIntake;

/** Add your docs here. */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private PWMVictorSPX intakeController;
  public Intake(){
    intakeController = new PWMVictorSPX(RobotMap.intakeChannel);
addChild("Speed Controller 5",intakeController);
intakeController.setInverted(false);
  }
  public void runIntake(double speed){
    intakeController.set(speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ControllerRunIntake());
  }
}
