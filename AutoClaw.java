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
        straight = 599;
        kp = 2 ;
        //the speed for the robot to drive forward as a percentage
        drive_speed = -0.7;
        //set initial speed for robot
        leftMotor.setPower(drive_speed);
        rightMotor.setPower(drive_speed);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        //error from setpoint to actual value according to the gyro
        //positive error if turning left neg if right
        error = straight - gyro.getRotation();
        //creating proportional part of pid loop
        p = kp * error;
        p = p / 50;
        if (p > 0.05){

        }

        //right correction drive values
        telemetry.addData("pro", p);
        telemetry.addData("gyro", gyro.getRotation());

    }

}
