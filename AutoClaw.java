package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.util.Range;

//TO DO
//29in/s
public class AutoClaw extends LinearOpMode {
    //drive motors
    DcMotor leftMotor;
    DcMotor rightMotor;
    //gyro sensor
    GyroSensor gyro;
    //compass sensor
    CompassSensor compass;



    @Override
    public void runOpMode() throws InterruptedException {
        //assign hardware to objects
        leftMotor = hardwareMap.dcMotor.get("left_Motor");
        rightMotor = hardwareMap.dcMotor.get("right_Motor");
        //assigning the hardware gyro to a variable
        gyro = hardwareMap.gyroSensor.get("gy");
        compass = hardwareMap.compassSensor.get("compass");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        //less than 600 is left more than right
        //straight is 600
        drive(1000, 600);
        drive(500, 500);
        drive_stop();

    }
    public void drive_stop(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    /*public void pivot(char z, double t){
        double start_time;
        start_time = System.currentTimeMillis();
        switch(z){
            case 'r':

                while (System.currentTimeMillis() - start_time < t) {
                    leftMotor.setPower(0.2);
                    rightMotor.setPower(-0.7);
                }

            case 'l':

                while (System.currentTimeMillis() - start_time < t) {
                   leftMotor.setPower(-0.2);
                   rightMotor.setPower(0.7);
                }

        }

    }
    */
    //x is direction
    public void  drive(double t, double x) throws InterruptedException{
        //reversing because its on oppisite side

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
        straight = x;
        //setting drive speed
        ld_speed = 0.8;
        rd_speed = 0.8;
        //how often to run the loop
        dt = 2;
        //coefficients for PID loop
        kp = 0;
        ki = 0;
        kd = 0;
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
            telemetry.addData("Compass", compass.getDirection());

            sleep(dt);
        }

    }
}
