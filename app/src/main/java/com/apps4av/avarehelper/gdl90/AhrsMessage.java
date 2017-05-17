package com.apps4av.avarehelper.gdl90;


import com.apps4av.avarehelper.utils.Logger;

/**
 * Created by pasniak on 5/16/2017.
 */

public class AhrsMessage extends Message {
    public float mRoll;
    public float mPitch;
    public float mHeading;
    public float mSlip;
    public float mYaw;
    public float mGs;

    public AhrsMessage() {
        super(MessageType.AHRS_REPORT);
    }

    @Override
    protected void parse(byte[] msg) {
        // gased on makeAHRSGDL90Report in  stratux/main/gps.go 
        int roll = (((int)msg[3]) << 8) + (msg[4] & 0xFF);
        int pitch = (((int)msg[5]) << 8) + (msg[6] & 0xFF);
        int heading = (((int)msg[7]) << 8) + (msg[8] & 0xFF);
        int slip = (((int)msg[9]) << 8) + (msg[10] & 0xFF);
        int yaw = (((int)msg[11]) << 8) + (msg[12] & 0xFF);
        int gs = (((int)msg[13]) << 8) + (msg[14] & 0xFF);
        mRoll = roll / 10f;
        mPitch = pitch / 10f;
        mHeading = heading / 10f;
        mSlip  = slip / 10f;
        mYaw = yaw / 10f;
        mGs = gs / 10f;

        Logger.Logit("AHRS roll=" + r(mRoll) + " pitch=" + r(mPitch) +
                " heading=" + r(mHeading) + " slip=" + r(mSlip) + " yaw=" + r(mYaw) +
                " Gs=" + mGs);
    }
    
    double r(float f) { return Math.round(f*100)/100; }
}
