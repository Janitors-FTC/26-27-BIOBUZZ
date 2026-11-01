package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Mecanum drive. Left stick forward and strafe, right stick turn.
 * The left motors are reversed so a positive forward command drives the robot forward.
 */
public final class TankDrive {
    public static final String FRONT_LEFT = "lf";
    public static final String BACK_LEFT = "lb";
    public static final String FRONT_RIGHT = "rf";
    public static final String BACK_RIGHT = "rb";

    private final DcMotor frontLeft;
    private final DcMotor backLeft;
    private final DcMotor frontRight;
    private final DcMotor backRight;

    private double frontLeftPower;
    private double backLeftPower;
    private double frontRightPower;
    private double backRightPower;

    public TankDrive(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, FRONT_LEFT);
        backLeft = hardwareMap.get(DcMotor.class, BACK_LEFT);
        frontRight = hardwareMap.get(DcMotor.class, FRONT_RIGHT);
        backRight = hardwareMap.get(DcMotor.class, BACK_RIGHT);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        drive(0.0, 0.0, 0.0);
    }

    /**
     * @param forward positive drives forward
     * @param strafe positive strafes right
     * @param turn positive turns clockwise
     */
    public void drive(double forward, double strafe, double turn) {
        frontLeftPower = forward + strafe + turn;
        frontRightPower = forward - strafe - turn;
        backLeftPower = forward - strafe + turn;
        backRightPower = forward + strafe - turn;

        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));
        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    public double getFrontLeftPower() {
        return frontLeftPower;
    }

    public double getFrontRightPower() {
        return frontRightPower;
    }

    public double getBackLeftPower() {
        return backLeftPower;
    }

    public double getBackRightPower() {
        return backRightPower;
    }
}
