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
  private Autonomous auto;
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private NetworkTableEntry ty = table.getEntry("ty");
  private NetworkTableEntry ta = table.getEntry("ta");
  private float Kp = -0.1f;
  
// private final PWMSparkMax m_leftMotor = new PWMSparkMax(0);
  // private final PWMSparkMax m_rightMotor = new PWMSparkMax(1);
  // private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final XboxController controller = new XboxController(0);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

    // m_rightMotor.setInverted(true);
  }

  public void robotPeriodic() {
    // read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //posts to dashboard periodically
    SmartDashboard.putString("DB/String 0", // Shows area of camera taken up by part to the camera.
      "Area = " + String.format("%.3f", area));
    SmartDashboard.putString("DB/String 1", // Shows the vertical location of the object to the camera.
      "Y = " + String.format("%.3f", y));
    SmartDashboard.putString("DB/String 2", // Shows the horizontal location of the object to the camera.
      "X = " + String.format("%.3f", x));
    SmartDashboard.putString("DB/String 9", // Test if dashboard is working.
      "LeftStickY = " + controller.getLeftY());
  }

  @Override
  public void autonomousPeriodic()  {
    double x = tx.getDouble(0.0);
    double steering_adjust = Kp * x;

    Double left_command =+ steering_adjust;
    Double right_command =- steering_adjust;

    auto.drive(left_command, right_command);
  }

  @Override
  public void teleopPeriodic() {}
}
