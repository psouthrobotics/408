package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Claw extends OpMode {

    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    //rack and pinion motor
    DcMotor pinion;
    //claw servos
    Servo sideSClaw;

    //drive values
    double left;
    double right;
    //drive values for claw
    double leftM;
    double rightM;
    //scale for expo
    double a;




    @Override
    public void init() {

        //Mapping physical motors to variable
        leftMotor = hardwareMap.dcMotor.get("left_Motor");
        rightMotor = hardwareMap.dcMotor.get("right_Motor");
        sideSClaw = hardwareMap.servo.get("sideServo");
        pinion = hardwareMap.dcMotor.get("rack");
        //scale value
        a = 5;

    }

    @Override
    public void loop() {
        //getting drive values from controll
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;
        //clipping values so they dont exceed 100% on motors
        left = Range.clip(left, -1, 1);
        right = Range.clip(right, -1, 1);
        //scale drive values for easier controling
        expoL();
        expoR();
        //set drive values for motors
        leftMotor.setPower(left);
        rightMotor.setPower(right);

        //pinion movement
        if (gamepad2.left_bumper){
            pinion.setPower(-0.5);
        }
        if (gamepad2.left_trigger >= 0.8){
            pinion.setPower(0.5);
        }
        //moving side servo
        if (gamepad1.b) {
            sideSClaw.setPosition(0);
        }
        if (gamepad1.x) {
            sideSClaw.setPosition(0.6);
        }

        //telemetry
        telemetry.addData("Left Motor power", left);
		telemetry.addData("Right Motor power", right);







    }
    //scale left drive value
    public void expoL() {
        double expo;
        double i;
        i = leftM;
        expo =i/((1+a)*(1-i));
        leftM = expo;
    }
    //scale right drive value
    public void expoR() {
        double expo;
        double i;
        i = rightM;
        expo =i/((1+a)*(1-i));
        rightM = expo;
    }
}