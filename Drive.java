package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.GyroSensor;


public abstract class Drive extends LinearOpMode {
    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftTank;
    DcMotor rightTank;
    GyroSensor gyro;

    //t is time x is direction
    public void go(double t, double x) throws InterruptedException{
        //assign hardware to objects
        leftMotor = hardwareMap.dcMotor.get("right_Motor");
        rightMotor = hardwareMap.dcMotor.get("left_Motor");
        leftTank = hardwareMap.dcMotor.get("left_tank");
        rightTank = hardwareMap.dcMotor.get("right_tank");
        gyro = hardwareMap.gyroSensor.get("gy");
        //reversing because its on oppisite side
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightTank.setDirection(DcMotor.Direction.FORWARD);
        leftTank.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setDirection(DcMotor.Direction.FORWARD);

        //defining variables
        double kp;
        double ki;
        double kd;
        double error;
        double previous_error;
        double proportional;
        double intergral;
        double derivitive;
        double start_time;
        double ld_speed;
        double rd_speed;
        double straight;
        long dt;
        double PID;
        double angle;
        double degrees;
        //setting straight value
        straight = 600 + x;
        //setting drive speed
        ld_speed = 0.8;
        rd_speed = 0.8;
        //how often to run the loop
        dt = 20;
        //coefficients for PID loop
        kp = 1.5;
        ki = 3;
        kd = 6;
        //start time to compare against
        start_time = System.currentTimeMillis();
        //setting variables to zero to use in first loop round
        intergral = 0;
        previous_error = 0;

        angle = 0;

        while (System.currentTimeMillis() - start_time < t) {
            leftMotor.setPower(ld_speed);
            rightMotor.setPower(rd_speed);
            leftTank.setPower(ld_speed);
            rightTank.setPower(rd_speed);

            angle = angle + gyro.getRotation()/dt;
            error = straight - gyro.getRotation();
            //dividing errror because motor speed is in percentage
            error = error / 200;
            //proportional which is just error
            proportional = error;
            //intergral art of loop- error over time
            intergral = intergral + error * dt;
            //derivitive which uses slope to correct future error
            derivitive = (error - previous_error) / dt;
            //suming together to create complete correction value
            PID = kp * proportional + ki * intergral + kd * derivitive;
            //applying corrections to driving
            ld_speed = ld_speed + PID;
            rd_speed = rd_speed - PID;
            ld_speed = Range.clip(ld_speed, 0, 1);
            rd_speed = Range.clip(rd_speed, 0, 1);
            leftMotor.setPower(ld_speed);
            rightMotor.setPower(rd_speed);
            leftTank.setPower(ld_speed);
            rightTank.setPower(rd_speed);
            //setting vlues for next loop
            previous_error = error;
            //telemetry stuff
            telemetry.addData("Left Drive", ld_speed);
            telemetry.addData("Right Drive", rd_speed);
            telemetry.addData("Rotation", gyro.getRotation());
            telemetry.addData("Angle", angle);

            sleep(dt);
        }

    }
    public void halt(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}


