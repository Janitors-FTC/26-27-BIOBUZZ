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
    public static final double SHOOTER_POWER = 0.783;
// assuming 4700 rpm. divide amt by 6000
    /**
     * Axon MAX travel from servo position 0.0 to 1.0, in degrees.
     * Match this to the operating angle saved in the Axon programmer.
     */
    public static final double AXON_RANGE_DEGREES = 360.0;

    public static final double SHOOTER_BLOCKER_REST_DEGREES = 0.0;
    public static final double SHOOTER_BLOCKER_RUN_DEGREES = 75.0;
}
