package com.qualcomm.ftcrobotcontroller.opmodes;


//TO DO
//29in/s
public class Red extends DriveWithoutEncoders {


    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        go(1200, 0);
        go(2000, -200);
        leftMotor.setPower(0.5);
        leftTank.setPower(0.5);
        rightMotor.setPower(0.5);
        rightTank.setPower(0.5);
        sleep(10000);
        halt();


    }


}
