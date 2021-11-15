package com.zybooks.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "madlib.txt";
    private static final String TAG = "Main Activity";
    private String madLib_string;
    private String Final_string;
    public MadLib userMadLib;
    private EditText word1;
    private EditText word2;
    private EditText word3;
    private EditText word4;
    private EditText word5;
    private EditText word6;
    private EditText word7;
    private ArrayList<Object> editText_Array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        word1 = findViewById(R.id.editText_word1);
        word2 = findViewById(R.id.editText_word2);
        word3 = findViewById(R.id.editText_word3);
        word4 = findViewById(R.id.editText_word4);
        word5 = findViewById(R.id.editText_word5);
        word6 = findViewById(R.id.editText_word6);
        word7 = findViewById(R.id.editText_word7);

        editText_Array.add(word1.getText());
        editText_Array.add(word2.getText());
        editText_Array.add(word3.getText());
        editText_Array.add(word4.getText());
        editText_Array.add(word5.getText());
        editText_Array.add(word6.getText());
        editText_Array.add(word7.getText());
        try {
            madLib_string = readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, madLib_string);
        userMadLib = new MadLib(madLib_string);
    }

    public String readFromFile() throws IOException {
        String string = "";
        StringBuilder stringBuilder = new StringBuilder();
        InputStream text = this.getResources().openRawResource(R.raw.madlib);
        BufferedReader reader = new BufferedReader(new InputStreamReader(text));
        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }

    private void getUserInput() {
        int numEntries = userMadLib.getNumEntries();
        for (int i = 0; i < numEntries; i++) {
            userMadLib.addAnswerToEntry((i), editText_Array.get(i).toString());
        }
        Final_string = userMadLib.genCompleteMadLib();
    }

    public void saveToFile() throws IOException {
        FileOutputStream outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
        outputStream.write(Final_string.getBytes());
        outputStream.close();
    }

    public void onSubmitClick(View view) throws IOException {
        getUserInput();
        saveToFile();
        Log.d(TAG, Final_string);
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        startActivity(intent);
    }
}
