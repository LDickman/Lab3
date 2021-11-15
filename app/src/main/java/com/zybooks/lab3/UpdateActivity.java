package com.zybooks.lab3;

import static com.zybooks.lab3.MainActivity.getFinalString;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG = "UpdateActivity";
    public static final String FILENAME = "madlib.txt";
    TextView userResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        userResult = findViewById(R.id.user_inputs);
        try {
            ReadTextFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void ReadTextFile() throws IOException {
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream text = openFileInput(FILENAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(text));
        Log.d(TAG, String.valueOf(text));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(string).append("\n");
            userResult.setText(stringBuilder);
        }
        //text.close();
    }
    public void onResetClick(View view) {
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
    }
}