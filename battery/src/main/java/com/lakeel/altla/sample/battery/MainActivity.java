package com.lakeel.altla.sample.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewHealth;

    private TextView textViewPlugged;

    private TextView textViewStatus;

    private TextView textViewLevelScale;

    private TextView textViewPresent;

    private TextView textViewTechnology;

    private TextView textViewTemerature;

    private TextView textViewVoltage;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
                String healthName = toHealthName(health);
                textViewHealth.setText(healthName);

                int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                String pluggedName = toPluggedName(plugged);
                textViewPlugged.setText(pluggedName);

                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                String statusName = toStatusName(status);
                textViewStatus.setText(statusName);

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                textViewLevelScale.setText(level + " / " + scale);

                boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
                textViewPresent.setText(Boolean.toString(present));

                String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
                textViewTechnology.setText(technology);

                int temperature = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -999);
                textViewTemerature.setText("" + temperature);

                int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
                textViewVoltage.setText("" + voltage);
            }
        }
    };

    private static String toHealthName(int value) {
        switch (value) {
            case BatteryManager.BATTERY_HEALTH_COLD:
                return "BATTERY_HEALTH_COLD";
            case BatteryManager.BATTERY_HEALTH_DEAD:
                return "BATTERY_HEALTH_DEAD";
            case BatteryManager.BATTERY_HEALTH_GOOD:
                return "BATTERY_HEALTH_GOOD";
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                return "BATTERY_HEALTH_OVERHEAT";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "BATTERY_HEALTH_OVER_VOLTAGE";
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                return "BATTERY_HEALTH_UNKNOWN";
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                return "BATTERY_HEALTH_UNSPECIFIED_FAILURE";
            default:
                return "(Unexpected value)";
        }
    }

    private static String toPluggedName(int value) {
        switch (value) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                return "BATTERY_PLUGGED_AC";
            case BatteryManager.BATTERY_PLUGGED_USB:
                return "BATTERY_PLUGGED_USB";
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                return "BATTERY_PLUGGED_WIRELESS";
            default:
                return "(Unexpected value)";
        }
    }

    private static String toStatusName(int value) {
        switch (value) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "BATTERY_STATUS_CHARGING";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "BATTERY_STATUS_DISCHARGING";
            case BatteryManager.BATTERY_STATUS_FULL:
                return "BATTERY_STATUS_FULL";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "BATTERY_STATUS_NOT_CHARGING";
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                return "BATTERY_STATUS_UNKNOWN";
            default:
                return "(Unexpected value)";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);

        textViewHealth = (TextView) findViewById(R.id.textViewHealth);
        textViewPlugged = (TextView) findViewById(R.id.textViewPlugged);
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        textViewLevelScale = (TextView) findViewById(R.id.textViewLevelScale);
        textViewPresent = (TextView) findViewById(R.id.textViewPresent);
        textViewTechnology = (TextView) findViewById(R.id.textViewTechnology);
        textViewTemerature = (TextView) findViewById(R.id.textViewTemperature);
        textViewVoltage = (TextView) findViewById(R.id.textViewVoltage);
    }
}
