package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
    public NetworkTable networkTable = NetworkTableInstance.getDefault().getTable("limelight");;

    //public double pipeline;

    public void setPipeline(int pipeline) {
		networkTable.getEntry("pipeline").setNumber(pipeline);
		SmartDashboard.putNumber("Pipeline Mode", pipeline);
	}
}
