// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;

import java.io.ObjectInputFilter.Config;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.SparkMax.IdleMode;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.DifferentialDriveAccelerationLimiter;


public class Drivetrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
private final SparkMax rightmotor1 = new SparkMax(Constants.Drivetrain.RightMotors.kRightMotor1_Port, MotorType.kBrushless);
private final SparkMax rightmotor2 = new SparkMax(Constants.Drivetrain.RightMotors.kRightMotor2_Port, MotorType.kBrushless);
private final SparkMax leftmotor1 = new SparkMax(Constants.Drivetrain.LeftMotors.kLeftMotor1_Port, MotorType.kBrushless);
private final SparkMax leftmotor2 = new SparkMax(Constants.Drivetrain.LeftMotors.kLeftMotor2_Port, MotorType.kBrushless);
RelativeEncoder rightRelativeEncoder = rightmotor1.getEncoder();
RelativeEncoder leftRelativeEncoder = leftmotor1.getEncoder();
private DifferentialDrive differentialDrive;



 public Drivetrain() {
  SparkMaxConfig leftmotor1Config = new SparkMaxConfig();
  leftmotor1Config
    .inverted(false)
    .idleMode(IdleMode.kBrake);
  
  leftmotor1Config.encoder
    .positionConversionFactor(1/17.5);

  leftmotor1.configure(leftmotor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  SparkMaxConfig leftmotor2Config = new SparkMaxConfig();
  leftmotor2Config
    .inverted(false)
    .idleMode(IdleMode.kBrake);

  leftmotor2Config.encoder
    .positionConversionFactor(1/17.5);
  
  leftmotor2Config.follow(leftmotor1);
  leftmotor2.configure(leftmotor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  SparkMaxConfig rightmotor1Config = new SparkMaxConfig();
  rightmotor1Config
    .inverted(true)
    .idleMode(IdleMode.kBrake);

    rightmotor1Config.encoder
    .positionConversionFactor(1/17.5);
 
  rightmotor1.configure(rightmotor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  SparkMaxConfig rightmotor2Config = new SparkMaxConfig();
  rightmotor2Config
    .inverted(true)
    .idleMode(IdleMode.kBrake);

    rightmotor2Config.encoder
    .positionConversionFactor(1/17.5);
    rightmotor2Config.follow(rightmotor1);

  rightmotor2.configure(rightmotor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

// outdated from 2024 season
  //leftmotor1.setInverted(false);
  // leftmotor2.setInverted(false);
  // rightmotor1.setInverted(true);
  // rightmotor2.setInverted(true);
  // leftmotor1.IdleMode(IdleMode.kBrake);
  // leftmotor2.setIdleMode(IdleMode.kBrake);
  // rightmotor1.setIdleMode(IdleMode.kBrake);
  // rightmotor2.setIdleMode(IdleMode.kBrake);
  // leftmotor2.follow(leftmotor1); 
  // rightmotor2.follow(rightmotor1);
  // leftRelativeEncoder.setPositionConversionFactor(1/17.5);
  // rightRelativeEncoder.setPositionConversionFactor(1/17.5);

leftRelativeEncoder.setPosition(0);
rightRelativeEncoder.setPosition(0);

// differentialDrive = new DifferentialDrive(leftmotor1, rightmotor1);




 }
 public void setLeft(double power){
  System.out.println(power);
leftmotor1.set(power);
 }
  public void setRight(double power){
rightmotor1.set(power); 

 }


  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("leftEncoder", leftRelativeEncoder.getPosition());
    SmartDashboard.putNumber("rightEncoder", rightRelativeEncoder.getPosition());
    SmartDashboard.putNumber("LeftPower", leftmotor1.get());
    SmartDashboard.putNumber("RightPower", rightmotor1.get());
  }

public void encoderReset(){
leftRelativeEncoder.setPosition(0);
rightRelativeEncoder.setPosition(0);
}
  
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  
  public void tankDrive(double leftSpeed, double rightSpeed, double exponent  ) {
    // deadband to zero out joystick values close to zero
    leftSpeed = MathUtil.applyDeadband(leftSpeed, 0.01); 
    rightSpeed = MathUtil.applyDeadband(rightSpeed, 0.01);

    // clamp to limit max
    leftSpeed = MathUtil.clamp(leftSpeed, -0.7, 0.7);
    rightSpeed = MathUtil.clamp(rightSpeed, -0.7, 0.7);

    // square decimal inputs to further reduce values
    if (exponent > 1) {
      leftSpeed = Math.copySign(Math.pow(leftSpeed,exponent), leftSpeed);
      rightSpeed = Math.copySign(Math.pow(rightSpeed,exponent), rightSpeed);
    }
    setLeft(leftSpeed);
    setRight(rightSpeed);

  }

  public void setArcadeDrive(double throttle, double rotation, boolean squareInputs){
    differentialDrive.arcadeDrive(throttle, -1*rotation, squareInputs);
  }

  public double getRightPosition(){
    return rightRelativeEncoder.getPosition();
  }

  public double getLeftPosition(){
    return leftRelativeEncoder.getPosition();
  }
}


// red white blues in the sky