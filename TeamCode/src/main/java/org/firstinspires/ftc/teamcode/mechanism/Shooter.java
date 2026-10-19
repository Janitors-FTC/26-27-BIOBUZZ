package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.RobotConstants;

/**
 * Two 6000 rpm shooter motors on one power constant.
 * The motor named {@code shooter2} runs in the opposite direction.
 */
public final class Shooter {
    public static final String MOTOR_NAME = "shooter";
    public static final String MOTOR_2_NAME = "shooter2";

    private final DcMotor motor;
    private final DcMotor motor2;

    public Shooter(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, MOTOR_NAME);
        motor2 = hardwareMap.get(DcMotor.class, MOTOR_2_NAME);

        motor.setDirection(DcMotor.Direction.FORWARD);
        motor2.setDirection(DcMotor.Direction.REVERSE);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setRunning(false);
    }

    public void setRunning(boolean running) {
        double power = running ? RobotConstants.SHOOTER_POWER : 0.0;
        motor.setPower(power);
        motor2.setPower(power);
    }

    public double getPower() {
        return motor.getPower();
    }
}
