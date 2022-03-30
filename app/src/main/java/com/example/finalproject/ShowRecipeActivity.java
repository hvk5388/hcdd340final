package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ShowRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);

        ImageButton button = findViewById(R.id.recipe_back);
        button.setOnClickListener(this);

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.recipe_back) {
//            CheckBox checkBox = findViewById(R.id.details_in_office_checkbox);
//            boolean inOfficeStatus = checkBox.isChecked();
//            Intent returnIntent = new Intent();
//            returnIntent.putExtra(MainActivity.EXTRA_RETURN_IN_OFFICE, inOfficeStatus);
//            setResult(RESULT_OK, returnIntent);
//            finish();
//        } else if (eventSourceId == R.id.button_details_cancel) {
            setResult(RESULT_CANCELED);
            finish();
        }
        else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }
}
