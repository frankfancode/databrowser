package com.frankfancode.databrowser;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createSp(View view) {
        for (int i = 0; i < 5; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int j = 0; j < 5; j++) {
                editor.putBoolean("sp_key_bool" + j, true);
                editor.putString("sp_key_string" + j, "{data:" + i + "" + j + "}");
            }
            editor.apply();
        }

    }

    public void updateSp(View view) {

        for (int i = 0; i < 5; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int j = 0; j < 5; j++) {
                editor.putBoolean("sp_key_bool" + j, true);
                editor.putString("sp_key_string" + j, "{time:" + System.currentTimeMillis() + "}" + Thread.getAllStackTraces() + Thread.getAllStackTraces() + Thread.getAllStackTraces() + Thread.getAllStackTraces() + Thread.getAllStackTraces() + Thread.getAllStackTraces() + Thread.getAllStackTraces() + Thread.getAllStackTraces());
            }
            editor.apply();
        }

    }

    public void clearSp(View view) {
        for (int i = 0; i < 5; i++) {
            SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences" + i, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            for (int j = 0; j < 5; j++) {
                editor.remove("sp_key_bool" + j);
                editor.remove("sp_key_string" + j);
            }
            editor.apply();
        }
    }

}
