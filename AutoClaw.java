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
public class AutoClaw extends LinearOpMode {
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
            drive_forward(1000);
        }
    }
    public void drive_forward(double t) throws InterruptedException{
        //reversing because its on oppisite side
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
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
        //setting straight value
        straight = 600;
        //setting drive speed
        ld_speed = 0.9;
        rd_speed = 0.9;
        //how often to run the loop
        dt = 200;
        //coefficients for PID loop
        kp = 1;
        ki = 2;
        kd = 3;
        //start time to compare against
        start_time = System.currentTimeMillis();
        //setting variables to zero to use in first loop round
        intergral = 0;
        previous_error = 0;
        //driving initial values
        leftMotor.setPower(ld_speed);
        rightMotor.setPower(rd_speed);
        while (System.currentTimeMillis() - start_time < t) {
            //error is the rotaion error
            error = straight - gyro.getRotation();
            //dividing errror because motor speed is in percentage
            error = error / 50;
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
            ld_speed = Range.clip(ld_speed, 0.5, 1);
            rd_speed = Range.clip(rd_speed, 0.5 , 1);
            leftMotor.setPower(ld_speed);
            rightMotor.setPower(rd_speed);
            //setting vlues for next loop
            previous_error = error;
            //telemetry stuff
            telemetry.addData("pro", proportional);
            telemetry.addData("int", intergral);
            telemetry.addData("der", derivitive);
            telemetry.addData("gyro", gyro.getRotation());
            telemetry.addData("error", error);

            sleep(dt);
        }

    }
}
    /*
    public void turn_r(double a){
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
        double ld_speed;
        double rd_speed;
        double dt;
        double PID;
        //setting drive speed
        ld_speed = 0.5;
        rd_speed = 0.5;
        //how often to run the loop
        dt = 200;
        //coefficients for PID loop
        kp = 1;
        ki = 2;
        kd = 3;
        //setting variables to zero to use in first loop round
        intergral = 0;
        previous_error = 0;
        //driving initial values
        rd_speed = -rd_speed;
        leftMotor.setPower(ld_speed);
        rightMotor.setPower(rd_speed);
        //initial compass value to comoare
        origin = compass.getDirection
        while ((compass.getDirection() - origin) != t){
            //error is the rotaion error
            error = t - (compass.getDirection() - origin);
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
    public void compass_cal(){
        //FIGURE OUT HOW TO CALIBRATE COMPASS
    }
    */
