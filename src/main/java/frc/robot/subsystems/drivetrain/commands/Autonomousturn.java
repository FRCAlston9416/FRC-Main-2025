package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class Autonomousturn extends Command{
    Drivetrain drivetrain;
    double speed = 0.25;
    private double meters;
    double degToMeters = 2.2/360.0;

    public Autonomousturn(Drivetrain drivetrain, double degrees){
        this.drivetrain = drivetrain;
        this.meters = degrees * degToMeters;
        SmartDashboard.putNumber("meters", this.meters);
        if(degrees < 0){
            speed = -speed;
        }

        addRequirements(drivetrain);
    }

    @Override
    public void initialize(){
        this.drivetrain.encoderReset();
  
    }

    @Override
    public void execute(){
        drivetrain.tankDrive(-speed, speed, 1);
        // drivetrain.setLeft(speed);
        // drivetrain.setRight(speed);
        SmartDashboard.putBoolean("isFinished", MathUtil.isNear(this.meters, drivetrain.getLeftPosition(), 0.05));
        SmartDashboard.putNumber("Speed", speed);
        SmartDashboard.putNumber("degrees", meters);
        SmartDashboard.putNumber("LeftPosition", drivetrain.getLeftPosition());


    }

    @Override
    public boolean isFinished(){
        // return drivetrain.getLeftPosition() > this.meters;
        return MathUtil.isNear(this.meters, drivetrain.getRightPosition(), 0.05);
        
        // return drivetrain.getLeftPosition() == drivetrain.getLeftPosition() + this.meters;
    }

    @Override
    public void end(boolean interrupted){
        System.out.println("hruhfh");
        drivetrain.tankDrive(0, 0, 1);
    }
}