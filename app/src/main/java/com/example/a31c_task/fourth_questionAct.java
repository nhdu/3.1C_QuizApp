package com.example.a31c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class fourth_questionAct extends AppCompatActivity {
    private TextView welcome, progressText;
    private Button OnClick, OnHover, OnKey, submit;
    private boolean OnClick_isClicked, OnHover_isClicked, OnKey_isClicked;
    private ProgressBar progress;
    private int counter;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        welcome = (TextView) findViewById(R.id.welcome_view4);
        OnClick = (Button) findViewById(R.id.OnClick_choice);
        OnHover = (Button) findViewById(R.id.OnHover_choice);
        OnKey = (Button) findViewById(R.id.OnKey_choice);
        submit = (Button) findViewById(R.id.submit_button4);
        progressText = (TextView) findViewById(R.id.text_progress4);
        progress = (ProgressBar) findViewById(R.id.progress4);
        Intent intent = getIntent();
        welcome.setText("Welcome " + intent.getStringExtra("name"));
        counter = intent.getIntExtra("progress_count", 0);
        score = intent.getIntExtra("score", 0);
        progressText.setText(counter + "/5");
        progress.setProgress(counter);
        progress.setMax(5);
        OnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClick_isClicked = true;
                OnHover_isClicked = false;
                OnKey_isClicked = false;
                score = score + 1;
                option_handling(OnClick, OnHover, OnKey);
            }
        });

        OnHover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClick_isClicked = false;
                OnHover_isClicked = true;
                OnKey_isClicked = false;
                option_handling(OnHover, OnClick, OnKey);
            }
        });

        OnKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClick_isClicked = false;
                OnHover_isClicked = false;
                OnKey_isClicked = true;
                option_handling(OnKey, OnHover, OnClick);
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
                    if (OnClick_isClicked) {
                        OnClick.setBackgroundColor(getColor(R.color.right));
                        option_isClicked();
                        button_disable();
                    } else if (OnHover_isClicked) {
                        answer_handling(OnHover);
                        option_isClicked();
                        button_disable();
                    } else if (OnKey_isClicked){
                        answer_handling(OnKey);
                        option_isClicked();
                        button_disable();
                    }

                }
            }
        });
    }

    private void option_isClicked() {
        counter = counter +1;
        progress.setProgress(counter);
        progress.setMax(5);
        progressText.setText(counter + "/5");
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
        OnClick.setBackgroundColor(getColor(R.color.right));
        chosen_option.setBackgroundColor(getColor(R.color.wrong));
    }

    public void button_disable()
    {
        OnClick.setEnabled(false);
        OnHover.setEnabled(false);
        OnKey.setEnabled(false);
    }

    public void switchActivity(String name)
    {
        Intent transition3 = new Intent(fourth_questionAct.this, fifth_questionAct.class);
        transition3.putExtra("name", name);
        transition3.putExtra("progress_count", counter);
        transition3.putExtra("score", score);
        startActivity(transition3);
        finish();
    }
}