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

import java.util.HashSet;
import java.util.Set;

public class AddGroceryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    private SharedPreferences sharedPreferences;
    private final static String SHARED_PREF_GROCERY_ITEM = "SHARED_PREF_GROCERY_ITEM";
    private final static Set<String> SHARED_PREF_GROCERY_ITEMS = new HashSet<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_grocery);

        ImageButton button = findViewById(R.id.addRecipeBack);
        button.setOnClickListener(this);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        /*
        Set up shared preferences
         */
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_final), MODE_PRIVATE);

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.addRecipeBack) {
            setResult(RESULT_CANCELED);
            finish();
        } else if (eventSourceId == R.id.saveButton) {
            handleSave();
        } else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }

    public void handleSave(){
        EditText editText = findViewById(R.id.editTextRecipeItem);
        String newItem = editText.getText().toString();
        Log.d(TAG, "GroceryItem: \"" + newItem);

        //pull current values
        Set<String> defVals = new HashSet<String>();
        Set<String> currentItems = sharedPreferences.getStringSet("SHARED_PREF_GROCERY_ITEM", defVals);
        Log.d(TAG, String.format("add grocery handle save:" + currentItems));

        //add to new list
        currentItems.add(newItem);
        //push to shred pref

        //add to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //putString
        editor.putStringSet(SHARED_PREF_GROCERY_ITEM, currentItems);
        editor.apply();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.EXTRA_RETURN_NEW_ITEM, newItem);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}

