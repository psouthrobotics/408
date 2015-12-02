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
        //assigning the hardware gyro to a variable
        gyro = hardwareMap.gyroSensor.get("gy");

        waitForStart();
        while (opModeIsActive()) {
            drive_forward();
        }
    }
    public void drive_forward(double t) {
        //reversing because its on oppisite side
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
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
        double dt;
        double PID;
        //setting straight value
        straight = 600;
        //setting drive speed
        ld_speed = 0.5;
        rd_speed = 0.5;
        //how often to run the loop
        dt = 200;
        //coefficients for PID loop
        kp = 1;
        ki = 2;
        kd = 3;
        //start time to compare against
        start_time = java.util.Date.getTime();
        //setting variables to zero to use in first loop round
        intergral = 0;
        previous_error = 0;
        //driving initial values
        leftMotor.setPower(ld_speed);
        rightMotor.setPower(rd_speed);
        while (java.util.Date.getTime() - start_time < t){
            //error is the rotaion error
            error = straight - gyro.getRotation;
            //dividing errror because motor speed is in percentage
            error = error / 10;
            //proportional which is just error
            proportional = error;
            //intergral art of loop- error over time
            intergral = intergral + error * dt;
            //derivitive which uses slope to correct future error
            derivitive = (error -  previous_error) / dt;
            //suming together to create complete correction value
            PID = kp * proportional + ki * intergral + kd * derivitive;
            //applying corrections to driving
            leftMotor.setPower(ld_speed + PID);
            rightMotor.setPower(rd_speed - PID)
            //setting vlues for next loop
            previous_error = error;
            ld_speed = ld_speed + PID;
            rd_speed = rd_speed - PID;
            //telemetry stuff
            telemetry.addData("pro", proportional);
            telemetry.addData("int", intergral);
            telemetry.addData("der", derivitive);
            telemetry.adddata("gyro", gyro.getRotation());
            telemetry.addData("error", error)

            sleep(dt);
        }

    }
    public double PID(char z, double t){
        //reversing because its on oppisite side
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
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
        double dt;
        double PID;
        //setting straight value
        straight = 600;
        //setting drive speed
        ld_speed = 0.5;
        rd_speed = 0.5;
        //how often to run the loop
        dt = 200;
        //coefficients for PID loop
        kp = 1;
        ki = 2;
        kd = 3;
        //start time to compare against
        start_time = java.util.Date.getTime();
        //setting variables to zero to use in first loop round
        intergral = 0;
        previous_error = 0;
        //driving initial values
        leftMotor.setPower(ld_speed);
        rightMotor.setPower(rd_speed);
        switch (z){
            case "d":
                do {
                    //error is the rotaion error
                    error = straight - gyro.getRotation;
                    //dividing errror because motor speed is in percentage
                    error = error / 10;
                    //proportional which is just error
                    proportional = error;
                    //intergral art of loop- error over time
                    intergral = intergral + error * dt;
                    //derivitive which uses slope to correct future error
                    derivitive = (error - previous_error) / dt;
                    //suming together to create complete correction value
                    PID = kp * proportional + ki * intergral + kd * derivitive;
                    //applying corrections to driving
                    leftMotor.setPower(ld_speed + PID);
                    rightMotor.setPower(rd_speed - PID)
                    //setting vlues for next loop
                    previous_error = error;
                    ld_speed = ld_speed + PID;
                    rd_speed = rd_speed - PID;
                    //telemetry stuff
                    telemetry.addData("pro", proportional);
                    telemetry.addData("int", intergral);
                    telemetry.addData("der", derivitive);
                    telemetry.adddata("gyro", gyro.getRotation());
                    telemetry.addData("error", error);
                    sleep(dt);
                } while (java.util.Date.getTime() - start_time < t);
            case "t":
                //add pis loop to use compass when turn is specified
        }
    }
}
