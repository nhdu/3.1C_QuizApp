package com.example.a31c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class second_questionAct extends AppCompatActivity {
    private TextView welcome, textProgress;
    private Button document, android, libraries, submit;
    private boolean document_isClicked, android_isClicked, libraries_isClicked;
    private ProgressBar progress;
    private int counter;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        welcome = (TextView) findViewById(R.id.welcome_view2);
        document = (Button) findViewById(R.id.document_choice);
        android = (Button) findViewById(R.id.android_choice);
        libraries = (Button) findViewById(R.id.libraries_choice);
        submit = (Button) findViewById(R.id.submit_button2);
        textProgress = (TextView) findViewById(R.id.text_progress2);
        progress = (ProgressBar) findViewById(R.id.progress2);

        Intent intent = getIntent();
        welcome.setText("Welcome " + intent.getStringExtra("name"));
        counter = intent.getIntExtra("progress_count", 0);
        score = intent.getIntExtra("score", 0);
        textProgress.setText(counter + "/5");
        progress.setProgress(counter);
        progress.setMax(5);

        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                document_isClicked = true;
                android_isClicked = false;
                libraries_isClicked = false;
                score = score + 1;
                option_handling(document, android, libraries);
            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                document_isClicked = false;
                android_isClicked = true;
                libraries_isClicked = false;
                option_handling(android, document, libraries);
            }
        });

        libraries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                document_isClicked = false;
                android_isClicked = false;
                libraries_isClicked = true;
                option_handling(libraries, android, document);
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
                    if (document_isClicked) {
                        document.setBackgroundColor(getColor(R.color.right));
                        option_isClicked();
                        button_disable();
                    } else if (android_isClicked) {
                        answer_handling(android);
                        option_isClicked();
                        button_disable();
                    } else if (libraries_isClicked){
                        answer_handling(libraries);
                        option_isClicked();
                        button_disable();
                    }
                }
            }
        });
    }

    public void option_isClicked() {
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
        document.setBackgroundColor(getColor(R.color.right));
        chosen_option.setBackgroundColor(getColor(R.color.wrong));
    }

    public void button_disable()
    {
        document.setEnabled(false);
        android.setEnabled(false);
        libraries.setEnabled(false);
    }

    public void switchActivity(String name)
    {
        Intent transition3 = new Intent(second_questionAct.this, third_questionAct.class);
        transition3.putExtra("name", name);
        transition3.putExtra("progress_count", counter);
        transition3.putExtra("score", score);
        startActivity(transition3);
        finish();
    }
}