package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.Range;


public class AutoClaw extends LinearOpMode {

    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    //claw motors
    DcMotor leftMClaw;
    DcMotor rightMClaw;
    //release serovos
    Servo releaseServo1;
    Servo releaseServo2;
    //gyro sensor
    GyroSensor gyro;


    @Override
    public void runOpMode() throws InterruptedException {
        //assign hardware to objects
        leftMotor = hardwareMap.dcMotor.get("left_Motor");
        rightMotor = hardwareMap.dcMotor.get("right_Motor");

        leftMClaw = hardwareMap.dcMotor.get("left_claw_motor");
        rightMClaw = hardwareMap.dcMotor.get("right_claw_motor");

        releaseServo1 = hardwareMap.servo.get("rs1");
        releaseServo2 = hardwareMap.servo.get("rs2");
        //assigning the hardware gyro to a variable
        gyro = hardwareMap.gyroSensor.get("gy");

        waitForStart();
        while (opModeIsActive()) {
            drive_forward();
        }
    }
    public void drive_forward() {
        double error;
        double kp;
        double p;
        double drive_speed;
        double straight;
        double rmotorRight;
        double lmotorRight;
        double rmotorLeft;
        double lmotorLeft;
        //straight value for gyro
        straight = 602;
        kp = 5;
        //the speed for the robot to drive forward as a percentage
        drive_speed = -0.7;
        //set initial speed for robot
        leftMotor.setPower(drive_speed);
        rightMotor.setPower(drive_speed);
        //error from setpoint to actual value according to the gyro
        error = 600 - gyro.getRotation();
        //creating proportional part of pid loop
        p = kp * error;
        p = p / 200;
        //right correction drive values
        rmotorRight = drive_speed - p;
        lmotorRight = drive_speed + p;
        //left correction drive values
        rmotorLeft = drive_speed - p;
        lmotorLeft = drive_speed + p;
        //clipping vlaues to not exceed 1
        if (rmotorLeft > 1){
            rmotorLeft = 1;
        }
        if (lmotorLeft > 1){
            lmotorLeft = 1;
        }
        if (rmotorRight > 1){
            rmotorRight = 1;
        }
        if (lmotorRight > 1){
            lmotorRight = 1;
        }
        if (gyro.getRotation() >= straight){
            rightMotor.setPower(Range.clip(rmotorRight, -1, 1));
            leftMotor.setPower(Range.clip(lmotorRight, -1, 1));
        }
        if (gyro.getRotation() <= straight){
            rightMotor.setPower(Range.clip(rmotorLeft, -1, 1));
            leftMotor.setPower(Range.clip(lmotorLeft, -1, 1));
        }
        telemetry.addData("pro", p);

    }

}
