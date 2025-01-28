package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class AutonomousDriveForward extends Command{
    Drivetrain drivetrain;
    double speed = 0.25;
    private double meters;

    public AutonomousDriveForward(Drivetrain drivetrain, double meters){
        this.drivetrain = drivetrain;
        this.meters = meters;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize(){
        System.out.println("hi");
        this.drivetrain.encoderReset();
        if(meters < 0){
            speed = -speed;
        }
    }

    @Override
    public void execute(){
        drivetrain.tankDrive(speed, speed, 1);
        // drivetrain.setLeft(speed);
        // drivetrain.setRight(speed);
        SmartDashboard.putBoolean("isFinished", MathUtil.isNear(this.meters, drivetrain.getLeftPosition(), 0.05));
        SmartDashboard.putNumber("Speed", speed);
        SmartDashboard.putNumber("Meters", meters);
        SmartDashboard.putNumber("LeftPosition", drivetrain.getLeftPosition());


    }

    @Override
    public boolean isFinished(){
        // return drivetrain.getLeftPosition() > this.meters;
        return MathUtil.isNear(this.meters, drivetrain.getLeftPosition(), 0.05);
        
        // return drivetrain.getLeftPosition() == drivetrain.getLeftPosition() + this.meters;
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("hruhfh");
        drivetrain.tankDrive(0, 0, 1);
    }
}