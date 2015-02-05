package com.rezonate.decidrr;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.Random;

import io.fabric.sdk.android.Fabric;

public class AnswerActivity extends ActionBarActivity {

    private String answerS = "";
    private String question = "";
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Fabric.with(this, new TweetComposer());


        TextView questionTextView = (TextView) findViewById(R.id.question);
        Intent intent = getIntent();
        question = intent.getExtras().getString("question");
        questionTextView.setText(question);
        TextView answer = (TextView) findViewById(R.id.answer);

        if(random.nextBoolean() == true) {
            answer.setText("YES");
            answerS = "Yes";
        }

        else {
            answer.setText("NO");
            answerS = "No";
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void tweet(View view) {
        int i = 0;
        if(question.length() < 88) {
            i = question.length();
        }
        else {
            i = 87;
        }

            TweetComposer.Builder builder = new TweetComposer.Builder(this)
                .text("I just asked @Decidrr: " + question.substring(0,i) + "... and it said " + answerS);

        builder.show();
    }
    public void switcher(View view) {
        TextView answer = (TextView) findViewById(R.id.answer);
        if(random.nextBoolean() == true) {
            answer.setText("NO");
            answerS = "No";
        }

        else {
            answer.setText("YES");
            answerS = "Yes";
        }
    }
}
