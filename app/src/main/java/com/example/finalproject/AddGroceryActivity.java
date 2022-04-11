package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class AddGroceryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grocery);

        ImageButton button = findViewById(R.id.addGroceryBack);
        button.setOnClickListener(this);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.addGroceryBack) {
            setResult(RESULT_CANCELED);
            finish();
        } else if (eventSourceId == R.id.saveButton) {
            setResult(RESULT_CANCELED);
            finish();
        } else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }
}

