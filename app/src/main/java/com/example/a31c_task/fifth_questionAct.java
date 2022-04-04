package com.example.a31c_task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class fifth_questionAct extends AppCompatActivity {
    private TextView welcome, textProgress;
    private Button ID, editable, text, submit;
    private boolean ID_isClicked, editable_isClicked, text_isClicked;
    private ProgressBar progress;
    private int score, counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_question);

        welcome = (TextView) findViewById(R.id.welcome_view5);
        ID = (Button) findViewById(R.id.ID_choice);
        editable = (Button) findViewById(R.id.editable_choice);
        text = (Button) findViewById(R.id.text_choice);
        submit = (Button) findViewById(R.id.submit_button5);
        progress = (ProgressBar) findViewById(R.id.progress5);
        textProgress = (TextView) findViewById(R.id.text_progress5);
        Intent intent = getIntent();
        welcome.setText("Welcome " + intent.getStringExtra("name"));
        counter = intent.getIntExtra("progress_count", 0);
        score = intent.getIntExtra("score", 0);
        textProgress.setText(counter + "/5");
        progress.setProgress(counter);
        progress.setMax(5);

        ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID_isClicked = true;
                editable_isClicked = false;
                text_isClicked = false;
                score = score +1;
                option_handling(ID, editable, text);
            }
        });

        editable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID_isClicked = false;
                editable_isClicked = true;
                text_isClicked = false;
                option_handling(editable, ID, text);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID_isClicked = false;
                editable_isClicked = false;
                text_isClicked = true;
                option_handling(text, editable, ID);
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
                    if (ID_isClicked) {
                        ID.setBackgroundColor(getColor(R.color.right));
                        option_isClicked();
                        button_disable();
                    } else if (editable_isClicked) {
                        answer_handling(editable);
                        option_isClicked();
                        button_disable();
                    } else if (text_isClicked){
                        answer_handling(text);
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
        ID.setBackgroundColor(getColor(R.color.right));
        chosen_option.setBackgroundColor(getColor(R.color.wrong));
    }

    public void button_disable()
    {
        ID.setEnabled(false);
        editable.setEnabled(false);
        text.setEnabled(false);
    }

    public void switchActivity(String name)
    {
        Intent transition3 = new Intent(fifth_questionAct.this, resultAct.class);
        transition3.putExtra("name", name);
        transition3.putExtra("score", score);
        startActivity(transition3);
        finish();
    }
}