package com.co.co_labexer2_persistentstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    FileOutputStream fos;
    SharedPreferences preferences;
    String filename = "sample.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
//        preferences = getPreferences(Context.MODE_PRIVATE);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void saveShared(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }

    public void saveInternal(View view){
        try{
            String message = "Username: " + username.getText().toString() + "\n" + "Password: " + password.getText().toString();
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(message.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }
}
