// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
  public static final int kDriverControllerPort = 0;
  }
  public static final class Drivetrain{
    public static final class RightMotors{
          public static final Integer kRightMotor1_Port = 1;
            public static final Integer kRightMotor2_Port = 4;}

    public static final class LeftMotors{
                  public static final Integer kLeftMotor1_Port = 2;
                        public static final Integer kLeftMotor2_Port = 3;}
  } 
public static final class Launcher{
  public static final Integer kLaunchMotor_port = 6;
  public static final Integer kSecondaryMotor_port = 5;
}
}
