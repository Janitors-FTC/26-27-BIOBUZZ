package org.firstinspires.ftc.teamcode.pedro.swerve;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.revhub.drivetrains.Swerve;
import com.pedropathing.revhub.drivetrains.SwerveConfig;
import com.pedropathing.revhub.drivetrains.SwervePod;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CorrectedSwerve extends Swerve {
    public CorrectedSwerve(HardwareMap hardwareMap, SwerveConfig config, SwervePod... pods) {
        super(hardwareMap, config, pods);
    }

    public void applyDrive(DrivePowers powers) {
        DrivePowers newPowers = new DrivePowers(
                powers.forward(),
                -powers.strafe(),
                -powers.turn()

        );
        super.applyDrive(newPowers);
    }
}