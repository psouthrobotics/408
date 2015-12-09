package com.qualcomm.ftcrobotcontroller.opmodes;

//TO DO
//29in/s
public class Red extends Drive {


    @Override
    public void runOpMode() throws InterruptedException {
        gyro_cal();

        waitForStart();
        //less than 600 is left more than right
        //straight is 600
        go(1200, 0);
        go(4000, -525);
        while (opModeIsActive()){
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        }
        halt();


    }


}
