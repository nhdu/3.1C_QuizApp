package com.example.a31c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class third_questionAct extends AppCompatActivity {
    private TextView welcome, textProgress;
    private Button yes3, no3, cant, submit;
    private boolean yes3_isClicked, no3_isClicked, cant_isClicked;
    private ProgressBar progress;
    private int counter;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        welcome = (TextView) findViewById(R.id.welcome_view3);
        yes3 = (Button) findViewById(R.id.yes3_choice);
        no3 = (Button) findViewById(R.id.no3_choice);
        cant = (Button) findViewById(R.id.cant_choice);
        submit = (Button) findViewById(R.id.submit_button3);
        textProgress = (TextView) findViewById(R.id.text_progress3);
        progress = (ProgressBar) findViewById(R.id.progress3);
        Intent intent = getIntent();
        welcome.setText("Welcome " + intent.getStringExtra("name"));
        counter = intent.getIntExtra("progress_count", 0);
        score = intent.getIntExtra("score", 0);
        textProgress.setText(counter + "/5");
        progress.setProgress(counter);
        progress.setMax(5);
        yes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes3_isClicked = true;
                no3_isClicked = false;
                cant_isClicked = false;
                score = score + 1;
                option_handling(yes3, no3, cant);
            }
        });

        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes3_isClicked = false;
                no3_isClicked = true;
                cant_isClicked = false;
                option_handling(no3, yes3, cant);
            }
        });

        cant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yes3_isClicked = false;
                no3_isClicked = false;
                cant_isClicked = true;
                option_handling(cant, no3, yes3);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit.getText().equals("NEXT"))
                {
                    switchActivity(intent.getStringExtra("name"));
                }
                else {
                    if (yes3_isClicked) {
                        yes3.setBackgroundColor(getColor(R.color.right));
                        option_isClicked();
                        button_disable();
                    } else if (no3_isClicked) {
                        answer_handling(no3);
                        option_isClicked();
                        button_disable();
                    } else if (cant_isClicked){
                        answer_handling(cant);
                        option_isClicked();
                        button_disable();
                    }

                }
            }
        });
    }

    private void option_isClicked() {
        counter = counter + 1;
        progress.setProgress(counter);
        progress.setMax(5);
        textProgress.setText(counter + "/5");
        submit.setText("NEXT");
    }

    public void option_handling(Button chosen_option, Button option2, Button option3)
    {
        chosen_option.setBackgroundColor(getColor(R.color.isClicked));
        option2.setBackgroundColor(getColor(R.color.button));
        option3.setBackgroundColor(getColor(R.color.button));
    }

    public void answer_handling(Button chosen_option)
    {
        yes3.setBackgroundColor(getColor(R.color.right));
        chosen_option.setBackgroundColor(getColor(R.color.wrong));
    }

    public void button_disable()
    {
        yes3.setEnabled(false);
        no3.setEnabled(false);
        cant.setEnabled(false);
    }

    public void switchActivity(String name)
    {
        Intent transition3 = new Intent(third_questionAct.this, fourth_questionAct.class);
        transition3.putExtra("name", name);
        transition3.putExtra("progress_count", counter);
        transition3.putExtra("score", score);
        startActivity(transition3);
        finish();
    }
}