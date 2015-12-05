package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.util.Range;

public class Claw extends OpMode {
    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    //rack and pinion motor
    DcMotor pinion;
    //claw roation
    DcMotor claw;
    //compass
    CompassSensor compass;
    //drive values
    double left;
     double right;
    double pinion_power;
    double claw_power;
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
        claw = hardwareMap.dcMotor.get("claw");
        pinion = hardwareMap.dcMotor.get("rack");
        compass = hardwareMap.compassSensor.get("compass");
        //scale value
        a = 20;
        compass.setMode(CompassSensor.CompassMode.CALIBRATION_MODE);

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
        leftMotor.setPower(-left);
        rightMotor.setPower(right);

        //pinion movement
        pinion_power = -gamepad2.left_stick_y;
        expoP();
        pinion_power = Range.clip(pinion_power, -1, 1);
        pinion.setPower(pinion_power);

        claw_power = -gamepad2.right_stick_y;
        claw_power = Range.clip(claw_power, -1, 1);
        expoC();
        claw.setPower(-gamepad2.right_stick_y);

        //telemetry
        telemetry.addData("Left Motor power", left);
		telemetry.addData("Right Motor power", right);
        telemetry.addData("rotation power", pinion_power);
        telemetry.addData("Extension power", claw_power);
        telemetry.addData("compass calibration", compass.calibrationFailed());
        telemetry.addData("compass data", compass.getDirection());
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
    public void expoP() {
        double expo;
        double i;
        i = pinion_power;
        expo =i/((1+a)*(1-i));
        pinion_power = expo;
    }
    public void expoC() {
        double expo;
        double i;
        i = claw_power;
        expo =i/((1+a)*(1-i));
        claw_power = expo;
    }
}