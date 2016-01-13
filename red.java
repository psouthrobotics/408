package com.qualcomm.ftcrobotcontroller.opmodes;

//TO DO
//29in/s
public class red extends Drive {


    @Override
    public void runOpMode() throws InterruptedException {
       

        waitForStart();
        //less than 600 is left more than right
        //straight is 600
        go(1200, 0);
        go(4000, -525);
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        halt();


    }


}
