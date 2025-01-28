// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.drivetrain.commands.AutonomousDriveForward;
import frc.robot.subsystems.drivetrain.commands.Autonomousturn;
import frc.robot.subsystems.drivetrain.commands.Shoot;
 
// import edu.wpi.first.wpilibj2.command.Command;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drivetrain = new Drivetrain();
  private final Launcher launcher = new Launcher();
 //  private final AutoPicker autoPicker;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

//  private Object autoPicker;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    CameraServer.startAutomaticCapture();
    SlewRateLimiter leftFilter = new SlewRateLimiter(3);
    SlewRateLimiter rightFilter = new SlewRateLimiter(3);
    // Configure the trigger bindings

    // Tank drive
    this.drivetrain.setDefaultCommand(new RunCommand(() ->{
      // With slew rate limit
      // drivetrain.tankDrive(leftFilter.calculate(m_driverController.getLeftY()), rightFilter.calculate(m_driverController.getRightY()), 3);
      drivetrain.setArcadeDrive(leftFilter.calculate(m_driverController.getLeftY()), rightFilter.calculate(m_driverController.getRightX()), false);
      
      // No slew rate limit
      // drivetrain.tankDrive(m_driverController.getLeftY(), m_driverController.getRightY(), true);
      }, drivetrain));

      // Intake
      this.launcher.setDefaultCommand(new RunCommand(() ->{
        double leftTrigger = m_driverController.getLeftTriggerAxis();
        double rightTrigger = m_driverController.getRightTriggerAxis();
        
  
        // launcher.setlaunchMotor(m_driverController.getLeftX(), m_driverController.getRightX());
        // launcher.setsecondaryMotor(m_driverController.getLeftX(), m_driverController.getRightX());
        launcher.setlaunchMotor(m_driverController.getLeftTriggerAxis(), m_driverController.getRightTriggerAxis());
        launcher.setsecondaryMotor(m_driverController.getLeftTriggerAxis(), m_driverController.getRightTriggerAxis());
      }, launcher));

    configureBindings();
}
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`


    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(drivetrain.exampleMethodCommand());
  
  }

  public Command getAutonomousCommand(){
    // return new Autonomousturn(drivetrain, 180);
    return new Shoot(launcher);
  }

}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */


  