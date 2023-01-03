package frc.robot.subsystems.turret;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utill.conversions;


public class TurretSS extends SubsystemBase {

    private final static TurretSS INSTANCE = new TurretSS();
    public static TurretSS getInstance() {return INSTANCE;}

    private final WPI_TalonFX motor = TurretContents.motor;;
    public double deadBand;
     double maxRotation =420;

    private TurretSS() {
        motor.setNeutralMode(NeutralMode.Brake);
        setZeroMid();
    }

    public void autonumisTurret(double limeLightTY){
        setDesiredAngle(limeLightTY);
    }


    private void setDesiredAngle(double desiredAngle) {
        double doableDesiredAngle = overLimit(desiredAngle) ? flippedValue(desiredAngle):desiredAngle;
        motor.set(conversions.deggresToFalconTicks());
    }

 

    //checks if the motor will rotate too much
    private boolean overLimit(double desiredAngle){
        if(desiredAngle+getAngle() <= -420 || desiredAngle+getAngle() >= 420 ){
            return true;
        }
        return false;
    }

    public double getAngle() {
        return motor.getSelectedSensorPosition() + deadBand;
    }

    private void setZeroMid(){
        motor.set(ControlMode.Position,-conversions.deggresToFalconTicks(deadBand));
    }


    
    public double flippedValue(double desiredAngle){

        double difference =getAngle()- desiredAngle;
        double positiveDistance = getAngle()-difference-360;
        
        return desiredAngle>0 ? positiveDistance:-positiveDistance;  
         
    }


}

