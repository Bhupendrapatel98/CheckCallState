package com.example.phonecallstatus;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        int state = telephonyManager.getCallState();
        String num = intent.getStringExtra("incoming_number");
        Toast.makeText(context, "" + num, Toast.LENGTH_SHORT).show();

        if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
           /*HashMap<String,Object> map=new HashMap<>();
           map.put("number",num);
           map.put("status","ACTIVE");
           FirebaseDatabase.getInstance().getReference().child("inset").push().setValue(map);*/
        }
        if (state == telephonyManager.CALL_STATE_IDLE) {
            Toast.makeText(context, "No call", Toast.LENGTH_SHORT).show();
        }
        if (state == telephonyManager.CALL_STATE_RINGING) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("number", num);
            // map.put("status","RINGING");
            FirebaseDatabase.getInstance().getReference().child("inset").push().setValue(map);
        }
    }
}
