package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.mechanism.ShooterBlocker;

/**
 * Match control for the mechanisms. Drive is not in this OpMode yet.
 *
 * Gamepad 1 right trigger runs the intake in proportion to the trigger.
 * Gamepad 1 A toggles the shooter and the shooter blocker together.
 */
@TeleOp(name = "Match", group = "Match")
public class MatchTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        Intake intake = new Intake(hardwareMap);
        ShooterBlocker blocker = new ShooterBlocker(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);

        telemetry.addLine("Right trigger: intake");
        telemetry.addLine("A: shooter and blocker");
        telemetry.update();
        waitForStart();

        boolean shooterOn = false;
        boolean lastA = false;

        while (opModeIsActive()) {
            intake.setTrigger(gamepad1.right_trigger);

            if (gamepad1.a && !lastA) {
                shooterOn = !shooterOn;
                blocker.setRunning(shooterOn);
                shooter.setRunning(shooterOn);
            }
            lastA = gamepad1.a;

            telemetry.addData("Intake", "%.2f", intake.getPower());
            telemetry.addData("Shooter", shooterOn ? "on" : "off");
            telemetry.addData("Shooter power", "%.2f", shooter.getPower());
            telemetry.addData("Blocker", "%.0f deg", blocker.getTargetDegrees());
            telemetry.update();
        }
    }
}
