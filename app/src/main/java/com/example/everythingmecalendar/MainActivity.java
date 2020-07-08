package com.example.everythingmecalendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Cursor cursor;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            Log.e("TAG", "onCreate: NO permissions");
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR}, 100);
            return;
        }

        cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null, null);


        assert cursor != null;
        while(cursor.moveToNext()){
            if(cursor!=null){
               int id_1 = cursor.getColumnIndex(CalendarContract.Events.TITLE);

               String titleValue = cursor.getString(id_1);

                Log.e("TAG", "onCreate: EVENTS " + titleValue);
            } else{
                Log.e("TAG", "onCreate: No events found");

            }

        }

        Log.e("TAG", "onCreate: " + cursor.getCount()); // total number of calendar events


    }
}