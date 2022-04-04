package com.example.a31c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class first_questionAct extends AppCompatActivity {

    private TextView welcome, textProgress;
    private Button no, yes, call_on, submit;
    private boolean no_isClicked, yes_isClicked, callOn_isClicked;
    private ProgressBar progress;
    private int counter;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_question);

        welcome = (TextView) findViewById(R.id.welcome_view);
        no = (Button) findViewById(R.id.no_choice);
        yes = (Button) findViewById(R.id.yes_choice);
        call_on = (Button) findViewById(R.id.callOn_choice);
        submit = (Button) findViewById(R.id.submit_button);
        progress = (ProgressBar) findViewById(R.id.progress1);
        textProgress = (TextView) findViewById(R.id.text_progress1);
        Intent intent = getIntent();
        welcome.setText("Welcome " + intent.getStringExtra("NAME").trim());
        counter = 0;
        score = 0;
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_isClicked = true;
                yes_isClicked = false;
                callOn_isClicked = false;
                score = score + 1;
                option_handling(no, yes, call_on);
            }
        });

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_isClicked = false;
                yes_isClicked = true;
                callOn_isClicked = false;
                option_handling(yes, no, call_on);
            }
        });

        call_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no_isClicked = false;
                yes_isClicked = false;
                callOn_isClicked = true;
                option_handling(call_on, yes, no);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (submit.getText().equals("NEXT"))
                {

                    switchActivity(intent.getStringExtra("NAME").trim());
                }
                else {
                    if (no_isClicked) {
                        no.setBackgroundColor(getColor(R.color.right));
                        button_disable();
                        option_isClicked();
                    } else if (yes_isClicked) {
                        answer_handling(yes);
                        button_disable();
                        option_isClicked();
                    } else if (callOn_isClicked){
                        answer_handling(call_on);
                        button_disable();
                        option_isClicked();
                    }
                    no_isClicked = false;
                    yes_isClicked = false;
                    callOn_isClicked = false;
                }
            }
        });
    }

    public void option_isClicked() {
        counter = counter + 1;
        progress.setProgress(counter);
        progress.setMax(5);
        textProgress.setText("1/5");
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
        no.setBackgroundColor(getColor(R.color.right));
        chosen_option.setBackgroundColor(getColor(R.color.wrong));
    }

    public void button_disable()
    {
        no.setEnabled(false);
        yes.setEnabled(false);
        call_on.setEnabled(false);
    }

    public void switchActivity(String name)
    {
        Intent transition = new Intent(first_questionAct.this, second_questionAct.class);
        transition.putExtra("name", name);
        transition.putExtra("score", score);
        transition.putExtra("progress_count", counter);
        startActivity(transition);
        finish();
    }


}