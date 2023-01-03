package frc.robot.subsystems.turret;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class TurretSS extends SubsystemBase {

    private WPI_TalonFX motor;
    public double deadBand;
    public double maxRotation =420;

    private final static TurretSS INSTANCE = new TurretSS();
    public static TurretSS getInstance() {return INSTANCE;}


    private TurretSS() {
        motor = TurretContents.motor;
        motor.setNeutralMode(NeutralMode.Brake);
        setZeroMid();
    }

    public void autoTurret(double limeLightTY){
        setDesiredAngle(limeLightTY);
    }


    private void setDesiredAngle(double desiredAngle) {
        if(desiredAngle+getAngle() <= -420 || desiredAngle+getAngle() >= 420 ){
            motor.set(mathCon.deggresToFalconTicks(flippedValue(desiredAngle)));
        }
        else{motor.set(mathCon.deggresToFalconTicks(desiredAngle));}
    }

    public double getAngle() {
        return motor.getSelectedSensorPosition() + deadBand;
    }

    private void setZeroMid(){
        motor.set(ControlMode.Position,-mathCon.deggresToFalconTicks(deadBand));
    }


    
    public double flippedValue(double desiredAngle){

        double difference =getAngle()- desiredAngle;
        double positiveDistance = getAngle()-difference-360;
        
        return desiredAngle>0 ? positiveDistance:-positiveDistance;  
    }


}

