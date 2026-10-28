package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.FlowerDescorer;
import org.firstinspires.ftc.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.mechanism.ShooterBlocker;

/**
 * Match control for the mechanisms. Drive is not in this OpMode yet.
 *
 * Gamepad 1 right trigger runs the intake in proportion to the trigger.
 * Gamepad 1 A toggles the shooter and the shooter blocker together.
 * Gamepad 1 right bumper toggles the flower descorer down and up.
 */
@TeleOp(name = "Match", group = "Match")
public class MatchTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        Intake intake = new Intake(hardwareMap);
        ShooterBlocker blocker = new ShooterBlocker(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        FlowerDescorer descorer = new FlowerDescorer(hardwareMap);

        telemetry.addLine("Right trigger: intake");
        telemetry.addLine("A: shooter and blocker");
        telemetry.addLine("Right bumper: flower descorer");
        telemetry.update();
        waitForStart();

        boolean shooterOn = false;
        boolean lastA = false;
        boolean descorerDown = false;
        boolean lastBumper = false;

        while (opModeIsActive()) {
            intake.setTrigger(gamepad1.right_trigger);

            if (gamepad1.a && !lastA) {
                shooterOn = !shooterOn;
                blocker.setRunning(shooterOn);
                shooter.setRunning(shooterOn);
            }
            lastA = gamepad1.a;

            if (gamepad1.right_bumper && !lastBumper) {
                descorerDown = !descorerDown;
                descorer.setDown(descorerDown);
            }
            lastBumper = gamepad1.right_bumper;

            telemetry.addData("Intake", "%.2f", intake.getPower());
            telemetry.addData("Shooter", shooterOn ? "on" : "off");
            telemetry.addData("Shooter power", "%.2f", shooter.getPower());
            telemetry.addData("Blocker", "%.0f deg", blocker.getTargetDegrees());
            telemetry.addData("Descorer", descorerDown ? "down" : "up");
            telemetry.addData("Descorer degrees", "%.0f", descorer.getTargetDegrees());
            telemetry.update();
        }
    }
}
