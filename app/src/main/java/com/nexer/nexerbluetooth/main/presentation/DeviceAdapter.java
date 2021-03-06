package com.nexer.nexerbluetooth.main.presentation;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import comunicacao.bluetooth.R;

/**
 * Created by guilhermecoelho on 1/30/18.
 */

class DeviceAdapter extends BaseAdapter {

    // Class Constants

    public static final String TAG = "DeviceAdapter";

    // Class Properties

    Map<String, Integer> devRssiValues;

    Context context;
    List<BluetoothDevice> devices;
    LayoutInflater inflater;

    // Class Life Cycle

    public DeviceAdapter(Context context, List<BluetoothDevice> devices) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewGroup vg;

        if (convertView != null) {
            vg = (ViewGroup) convertView;
        } else {
            vg = (ViewGroup) inflater.inflate(R.layout.device_element, null);
        }

        BluetoothDevice device = devices.get(position);
        final TextView tvadd = ((TextView) vg.findViewById(R.id.address));
        final TextView tvname = ((TextView) vg.findViewById(R.id.name));
        final TextView tvpaired = (TextView) vg.findViewById(R.id.paired);
        final TextView tvrssi = (TextView) vg.findViewById(R.id.rssi);

        tvrssi.setVisibility(View.VISIBLE);

        byte rssival = (byte) devRssiValues.get(device.getAddress()).intValue();
        if (rssival != 0) {
            tvrssi.setText("Rssi = " + String.valueOf(rssival));
        }

        tvname.setText(device.getName());
        tvadd.setText(device.getAddress());
        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
            Log.i(TAG, "device::" + device.getName());
            tvname.setTextColor(Color.WHITE);
            tvadd.setTextColor(Color.WHITE);
            tvpaired.setTextColor(Color.GRAY);
            tvpaired.setVisibility(View.VISIBLE);
            tvpaired.setText(R.string.paired);
            tvrssi.setVisibility(View.VISIBLE);
            tvrssi.setTextColor(Color.WHITE);

        } else {
            tvname.setTextColor(Color.WHITE);
            tvadd.setTextColor(Color.WHITE);
            tvpaired.setVisibility(View.GONE);
            tvrssi.setVisibility(View.VISIBLE);
            tvrssi.setTextColor(Color.WHITE);
        }
        return vg;
    }


}
