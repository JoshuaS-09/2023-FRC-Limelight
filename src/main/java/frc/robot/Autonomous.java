package frc.robot;

public class Autonomous {
  private Drivetrain drivetrain;

  public void drive(double speedLeft, double speedRight) {
    drivetrain.drive.tankDrive(-speedLeft, -speedRight, false);
  }
}
