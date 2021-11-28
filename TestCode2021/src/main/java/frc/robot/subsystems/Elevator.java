// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ControllerRunElevator;

/** Add your docs here. */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private PWMVictorSPX elevatorController;
  public Elevator(){
    elevatorController = new PWMVictorSPX(RobotMap.elevatorChannel);
    addChild("Elevator Controller",elevatorController);
    elevatorController.setInverted(false);
  }
  public void runElevator(double speed){
    elevatorController.set(speed);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ControllerRunElevator());
  }
}
