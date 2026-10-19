package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.Shooter;

/**
 * Gamepad 1 A toggles both shooter motors. {@code shooter2} is reversed.
 */
@TeleOp(name = "Test: Shooter", group = "Test")
public class ShooterTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Shooter shooter = new Shooter(hardwareMap);

        telemetry.addLine("Gamepad 1 A toggles both shooter motors.");
        telemetry.update();
        waitForStart();

        boolean running = false;
        boolean lastA = false;

        while (opModeIsActive()) {
            if (gamepad1.a && !lastA) {
                running = !running;
                shooter.setRunning(running);
            }
            lastA = gamepad1.a;

            telemetry.addData("Running", running);
            telemetry.addData("Power", "%.2f", shooter.getPower());
            telemetry.addLine("shooter is forward. shooter2 is reversed.");
            telemetry.update();
        }
    }
}
