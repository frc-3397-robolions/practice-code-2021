// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Autonomous;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.TestMotors;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  static DriveTrain driveTrain;
  static OI oi;


  public static OI getOI() {
    return oi;
  }

  Command autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Double> shootSpeedChooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    // Start the camera, theoretically
    CameraServer.getInstance().startAutomaticCapture();
    // WPIlib code for choosing an auto phase from SmartDB, we don't do that yet.
    // m_chooser.setDefaultOption("Default Auto", new Autonomous());
    // How to add an option: chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    // My code for hopefully choosing the shooter speed from SmartDB
    shootSpeedChooser.setDefaultOption("100% Power", RobotMap.baseShooterSpeed);
    shootSpeedChooser.addOption("75% Power", RobotMap.baseShooterSpeed*0.75);
    shootSpeedChooser.addOption("50% Power", RobotMap.baseShooterSpeed*0.5);
    SmartDashboard.putData("Shooter Power", shootSpeedChooser);

  }

  public double getShootSpeedChooser() {
    return shootSpeedChooser.getSelected();
  }

  public static DriveTrain getDriveTrain(){
    return driveTrain;
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This function is called once each time the robot enters Disabled mode. You can use it to reset
   * any subsystem information you want to clear when the robot is disabled.
   */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the chooser code above
   * (like the commented example) or additional comparisons to the switch structure below with
   * additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    driveTrain = ((driveTrain==null) ? new DriveTrain():driveTrain);
    autonomousCommand.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    driveTrain = ((driveTrain==null) ? new DriveTrain():driveTrain);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  @Override
  public void testInit() {
    // TODO Auto-generated method stub
    super.testInit();
    TestMotors testMotors = new TestMotors();
    // testMotors.runTestMotor(0);
    // testMotors.runTestMotor(1);
    // testMotors.runTestMotor(2);
    // testMotors.runTestMotor(3);
    testMotors.runTestMotor(RobotMap.frontLeftChannel);
    testMotors.runTestMotor(RobotMap.frontRightChannel);
    testMotors.runTestMotor(RobotMap.rearLeftChannel);
    testMotors.runTestMotor(RobotMap.rearRightChannel);

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
