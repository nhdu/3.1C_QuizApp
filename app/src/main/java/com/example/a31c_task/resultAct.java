package com.example.a31c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultAct extends AppCompatActivity {
    private Button retake, finish;
    private TextView viewCongrat, viewScore;
    private String name;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        retake = (Button) findViewById(R.id.new_quiz);
        finish = (Button) findViewById(R.id.finish_quiz);
        viewCongrat = (TextView) findViewById(R.id.congrat_view);
        viewScore = (TextView) findViewById(R.id.score_view);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        score = intent.getIntExtra("score", 0);

        viewCongrat.setText("Congratulation " + name);
        viewScore.setText(score + "/5");
        retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

}