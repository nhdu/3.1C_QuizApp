package com.example.a31c_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText userinput;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userinput = (EditText) findViewById(R.id.name);
        start = (Button) findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String user_name = userinput.getText().toString();
               Intent intent = new Intent(MainActivity.this, first_questionAct.class);
               intent.putExtra("NAME",user_name);
               startActivity(intent);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText input = (EditText) findViewById(R.id.name);
        outState.putString("user_name", input.getText().toString());
        Log.i("STATES", "ONSAVED ISNTANCE IS CALLED!!!!");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText input2 = (EditText) findViewById(R.id.name);
        input2.setText(savedInstanceState.getString("user_name"));
        Log.i("STTES", "ONRESTORE ISNTANCE IS CALLED!!!!");
    }
}