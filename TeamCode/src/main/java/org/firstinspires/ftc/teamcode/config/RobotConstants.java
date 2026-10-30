package org.firstinspires.ftc.teamcode.config;

/**
 * Mechanism positions and limits. Pedro follower values stay in {@code pedro.Constants}.
 */
public final class RobotConstants {
    private RobotConstants() {
    }

    /** Intake power when the trigger is released. */
    public static final double INTAKE_MIN_POWER = 0.0;

    /** Intake power when the trigger is fully pressed. */
    public static final double INTAKE_MAX_POWER = 1.0;

    /** Open-loop power for the shooter motor while the shooter is on. */
    public static final double SHOOTER_POWER = 1.0;
// assuming 4700 rpm. divide amt by 6000
    // made back to 1.0 for 6000 constants
    public static final double SHOOTER_BLOCKER_REST_DEGREES = 0.0;
    public static final double SHOOTER_BLOCKER_RUN_DEGREES = 75.0;

    /** Degrees across servo positions 0 to 1 on the hub's normal signal. */
    public static final double SHOOTER_BLOCKER_RANGE_DEGREES = 270.0;

    /** Flower descorer angle when it is down. */
    public static final double FLOWER_DESCORER_DOWN_DEGREES = 0.0;

    /** Degrees above the down angle for init and the raised pose. */
    public static final double FLOWER_DESCORER_RAISE_DEGREES = 15.0;

    /**
     * Servo travel from position 0.0 to 1.0 on the flower descorer.
     * Match this to that servo's programmed range.
     */
    public static final double FLOWER_DESCORER_RANGE_DEGREES = 360.0;
}
