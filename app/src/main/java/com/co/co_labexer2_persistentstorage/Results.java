package com.co.co_labexer2_persistentstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Results extends AppCompatActivity {
    TextView display;
    FileInputStream fis;
    SharedPreferences preferences;
    String filename = "sample.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        display = (TextView) findViewById(R.id.displayMessage);
//        preferences = getPreferences(Context.MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void loadShared(View view){
        String user = preferences.getString("username", "");
        String pass = preferences.getString("password", "");
        String message = "Username: " + user + "\n" + "Password: " + pass;

        display.setText(message);
    }

    public void loadInternal(View view){
        try{
            fis = openFileInput(filename);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n;

            while((n = fis.read(buffer)) != -1){
                fileContent.append(new String(buffer, 0, n));
            }
            display.setText(fileContent);
            fis.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void clearMessage(View view){
        display.setText(" ");
    }

    public void goBack(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
