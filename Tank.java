package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.util.Range;

public class Tank extends OpMode {
    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor rightTank;
    DcMotor leftTank;
    //compass
    CompassSensor compass;
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
        //scale value
        a = 20;
        leftMotor.setDirection(DcMotor.Direction.REVERSE );
        rightTank.setDirection(DcMotor.Direction.REVERSE);
        leftTank.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);


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
        //expoL();
        //expoR();
        //set drive values for
        double tankL = left/4;
        double tankR = right/4;
        leftMotor.setPower(left);
        rightMotor.setPower(right);
        leftTank.setPower(tankL);
        rightTank.setPower(tankR);

        //telemetryc vz
        telemetry.addData("Left Motor power", left);
		telemetry.addData("Right Motor power", right);
        telemetry.addData("Left Tank power", tankL);
        telemetry.addData("Right Tank", tankR);
    }
    //scale left drive value
    public void expoL() {
        double expo;
        double i;
        i = left;
        expo =i/((1+a)*(1-i));
        left = expo;
    }
    //scale right drive value
    public void expoR() {
        double expo;
        double i;
        i = right;
        expo =i/((1+a)*(1-i));
        right = expo;
    }

}