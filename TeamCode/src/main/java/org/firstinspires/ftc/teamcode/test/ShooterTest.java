package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.config.RobotConstants;
import org.firstinspires.ftc.teamcode.mechanism.Shooter;

/**
 * Runs the shooter so its power can be tried one step at a time.
 * Gamepad 1 A raises the power. Y lowers it.
 */
@TeleOp(name = "Test: Shooter", group = "Test")
public class ShooterTest extends LinearOpMode {

    private static final double POWER_STEP = 0.05;

    @Override
    public void runOpMode() {
        Shooter shooter = new Shooter(hardwareMap);

        telemetry.addLine("Shooter runs after start.");
        telemetry.addLine("A raises power. Y lowers power.");
        telemetry.update();
        waitForStart();

        double power = RobotConstants.SHOOTER_POWER;
        shooter.setPower(power);
        boolean lastA = false;
        boolean lastY = false;

        while (opModeIsActive()) {
            if (gamepad1.a && !lastA) {
                power = Math.min(1.0, power + POWER_STEP);
            }
            if (gamepad1.y && !lastY) {
                power = Math.max(0.0, power - POWER_STEP);
            }
            lastA = gamepad1.a;
            lastY = gamepad1.y;
            power = Math.round(power * 100.0) / 100.0;
            shooter.setPower(power);

            telemetry.addData("Power", "%.2f", power);
            telemetry.addLine("A +0.05. Y -0.05.");
            telemetry.update();
        }
    }
}
