// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;

/** Add your docs here. */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
private PWMVictorSPX frontLeftController;
private PWMVictorSPX frontRightController;
private PWMVictorSPX rearLeftController;
private PWMVictorSPX rearRightController;
private MecanumDrive mecanumDrive1;

public void runMecanumDrive(double ySpeed, double xSpeed, double zRotation){
  mecanumDrive1.driveCartesian(ySpeed, xSpeed, zRotation);
}
public DriveTrain(){
  frontLeftController = new PWMVictorSPX(RobotMap.frontLeftChannel);
addChild("Speed Controller 1",frontLeftController);
frontLeftController.setInverted(false);

  rearLeftController = new PWMVictorSPX(RobotMap.rearLeftChannel);
addChild("Speed Controller 2",rearLeftController);
rearLeftController.setInverted(false);

  frontRightController = new PWMVictorSPX(RobotMap.frontRightChannel);
addChild("Speed Controller 3",frontRightController);
frontRightController.setInverted(false);

  rearLeftController = new PWMVictorSPX(RobotMap.rearLeftChannel);
addChild("Speed Controller 4",rearLeftController);
rearLeftController.setInverted(false);

mecanumDrive1 = new MecanumDrive(frontLeftController, rearLeftController,
frontRightController, rearRightController);
addChild("Mecanum Drive 1",mecanumDrive1);
mecanumDrive1.setSafetyEnabled(true);
mecanumDrive1.setExpiration(0.1);
mecanumDrive1.setMaxOutput(1.0);
  
}
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
