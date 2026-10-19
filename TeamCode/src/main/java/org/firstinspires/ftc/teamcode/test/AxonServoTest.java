package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Locale;
import java.util.Map;

/**
 * Fast back-and-forth on the servo named "axon".
 *
 * Starts in continuous mode, sweeping 0.30–0.70 in 0.30 seconds. Gamepad 1 A
 * switches to the slow step sweep, and A again returns to continuous.
 * The 500–2500 µs pulse is enabled and left on, which is the signal the
 * Axons chirped on when continuous mode was first added.
 */
@TeleOp(name = "Test: Axon Servo", group = "Test")
public class AxonServoTest extends LinearOpMode {

    private static final String SERVO_NAME = "axon";
    private static final double MIN_POWER = 0.30;
    private static final double MAX_POWER = 0.70;
    private static final double FAST_SWEEP_SECONDS = 0.30;
    private static final double STEP_SWEEP_SECONDS = 2.0;

    @Override
    public void runOpMode() {
        Servo servo = null;
        CRServo crServo = findCrServo();
        if (crServo == null) {
            servo = findServo();
        }
        if (servo == null && crServo == null) {
            while (!isStopRequested()) {
                telemetry.addLine("No servo named \"" + SERVO_NAME + "\" and nothing on port 3.");
                listDevices("CR servos", hardwareMap.crservo.entrySet());
                listDevices("Servos", hardwareMap.servo.entrySet());
                telemetry.update();
                idle();
            }
            return;
        }

        PwmControl pwm = crServo instanceof CRServoImplEx
                ? (CRServoImplEx) crServo
                : servo instanceof ServoImplEx ? (ServoImplEx) servo : null;
        if (pwm != null) {
            pwm.setPwmRange(new PwmControl.PwmRange(500, 2500));
            pwm.setPwmEnable();
        }
        if (crServo != null) {
            crServo.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
            servo.setDirection(Servo.Direction.FORWARD);
        }

        ElapsedTime timer = new ElapsedTime();
        boolean fastMode = true;
        boolean lastA = false;

        while (!isStopRequested()) {
            if (gamepad1.a && !lastA) {
                fastMode = !fastMode;
                timer.reset();
            }
            lastA = gamepad1.a;

            double sweepSeconds = fastMode ? FAST_SWEEP_SECONDS : STEP_SWEEP_SECONDS;
            double phase = (timer.seconds() % sweepSeconds) / sweepSeconds;
            double triangle = phase < 0.5 ? phase * 2.0 : (1.0 - phase) * 2.0;
            double power = MIN_POWER + (MAX_POWER - MIN_POWER) * triangle;

            if (pwm != null) {
                pwm.setPwmEnable();
            }
            if (crServo != null) {
                crServo.setPower(power);
            } else {
                servo.setPosition((power + 1.0) / 2.0);
            }

            HardwareDevice device = crServo != null ? crServo : servo;
            telemetry.addData("Driving", crServo != null ? "CR servo" : "servo");
            telemetry.addData("Connection", device.getConnectionInfo());
            telemetry.addData("Mode", fastMode ? "continuous" : "step");
            telemetry.addData("Power", "%.2f", power);
            telemetry.addData("PWM", pwm != null && pwm.isPwmEnabled() ? "enabled" : "not enabled");
            telemetry.addLine("Gamepad 1 A toggles step mode.");
            telemetry.update();
            idle();
        }
    }

    private CRServo findCrServo() {
        CRServo named = null;
        CRServo port3 = null;
        for (Map.Entry<String, CRServo> entry : hardwareMap.crservo.entrySet()) {
            if (entry.getKey().toLowerCase(Locale.US).contains(SERVO_NAME)) {
                named = entry.getValue();
            }
            if (isPort3(entry.getValue())) {
                port3 = entry.getValue();
            }
        }
        return named != null ? named : port3;
    }

    private Servo findServo() {
        Servo named = null;
        Servo port3 = null;
        for (Map.Entry<String, Servo> entry : hardwareMap.servo.entrySet()) {
            if (entry.getKey().toLowerCase(Locale.US).contains(SERVO_NAME)) {
                named = entry.getValue();
            }
            if (isPort3(entry.getValue())) {
                port3 = entry.getValue();
            }
        }
        return named != null ? named : port3;
    }

    private boolean isPort3(HardwareDevice device) {
        return device.getConnectionInfo().toLowerCase(Locale.US).endsWith("port 3");
    }

    private <T extends HardwareDevice> void listDevices(String heading, Iterable<Map.Entry<String, T>> devices) {
        telemetry.addLine(heading + ":");
        boolean any = false;
        for (Map.Entry<String, T> entry : devices) {
            any = true;
            telemetry.addData(entry.getKey(), entry.getValue().getConnectionInfo());
        }
        if (!any) {
            telemetry.addLine("  none");
        }
    }
}
