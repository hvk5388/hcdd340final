package com.example.finalproject;

import android.content.SharedPreferences;
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

    private SharedPreferences sharedPreferences;
    private final static String SHARED_PREF_NEW_ITEM = "SHARED_PREF_NEW_ITEM";
    private final static String SHARED_PREF_NEW_STEP = "SHARED_PREF_NEW_STEP";


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
        EditText editText = findViewById(R.id.editTextRecipeItem);
        String newItem = editText.getText().toString();
        Log.d(TAG, "RecipeItem: \"" + newItem);

        //add to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //putString
        editor.putString(SHARED_PREF_NEW_ITEM, newItem);
        editor.apply();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.EXTRA_RETURN_NEW_ITEM, newItem);
        setResult(RESULT_OK, returnIntent);

        finish();

//        EditText editStepText = findViewById(R.id.editTextRecipeStep);
//        String newStep = editStepText.getText().toString();
//        Log.d(TAG, "RecipeStep: \"" + newStep);
//
//        //add to shared preferences
//        SharedPreferences.Editor stepEditor = sharedPreferences.edit();
//
//        //putString
//        stepEditor.putString(SHARED_PREF_NEW_STEP, newStep);
//        stepEditor.apply();
//
//        Intent returnStepIntent = new Intent();
//        returnStepIntent.putExtra(MainActivity.EXTRA_RETURN_NEW_STEP, newStep);
//        setResult(RESULT_OK, returnIntent);
//
//        finish();
    }
}


