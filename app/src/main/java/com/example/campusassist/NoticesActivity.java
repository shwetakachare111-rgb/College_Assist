package com.example.campusassist;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class NoticesActivity extends AppCompatActivity {

    ListView noticesListView;

    TextToSpeech textToSpeech;

    ArrayList<String> noticesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notices);

        noticesListView = findViewById(R.id.noticesListView);

        noticesList = new ArrayList<>();

        noticesList.add("Exam timetable will be displayed soon.");
        noticesList.add("College will remain closed on the upcoming holiday.");
        noticesList.add("Students are requested to keep the campus clean.");
        noticesList.add("Annual college event registration is now open.");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                noticesList
        );

        noticesListView.setAdapter(adapter);

        // Text-to-Speech
        textToSpeech = new TextToSpeech(
                this,
                status -> {

                    if (status == TextToSpeech.SUCCESS) {

                        textToSpeech.setLanguage(Locale.US);
                    }
                }
        );

        // Tap a notice to read it aloud
        noticesListView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    String notice = noticesList.get(position);

                    textToSpeech.speak(
                            notice,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            null
                    );

                    Toast.makeText(
                            NoticesActivity.this,
                            "Reading notice...",
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );
    }

    @Override
    protected void onDestroy() {

        if (textToSpeech != null) {

            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();
    }
}