// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {

  // Autonomous
  // private Autonomous auto;
  private Drivetrain drivetrain;
  // Limelight
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private NetworkTableEntry ty = table.getEntry("ty");
  private NetworkTableEntry ta = table.getEntry("ta");
    
  private final XboxController controller = new XboxController(0);

  public double leftSpeed;
  public double rightSpeed;
  public double steeringAdjust;

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

    // m_rightMotor.setInverted(true);
    drivetrain = new Drivetrain();
  }

  public void robotPeriodic() {
    // read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //posts to dashboard periodically
    SmartDashboard.putString("Area", // Shows area of camera taken up by part to the camera.
      "Area = " + String.format("%.3f", area));
    SmartDashboard.putString("Y", // Shows the vertical location of the object to the camera.
      "Y = " + String.format("%.3f", y));
    SmartDashboard.putString("X", // Shows the horizontal location of the object to the camera.
      "X = " + String.format("%.3f", x));
    // SmartDashboard.putString("Distance", // Shows the distance between the limelight and the object.
    //   "Distance(MTEST) = " + String.format("%.3f", distanceToGoal));
    SmartDashboard.putString("Controller Left Y", // Test if dashboard is working.
      "LeftStickY = " + controller.getLeftY());
  }

  @Override
  public void teleopPeriodic() {
    double x = tx.getDouble(0.0);
    double Kp = 0.03; // 0.1;
    
    if (controller.getRightBumper()) {      
      steeringAdjust = Kp * x;
      double driveSpeed = -0.25;
      leftSpeed = driveSpeed + steeringAdjust;
      rightSpeed = driveSpeed - steeringAdjust;

      SmartDashboard.putString("Left Speed", // Test if dashboard is working.
          "Left Speed = " + leftSpeed);
      SmartDashboard.putString("Right Speed", // Test if dashboard is working.
          "Right Speed = " + rightSpeed);

      drivetrain.drive.tankDrive(leftSpeed, rightSpeed);
    } else {
      drivetrain.drive.tankDrive(0, 0);
    }
  }
}
