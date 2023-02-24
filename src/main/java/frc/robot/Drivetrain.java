package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class Drivetrain {
  public PWMVictorSPX driveLeftFront, driveLeftBack, driveRightFront, driveRightBack;
  public MotorControllerGroup driveLeft, driveRight;
  public DifferentialDrive drive;

  public Drivetrain() {
    driveLeftFront = new PWMVictorSPX(0);
    driveLeftBack = new PWMVictorSPX(1);
    driveLeft = new MotorControllerGroup(driveLeftFront, driveLeftBack);
    driveLeft.setInverted(false);

    driveRightFront = new PWMVictorSPX(2);
    driveRightBack = new PWMVictorSPX(3);
    driveRight = new MotorControllerGroup(driveRightFront, driveRightBack);
    driveRight.setInverted(true);

    drive = new DifferentialDrive(driveLeft, driveRight);
    drive.setDeadband(0.02);
    drive.setMaxOutput(1.0);
  }
}
