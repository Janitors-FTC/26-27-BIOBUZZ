package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.ShooterBlocker;

/**
 * Parks the shooter blocker at 0 degrees. Gamepad 1 A toggles the run angle.
 */
@TeleOp(name = "Test: Shooter Blocker", group = "Test")
public class ShooterBlockerTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        ShooterBlocker blocker = new ShooterBlocker(hardwareMap);

        telemetry.addLine("Blocker starts at 0 degrees.");
        telemetry.addLine("Gamepad 1 A toggles the run angle.");
        telemetry.update();
        waitForStart();

        boolean running = false;
        boolean lastA = false;

        while (opModeIsActive()) {
            if (gamepad1.a && !lastA) {
                running = !running;
                blocker.setRunning(running);
            }
            lastA = gamepad1.a;

            telemetry.addData("Running", running);
            telemetry.addData("Target degrees", "%.1f", blocker.getTargetDegrees());
            telemetry.addData("Position", "%.3f", blocker.getPosition());
            telemetry.update();
        }
    }
}
