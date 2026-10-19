package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.config.RobotConstants;

/**
 * Intake and transfer. Both are the one motor named {@code intake}.
 */
public final class Intake {
    public static final String MOTOR_NAME = "intake";

    private final DcMotor motor;

    public Intake(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, MOTOR_NAME);
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(0.0);
    }

    /**
     * Runs the intake in proportion to a trigger.
     *
     * @param trigger 0.0 released, 1.0 fully pressed
     */
    public void setTrigger(double trigger) {
        double command = Math.max(0.0, Math.min(1.0, trigger));
        double power = RobotConstants.INTAKE_MIN_POWER
                + (RobotConstants.INTAKE_MAX_POWER - RobotConstants.INTAKE_MIN_POWER) * command;
        motor.setPower(power);
    }

    public double getPower() {
        return motor.getPower();
    }
}
