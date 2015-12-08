package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

public class Gyro extends LinearOpMode implements Runnable{
    //because i have to
    public void runOpMode(){}

    GyroSensor gyro;

    double a;
    double b;
    double c;
    double d;
    double e;
    double f;
    double g;
    double h;
    double i;
    double j;
    double k;
    double l;
    double m;
    double n;
    double o;
    double p;
    double q;
    double r;
    double s;
    double t;
    double u;
    double v;
    double w;
    double x;
    double y;
    double z;
    double degrees;
    public double angle;
    //calibrated straight value;
    public double cal_straight;

    //keeps trak of gyro angle in seperate thread
    public void run() {
        long dt = 2;
        angle = 0;
        while (true){
            //ratioan in degrees per a second about the z axis of robot
            degrees =  gyro.getRotation() - cal_straight;
            angle = angle + degrees * (dt/1000);
            try{
                Thread.sleep(dt);
            }
            catch (InterruptedException e){
            }

        }

    }
    public double get_angle(){
        return angle;
    }

    public void gyro_cal() throws InterruptedException{
        a = gyro.getRotation();
        sleep(20);
        b = gyro.getRotation();
        sleep(20);
        c = gyro.getRotation();
        sleep(20);
        d = gyro.getRotation();
        sleep(20);
        e = gyro.getRotation();
        sleep(20);
        f = gyro.getRotation();
        sleep(20);
        g = gyro.getRotation();
        sleep(20);
        h = gyro.getRotation();
        sleep(20);
        i = gyro.getRotation();
        sleep(20);
        j = gyro.getRotation();
        sleep(20);
        k = gyro.getRotation();
        sleep(20);
        l = gyro.getRotation();
        sleep(20);
        m = gyro.getRotation();
        sleep(20);
        n = gyro.getRotation();
        sleep(20);
        o = gyro.getRotation();
        sleep(20);
        p = gyro.getRotation();
        sleep(20);
        q = gyro.getRotation();
        sleep(20);
        r = gyro.getRotation();
        sleep(20);
        s = gyro.getRotation();
        sleep(20);
        t = gyro.getRotation();
        sleep(20);
        u = gyro.getRotation();
        sleep(20);
        v = gyro.getRotation();
        sleep(20);
        w = gyro.getRotation();
        sleep(20);
        x = gyro.getRotation();
        sleep(20);
        y = gyro.getRotation();
        sleep(20);
        z = gyro.getRotation();
        sleep(20);

        cal_straight = (a + b + c + d + e + f + g + h + i + j + k +l + m + n + o + p + q + r + s + t + u + v + w + x + y + z) / 26;

    }
}
