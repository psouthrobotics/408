package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;

//TO DO
//29in/s
public class BlueWithEncoders extends DriveWithoutEncoders {


    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);


    }


}
