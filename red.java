package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.util.Range;

//TO DO
//29in/s
public class Red extends Drive {


    @Override
    public void runOpMode() throws InterruptedException {
        gyro_cal();

        waitForStart();
        //less than 600 is left more than right
        //straight is 600
        go(1200, 600);
        go(4000, 75);
        while (opModeIsActive()){
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        }
        halt();


    }


}
