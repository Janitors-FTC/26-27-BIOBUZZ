package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.config.RobotConstants;

/**
 * Slant flower descorer on a servo.
 * Init and the raised pose sit {@code raise} degrees above the down angle.
 */
public final class FlowerDescorer {
    public static final String SERVO_NAME = "flowerdescorer";

    private final Servo servo;
    private double targetDegrees;

    public FlowerDescorer(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, SERVO_NAME);
        servo.setDirection(Servo.Direction.FORWARD);
        setDown(false);
    }

    public void setDown(boolean down) {
        targetDegrees = RobotConstants.FLOWER_DESCORER_DOWN_DEGREES
                + (down ? 0.0 : RobotConstants.FLOWER_DESCORER_RAISE_DEGREES);
        servo.setPosition(degreesToPosition(targetDegrees));
    }

    public double getTargetDegrees() {
        return targetDegrees;
    }

    public double getPosition() {
        return servo.getPosition();
    }

    private static double degreesToPosition(double degrees) {
        double position = degrees / RobotConstants.FLOWER_DESCORER_RANGE_DEGREES;
        return Math.max(0.0, Math.min(1.0, position));
    }
}
