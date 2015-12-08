package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by robotics on 12/8/15.
 */
public class Auto extends Drive {

    public void runOpMode() throws InterruptedException {
        gyro_cal();

        while(opModeIsActive()){
            go(1000, 0);
        }


    }
}
