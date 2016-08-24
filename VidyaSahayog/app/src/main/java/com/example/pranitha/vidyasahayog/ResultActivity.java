package com.example.pranitha.vidyasahayog;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ResultActivity extends Activity implements
        TextToSpeech.OnInitListener{
Button playagain;
    TextView score;
    private TextToSpeech tts;
    String speaktest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        tts = new TextToSpeech(this, this);
        playagain = (Button)findViewById(R.id.playagainbutton);
        score = (TextView)findViewById(R.id.score);
        speaktest="Congratulations!! Your Score is "+getIntent().getStringExtra("score")+"out of 5.";
        score.setText("Congratulations!! Your Score is "+getIntent().getStringExtra("score")+"/5");
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            Log.d("tts", "success");

            tts.speak(speaktest, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }
}
