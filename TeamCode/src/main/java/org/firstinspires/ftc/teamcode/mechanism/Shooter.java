package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.RobotConstants;

/**
 * One 6000 rpm shooter motor. {@code shooter2} runs in reverse.
 */
public final class Shooter {
    public static final String MOTOR_NAME = "shooter1";

    private final DcMotor motor;

    public Shooter(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, MOTOR_NAME);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        setRunning(false);
    }

    public void setRunning(boolean running) {
        motor.setPower(running ? RobotConstants.SHOOTER_POWER : 0.0);
    }

    public double getPower() {
        return motor.getPower();
    }
}
