// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class Autonomous extends CommandGroup {
  /** Add your docs here. */
  public Autonomous() {
    addSequential(AutoShooter(2));
    addParallel(AutoShooter(5, RobotMap.baseShooterSpeed));
    addParallel(AutoElevator(5));
    addParallel(AutoIntake(5));
    addSequential(DriveBackward(1));
    addSequential(TimedTurn(3));
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
