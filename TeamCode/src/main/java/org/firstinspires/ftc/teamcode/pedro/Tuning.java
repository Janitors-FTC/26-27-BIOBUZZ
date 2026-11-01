package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.Swerve;
import com.pedropathing.revhub.localizers.OctoQuadLocalizer;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;

import org.firstinspires.ftc.teamcode.pedro.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.OctoQuadTuner;
import org.firstinspires.ftc.teamcode.pedro.procedures.Tests;

public class Tuning {
    @Tuner
    public static Procedure octoquadTuner() {
        return new OctoQuadTuner();
    }
    @Tuner
    public static Procedure tests() {
        return new Tests(
                Constants::createDrivetrain,
                hardwareMap -> new OctoQuadLocalizer(hardwareMap, Constants.localizerConfig),
                null
        );
    }

    @Tuner
    public static Procedure foresightTuner() {
        return new ForesightTuner(
                hardwareMap -> new OctoQuadLocalizer(hardwareMap, Constants.localizerConfig),
                Constants::createDrivetrain
        );
    }

}
