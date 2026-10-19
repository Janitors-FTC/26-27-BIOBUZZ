package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.Intake;

/**
 * Right trigger runs the intake. Power follows how far the trigger is pressed.
 */
@TeleOp(name = "Test: Intake", group = "Test")
public class IntakeTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        Intake intake = new Intake(hardwareMap);

        telemetry.addLine("Hold gamepad 1 right trigger.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            intake.setTrigger(gamepad1.right_trigger);

            telemetry.addData("Trigger", "%.2f", gamepad1.right_trigger);
            telemetry.addData("Power", "%.2f", intake.getPower());
            telemetry.update();
        }
    }
}
