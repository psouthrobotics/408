package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by robotics on 1/30/16.
 */
public class StraightEncoders extends LinearOpMode {
    DcMotor leftTank;
    DcMotor leftMotor;
    DcMotor rightTank;
    DcMotor rightMotor;
    public void runOpMode() throws InterruptedException{
        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        leftTank.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        rightTank.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        while (opModeIsActive()) {
            telemetry.addData("Position Right", rightMotor.getCurrentPosition());
            telemetry.addData("Position Left", leftMotor.getCurrentPosition());

        }

    }
}
