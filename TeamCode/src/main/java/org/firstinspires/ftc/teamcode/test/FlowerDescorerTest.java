package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.FlowerDescorer;

/**
 * Starts the flower descorer raised. Gamepad 1 right bumper toggles down and up.
 */
@TeleOp(name = "Test: Flower Descorer", group = "Test")
public class FlowerDescorerTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        FlowerDescorer descorer = new FlowerDescorer(hardwareMap);

        telemetry.addLine("Descorer starts raised.");
        telemetry.addLine("Gamepad 1 right bumper toggles down and up.");
        telemetry.update();
        waitForStart();

        boolean down = false;
        boolean lastBumper = false;

        while (opModeIsActive()) {
            if (gamepad1.right_bumper && !lastBumper) {
                down = !down;
                descorer.setDown(down);
            }
            lastBumper = gamepad1.right_bumper;

            telemetry.addData("Down", down);
            telemetry.addData("Target degrees", "%.1f", descorer.getTargetDegrees());
            telemetry.addData("Position", "%.3f", descorer.getPosition());
            telemetry.update();
        }
    }
}
