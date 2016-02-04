package com.qualcomm.ftcrobotcontroller.opmodes;

//TO DO
//29in/s
public class blue extends Drive {


    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        //less than 600 is left more than right
        //straight is 600
        go(1500, 0);
        go(4000, -400);
        leftTank.setPower(0.5);
        leftMotor.setPower(0.5);
        rightTank.setPower(0.5);
        rightMotor.setPower(0.5);
        sleep(27200);
        halt();


    }

}