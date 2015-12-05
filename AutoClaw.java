package com.qualcomm.ftcrobotcontroller.opmodes;
/*
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.util.Range;

//TO DO
//29in/s
public class AutoClaw extends LinearOpMode {
   /* //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor claw;
    DcMotor pinion;
    //gyro sensor
    GyroSensor gyro;
    //compass sensor
    CompassSensor compass;



    @Override
    public void runOpMode() throws InterruptedException {
        //assign hardware to objects
        leftMotor = hardwareMap.dcMotor.get("left_Motor");
        rightMotor = hardwareMap.dcMotor.get("right_Motor");
        claw = hardwareMap.dcMotor.get("claw");
        pinion = hardwareMap.dcMotor.get("rack");
        //assigning the hardware gyro to a variable
        gyro = hardwareMap.gyroSensor.get("gy");
        compass = hardwareMap.compassSensor.get("compass");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        //less than 600 is left more than right
        //straight is 600

        drive(1500, 600);
        drive(4000, 1000);
        claw_pos();
        while (opModeIsActive()){
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        }
        drive_stop();


    }
    //x is direction
    public void  claw_pos() throws InterruptedException{
        pinion.setPower(0.8);
        sleep(2250);
        pinion.setPower(0);
        claw.setPower(-0.8);
        sleep(1000);
        claw.setPower(0);

    }

}*/
