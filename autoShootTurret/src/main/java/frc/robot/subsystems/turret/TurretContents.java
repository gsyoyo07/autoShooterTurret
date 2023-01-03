package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TurretContents {

    
    // turret id 
    static int TurretId = 0; 
    
    //turet motor 
    public static WPI_TalonFX motor = new WPI_TalonFX(TurretId); 


    static{
        motor.config_kP(1, 0);
        motor.config_kI(1, 0);
        motor.config_kD(1, 0);
    }
}
