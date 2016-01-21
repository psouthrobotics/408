package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Tank extends OpMode {
    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor rightTank;
    DcMotor leftTank;
    Servo servo;
    //drive values
    double left;
    double right;
    //scale for expo
    double a;





    @Override
    public void init() {

        //Mapping physical motors to variable
        leftMotor = hardwareMap.dcMotor.get("left_Motor");
        rightMotor = hardwareMap.dcMotor.get("right_Motor");
        leftTank = hardwareMap.dcMotor.get("left_tank");
        rightTank = hardwareMap.dcMotor.get("right_tank");
        servo = hardwareMap.servo.get("servo");
        //scale value
        a = 20;
        leftMotor.setDirection(DcMotor.Direction.REVERSE );
        rightTank.setDirection(DcMotor.Direction.REVERSE);
        leftTank.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);


    }

    @Override
    public void loop() {
        //getting drive values from controller
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;
        //clipping values so they dont exceed 100% on motors
        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);
        //scale drive values for easier controlling
        //expoL();
        //expoR();
        //set drive values for
        leftMotor.setPower(left);
        rightMotor.setPower(right);
        leftTank.setPower(left/3);
        rightTank.setPower(right/3);
        if (gamepad1.a)
            servo.setPosition(1);
        if (gamepad1.b)
            servo.setPosition(0);


        //telemetryc vz
        telemetry.addData("Left Motor power", left);
		telemetry.addData("Right Motor power", right);
        telemetry.addData("Servo Position", servo.getPosition());
    }
    //scale left drive value
    public void expoL() {
        left = left*left;
    }
    //scale right drive value
    public void expoR() {
        right = right*right;
    }

}