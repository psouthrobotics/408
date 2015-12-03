package com.qualcomm.ftcrobotcontroller.opmodes;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.util.Range;

//TO DO
//CLIP DRIVE VALUES TO 1 AND -1
//DO WHILE LOOP RATHER THAN WHILE
public class turnR extends LinearOpMode {
    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    //gyro sensor
    GyroSensor gyro;


    @Override
    public void runOpMode() throws InterruptedException {
        //assign hardware to objects
        leftMotor = hardwareMap.dcMotor.get("left_Motor");
        rightMotor = hardwareMap.dcMotor.get("right_Motor");
        //assigning the hardware gyro to a variable
        gyro = hardwareMap.gyroSensor.get("gy");

        waitForStart();
        while (opModeIsActive()) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0 );
    }
}