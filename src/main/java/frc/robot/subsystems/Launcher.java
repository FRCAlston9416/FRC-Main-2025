package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.util.concurrent.TimeUnit;


public class Launcher extends SubsystemBase{
    private final SparkMax launchMotor = new SparkMax(Constants.Launcher.kLaunchMotor_port, MotorType.kBrushless);
    private final SparkMax secondaryMotor = new SparkMax(Constants.Launcher.kSecondaryMotor_port, MotorType.kBrushless);
    

    public Launcher() {
        launchMotor.setInverted(false);
        secondaryMotor.setInverted(true);
        
    }
    // leftTrigger: backwards
    // rightTrigger: forward
    // top motor
    public void setlaunchMotor(double leftTrigger, double rightTrigger) {
        leftTrigger = leftTrigger * -1; // LT up, RT down
        double power = leftTrigger + rightTrigger;
        launchMotor.set(-power); 
    }

    // bottom motor
    public void setsecondaryMotor(double leftTrigger, double rightTrigger) {
         
        leftTrigger = leftTrigger * -1;
        double power = leftTrigger + rightTrigger;
        
        // Only use top motor when intaking it doesn't over-intake
        if (power > 0) {
            power = 0;
        }

        // when shooting, have a time delay so top motor can reach max speed first
        if (power < 0) {
            System.out.println(this.launchMotor.getAppliedOutput());
            if ((this.launchMotor.getAppliedOutput()) < 1) {
                power = 0;
            }
            else {
                power = leftTrigger + rightTrigger;
            }
        }
        secondaryMotor.set(power);

    }
}
