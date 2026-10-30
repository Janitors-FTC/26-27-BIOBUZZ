package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.RobotConstants;

/**
 * Shooter blocker. Rest is 0 degrees. Running moves to 75 degrees and holds.
 */
public final class ShooterBlocker {
    public static final String SERVO_NAME = "shooterblocker";

    private final Servo servo;
    private double targetDegrees = RobotConstants.SHOOTER_BLOCKER_REST_DEGREES;

    public ShooterBlocker(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, SERVO_NAME);
        servo.setDirection(Servo.Direction.FORWARD);
        setRunning(false);
    }

    public void setRunning(boolean running) {
        targetDegrees = running
                ? RobotConstants.SHOOTER_BLOCKER_RUN_DEGREES
                : RobotConstants.SHOOTER_BLOCKER_REST_DEGREES;
        double position = targetDegrees / RobotConstants.SHOOTER_BLOCKER_RANGE_DEGREES;
        servo.setPosition(Math.max(0.0, Math.min(1.0, position)));
    }

    public double getTargetDegrees() {
        return targetDegrees;
    }

    public double getPosition() {
        return servo.getPosition();
    }
}
