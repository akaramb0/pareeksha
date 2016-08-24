package com.example.pranitha.vidyasahayog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class DetailsActivity extends Activity implements
        TextToSpeech.OnInitListener {
EditText nametext;
    EditText agetext;
    Button button;
    Context context;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nametext= (EditText)findViewById(R.id.nameval);
        agetext= (EditText)findViewById(R.id.ageval);
        button= (Button)findViewById(R.id.numberButton);
        tts = new TextToSpeech(this, this);
        context= this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), LetterActivity.class);
                i.putExtra("name",nametext.getText().toString());
                i.putExtra("age",agetext.getText().toString());
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

            tts.speak("Enter your Name, Age and select a Button!", TextToSpeech.QUEUE_FLUSH, null);
        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
}
