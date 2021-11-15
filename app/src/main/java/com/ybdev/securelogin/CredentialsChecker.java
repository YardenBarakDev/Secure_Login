package com.ybdev.securelogin;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.os.BatteryManager;
import android.provider.Settings;
import java.util.Calendar;

public class CredentialsChecker {

    private final Context context;

    public CredentialsChecker(Context context){ this.context = context; }

    private int getBatteryPercentage() {

        BatteryManager batteryManager = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    private int getOrientation(){
        return context.getResources().getConfiguration().orientation;
    }

    private int getCurrentHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    private boolean checkBluetooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
            return false;
        return mBluetoothAdapter.isEnabled();
    }

    public static boolean isAirplaneModeOn(Context airplaneModeContext) {
        return Settings.Global.getInt(airplaneModeContext.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    public boolean checkAccess(String password){
        return password.contains(String.valueOf(getCurrentHour()))&&
                password.contains(String.valueOf(getBatteryPercentage())) &&
                checkBluetooth() &&
                isAirplaneModeOn(context) &&
                getOrientation() == Configuration.ORIENTATION_LANDSCAPE;
    }
}
