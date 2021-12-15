// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TestMotors;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Thread m_visionThread;
  static Elevator elevator;
  static Shooter shooter;
  static Intake intake;
  static DriveTrain driveTrain;
  static OI oi;


  public static OI getOI() {
    return oi;
  }

  Command autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  static SendableChooser<Double> shootSpeedChooser = new SendableChooser<>();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    driveTrain = new DriveTrain();
    oi = new OI();
    shooter = new Shooter();
    elevator = new Elevator();
    intake = new Intake();
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
    m_visionThread =
        new Thread(
            () -> {
              // Get the Axis camera from CameraServer
              AxisCamera camera = CameraServer.getInstance().addAxisCamera("axis-camera.local");
              // Set the resolution
              camera.setResolution(640, 480);

              // Get a CvSink. This will capture Mats from the camera
              CvSink cvSink = CameraServer.getInstance().getVideo();
              // Setup a CvSource. This will send images back to the Dashboard
              CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

              // Mats are very memory expensive. Lets reuse this Mat.
              Mat mat = new Mat();

              // This cannot be 'true'. The program will never exit if it is. This
              // lets the robot stop this thread when restarting robot code or
              // deploying.
              while (!Thread.interrupted()) {
                // Tell the CvSink to grab a frame from the camera and put it
                // in the source mat.  If there is an error notify the output.
                if (cvSink.grabFrame(mat) == 0) {
                  // Send the output the error.
                  outputStream.notifyError(cvSink.getError());
                  // skip the rest of the current iteration
                  continue;
                }
                // Put a rectangle on the image
                Imgproc.rectangle(
                    mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                // Give the output stream a new image to display
                outputStream.putFrame(mat);
              }
            });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  }

  public static double getShootSpeedChooser() {
    return shootSpeedChooser.getSelected();
  }

  public static DriveTrain getDriveTrain(){
    return driveTrain;
  }
  public static Elevator getElevator() {
    return elevator;
  }
  public static Intake getIntake() {
    return intake;
  }
  public static Shooter getShooter() {
    return shooter;
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
    //driveTrain = ((driveTrain==null) ? new DriveTrain():driveTrain);
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
    //driveTrain = ((driveTrain==null) ? new DriveTrain():driveTrain);
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
    try (TestMotors testMotors = new TestMotors()) {
      // testMotors.runTestMotor(0);
      // testMotors.runTestMotor(1);
      // testMotors.runTestMotor(2);
      // testMotors.runTestMotor(3);
      testMotors.runTestMotor(RobotMap.frontLeftChannel);
      testMotors.runTestMotor(RobotMap.frontRightChannel);
      testMotors.runTestMotor(RobotMap.rearLeftChannel);
      testMotors.runTestMotor(RobotMap.rearRightChannel);
    }

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  
}
