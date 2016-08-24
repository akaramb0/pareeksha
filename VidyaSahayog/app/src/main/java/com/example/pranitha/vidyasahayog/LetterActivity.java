package com.example.pranitha.vidyasahayog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class LetterActivity extends Activity implements
        TextToSpeech.OnInitListener,View.OnClickListener{
TextView nammegreeting;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    ImageView fruitImage;
    String speaktest;
    private TextToSpeech tts;
    int count=0;
    int score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
        nammegreeting= (TextView)findViewById(R.id.namegreeting);
        nammegreeting.setText("Hi "+getIntent().getStringExtra("name") +
                "!!");
        speaktest= "Hi "+getIntent().getStringExtra("name") +
                "! "+"Select Starting Letter of this Fruit!";
        tts = new TextToSpeech(this, this);
        fruitImage= (ImageView)findViewById(R.id.imageselection);
        button1= (Button)findViewById(R.id.button1);
        button2= (Button)findViewById(R.id.button2);
        button3= (Button)findViewById(R.id.button3);
        button4= (Button)findViewById(R.id.button4);
        button1.setTag("1");
        button2.setTag("2");
        button3.setTag("3");
        button4.setTag("4");
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        count=count+1;
        String tag = v.getTag().toString();
        switch(count){
            case 1:
                if(tag.equalsIgnoreCase("1"))
                    score=score+1;
                fruitImage.setBackground(getResources().getDrawable(R.drawable.mangofruit));
                button2.setText("M");
                break;
            case 2:
                if(tag.equalsIgnoreCase("2"))
                    score=score+1;
                fruitImage.setBackground(getResources().getDrawable(R.drawable.guavafruit));
                button4.setText("G");
                break;
            case 3:
                if(tag.equalsIgnoreCase("4"))
                    score=score+1;
                fruitImage.setBackground(getResources().getDrawable(R.drawable.orangefruit));
                button3.setText("O");
                break;
            case 4:
                if(tag.equalsIgnoreCase("3"))
                    score=score+1;
                fruitImage.setBackground(getResources().getDrawable(R.drawable.pineapple1));
                button1.setText("P");
                break;
            case 5:
                if(tag.equalsIgnoreCase("1"))
                    score=score+1;
                Intent i = new Intent(getBaseContext(), ResultActivity.class);
                i.putExtra("score",String.valueOf(score));
                startActivity(i);
                break;
            default:
                break;
        }
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
