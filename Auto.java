package com.qualcomm.ftcrobotcontroller.opmodes;


/**
 * Created by robotics on 12/8/15.
 */
public class Auto extends Drive {
    public void runOpMode() throws InterruptedException {

        waitForStart();
        while(opModeIsActive()){
            go(100000, 0);
        }
        halt();
    }
}
