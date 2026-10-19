# Team code conventions

Package root: `org.firstinspires.ftc.teamcode`

## Packages

| Package | Holds |
|---|---|
| `auton` | One OpMode per autonomous routine |
| `teleop` | One OpMode per driver mode |
| `test` | Hardware bring-up OpModes |
| `config` | `RobotConstants`: mechanism positions and limits |
| `mechanism` | One class per mechanism. That class owns its hardware-map names |
| `drive` | `SwerveDrive`: module hardware-map names, then teleop drive |
| `pedro` | `Constants` and `Tuning` only |
| `pedro.procedures` | Shipped Pedro 3 tuners. Do not restyle them |

OpModes schedule work. Hardware math lives in `drive` or a mechanism class, not in the OpMode. Pedro tuner output stays in `pedro.Constants`. Do not copy it into `RobotConstants`.

The Pedro 3 package is `pedro`. Do not rename it to `pedroPathing`.

## Names

| Kind | Pattern | Example |
|---|---|---|
| Package | one lowercase word | `drive` |
| Class and file | PascalCase, file name matches the class | `SwerveDrive.java` |
| OpMode class | task plus `Auto`, `TeleOp`, or `Test` | `SpecimenAuto`, `DriveTeleOp`, `AxonServoTest` |
| Field and method | camelCase, full words | `leftFrontPower` |
| Hardware-map string | camelCase, written once | `"leftFrontDrive"` |
| Java constant holding that string | UPPER_SNAKE | `LEFT_FRONT_DRIVE` |
| Fixed number | UPPER_SNAKE | `INTAKE_HOLD_POWER` |
| Pedro fields | names the tuner pastes | `drivetrainConfig`, `localizerConfig`, `foresightConfig` |

One public class per file. Hardware-map strings match the Robot Configuration exactly and are declared on the class that owns the device. `SwerveDrive` is the only place swerve module names are written.
