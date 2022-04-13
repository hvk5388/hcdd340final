package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe);

        ImageButton button = findViewById(R.id.addRecipeBack);
        button.setOnClickListener(this);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.addRecipeBack) {
            setResult(RESULT_CANCELED);
            finish();
        } else if (eventSourceId == R.id.saveButton) {
            EditText editTextItem = findViewById(R.id.editTextRecipeItem);
            EditText editTextStep = findViewById(R.id.editTextRecipeStep);
            String newItem = editTextItem.getText().toString();
            String newStep = editTextStep.getText().toString();
            Log.d(TAG, "Item: \"" + newItem);
            Log.d(TAG, "Step: \"" + newStep);


            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.EXTRA_RETURN_NEW_ITEM, newItem);
            returnIntent.putExtra(MainActivity.EXTRA_RETURN_NEW_STEP, newStep);
            setResult(RESULT_OK, returnIntent);
            finish();
        } else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }

    public void handleSave(){

    }
}


