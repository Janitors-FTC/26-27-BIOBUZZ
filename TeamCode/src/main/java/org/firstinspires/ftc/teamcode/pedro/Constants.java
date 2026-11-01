package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.controllers.Controller;
import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.CoaxialPod;
import com.pedropathing.revhub.drivetrains.CoaxialPodConfig;
import com.pedropathing.revhub.drivetrains.Swerve;
import com.pedropathing.revhub.drivetrains.SwerveConfig;
import com.pedropathing.revhub.localizers.OctoQuadConfig;
import com.pedropathing.revhub.localizers.OctoQuadLocalizer;
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.math.Vector2D;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.pedro.swerve.CorrectedSwerve;

public class Constants {
    public static final double dtLength = 351.52500;
    public static final double dtWidth = 314.76250;
    public static SwerveConfig driveConfig = new SwerveConfig(
            c -> {
                c.zeroPowerBehavior.set(SwerveConfig.ZeroPowerBehavior.IGNORE_ANGLE_CHANGES);
                c.manualBrakeMode.set(true);
                c.voltageCompensation.set(false);
            }
    );

    public static CoaxialPodConfig rightBack = new CoaxialPodConfig(
            c -> {
                c.name.set("rightBack");
                c.motorName.set("rb");
                c.servoName.set("rbTurn");
                c.servoEncoderName.set("rbTurnEncoder");
                c.turnController.set(Controller.pid(0.3, 0, 0.005)
                        .plus(Controller.proportionalFeedforward(0)));
                c.driveDirection.set(DcMotorSimple.Direction.FORWARD);
                c.servoDirection.set(DcMotorSimple.Direction.FORWARD);
                c.analogMinVoltage.set(0.003);
                c.analogMaxVoltage.set(3.179);
                c.podOffset.set(Vector2D.cartesian(-dtLength, -dtWidth));
                c.angleOffsetRad.set(3.64);
                c.encoderReversed.set(true);
            }
    );

    public static CoaxialPodConfig leftFront = new CoaxialPodConfig(
            c -> {
                c.name.set("leftFront");
                c.motorName.set("lf");
                c.servoName.set("lfTurn");
                c.servoEncoderName.set("lfTurnEncoder");
                c.turnController.set(Controller.pid(0.3, 0, 0.005)
                        .plus(Controller.proportionalFeedforward(0)));
                c.driveDirection.set(DcMotorSimple.Direction.REVERSE);
                c.servoDirection.set(DcMotorSimple.Direction.FORWARD);
                c.analogMinVoltage.set(0.003);
                c.analogMaxVoltage.set(3.206);
                c.podOffset.set(Vector2D.cartesian(dtLength, dtWidth));
                c.angleOffsetRad.set(2.91);
                c.encoderReversed.set(true);
            }
    );

    public static CoaxialPodConfig rightFront = new CoaxialPodConfig(
            c -> {
                c.name.set("rightFront");
                c.motorName.set("rf");
                c.servoName.set("rfTurn");
                c.servoEncoderName.set("rfTurnEncoder");
                c.turnController.set(Controller.pid(0.3, 0, 0.005)
                        .plus(Controller.proportionalFeedforward(0)));
                c.driveDirection.set(DcMotorSimple.Direction.FORWARD);
                c.servoDirection.set(DcMotorSimple.Direction.FORWARD);
                c.analogMinVoltage.set(0.008);
                c.analogMaxVoltage.set(3.195);
                c.podOffset.set(Vector2D.cartesian(dtLength, -dtWidth));
                c.angleOffsetRad.set(4.67);
                c.encoderReversed.set(true);
            }
    );

    public static CoaxialPodConfig leftBack = new CoaxialPodConfig(
            c -> {
                c.name.set("leftBack");
                c.motorName.set("lb");
                c.servoName.set("lbTurn");
                c.servoEncoderName.set("lbTurnEncoder");
                c.turnController.set(Controller.pid(0.3, 0, 0.0086)
                        .plus(Controller.proportionalFeedforward(0)));
                c.driveDirection.set(DcMotorSimple.Direction.REVERSE);
                c.servoDirection.set(DcMotorSimple.Direction.FORWARD);
                c.analogMinVoltage.set(0.001);
                c.analogMaxVoltage.set(3.219);
                c.podOffset.set(Vector2D.cartesian(-dtLength, dtWidth));
                c.angleOffsetRad.set(3.55);
                c.encoderReversed.set(true);
            }

    );
    public static OctoQuadConfig localizerConfig = new OctoQuadConfig(c -> {
        c.name.set("octoquad");
        c.xPodPort.set(0);
        c.yPodPort.set(1);
        c.ticksPerUnit.set(505.316944406);
        c.xPodOffset.set(-0.0);
        c.yPodOffset.set(-0.0);
        c.xPodDirection.set(OctoQuad.EncoderDirection.FORWARD);
        c.yPodDirection.set(OctoQuad.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
        c.i2cRecoveryMode.set(OctoQuad.I2cRecoveryMode.MODE_1_PERIPH_RST_ON_FRAME_ERR);
        c.headingScalar.set(1.0210343443159844);

    });
    public static Follower create(HardwareMap h) {
        return new Follower(
                new OctoQuadLocalizer(h, localizerConfig),
                createDrivetrain(h),
                null
        );
    }
    public static Drivetrain createDrivetrain(HardwareMap h) {
        CoaxialPod leftFrontPod = new CoaxialPod(h, leftFront);
        CoaxialPod rightFrontPod = new CoaxialPod(h, rightFront);
        CoaxialPod leftBackPod = new CoaxialPod(h, leftBack);
        CoaxialPod rightBackPod = new CoaxialPod(h, rightBack);
        return new CorrectedSwerve(h, driveConfig,
                leftFrontPod, rightFrontPod, leftBackPod, rightBackPod);
    }

}
