package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.config.RobotConstants;

/**
 * Axon MAX shooter blocker. Starts at 0 degrees and swings to the run angle when running.
 */
public final class ShooterBlocker {
    public static final String SERVO_NAME = "shooterblocker";

    /** Axon pulse width. Wider than the Control Hub's default servo range. */
    private static final double PWM_MIN_MICROSECONDS = 500;
    private static final double PWM_MAX_MICROSECONDS = 2500;

    private final Servo servo;
    private double targetDegrees = RobotConstants.SHOOTER_BLOCKER_REST_DEGREES;

    public ShooterBlocker(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, SERVO_NAME);
        if (servo instanceof ServoImplEx) {
            ServoImplEx axon = (ServoImplEx) servo;
            axon.setPwmRange(new PwmControl.PwmRange(PWM_MIN_MICROSECONDS, PWM_MAX_MICROSECONDS));
            axon.setPwmEnable();
        }
        servo.setDirection(Servo.Direction.FORWARD);
        setRunning(false);
    }

    public void setRunning(boolean running) {
        targetDegrees = running
                ? RobotConstants.SHOOTER_BLOCKER_RUN_DEGREES
                : RobotConstants.SHOOTER_BLOCKER_REST_DEGREES;
        servo.setPosition(degreesToPosition(targetDegrees));
    }

    public double getTargetDegrees() {
        return targetDegrees;
    }

    public double getPosition() {
        return servo.getPosition();
    }

    private static double degreesToPosition(double degrees) {
        double position = degrees / RobotConstants.AXON_RANGE_DEGREES;
        return Math.max(0.0, Math.min(1.0, position));
    }
}
