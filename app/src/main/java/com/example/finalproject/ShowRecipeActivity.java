package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ShowRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    public static final String EXTRA_RETURN_NEW_INGREDIENT = "RETURN_NEW_INGREDIENT";
    public static final String EXTRA_RETURN_NEW_STEP = "RETURN_NEW_STEP";

    private final static String SHARED_PREF_NEW_INGREDIENT = "SHARED_PREF_NEW_INGREDIENT";
    private final static String SHARED_PREF_NEW_STEP = "SHARED_PREF_NEW_STEP";

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);

        ImageButton button = findViewById(R.id.addRecipeBack);
        button.setOnClickListener(this);

        Button addButton = findViewById(R.id.addRecipe);
        addButton.setOnClickListener(this);

        Button deleteButton = findViewById(R.id.deletebtn);
        deleteButton.setOnClickListener(this);

        /*
        Set up shared preferences
         */
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_final), MODE_PRIVATE);

        //populate the list
        populateRecipeList();

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.addRecipeBack) {
            Log.d(TAG, String.format("show: caught back click event"));
            setResult(RESULT_CANCELED);
            finish();
        } else if (eventSourceId == R.id.addRecipe) {
            Log.d(TAG, String.format("show: caught add click event"));
            handleAddButtonClick();
        } else if (eventSourceId == R.id.deletebtn){
            Log.d(TAG, String.format("caught delete click event"));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            populateRecipeList();
        } else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }

    /**
     * Handle the click event for the "Grocery" Button
     */
    private void handleAddButtonClick() {
        Intent intent = new Intent(this, AddRecipeActivity.class);

        addRecipeGetStatus.launch(intent);
    }

    ActivityResultLauncher<Intent> addRecipeGetStatus = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        assert result.getData() != null;
                        String newItemFromAdd = result.getData().getStringExtra(EXTRA_RETURN_NEW_INGREDIENT);
                        String newStepFromAdd = result.getData().getStringExtra(EXTRA_RETURN_NEW_STEP);
                        Log.d(TAG, String.format("Result OK. Item: " + newItemFromAdd + " Step: " + newStepFromAdd));

                        //positive snackbar
                        Snackbar.make(findViewById(R.id.addRecipe), "Recipe Added", Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.green)).show();

                        //add item to list
                        populateRecipeList();
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled from AddRecipeActivity");

                        //negative snackbar
                        Snackbar.make(findViewById(R.id.addRecipe), "No Recipe Added", Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.red)).show();
                    } else {
                        Log.d(TAG, String.format("Unknown return code from AddRecipeActivity: %s", resultCode));
                    }
                }
            }
    );

    //populate grocery list
    private void populateRecipeList() {
        //check if shared preferences is empty
        String currentItemSP = sharedPreferences.getString(SHARED_PREF_NEW_INGREDIENT, "");
        String currentStepSP = sharedPreferences.getString(SHARED_PREF_NEW_STEP, "");

        //use addView
        if(currentItemSP == ""){
            //if it is have no content say that
            LinearLayout parentLayout = (LinearLayout)findViewById(R.id.list);

            TextView emptyMessage = (TextView) findViewById(R.id.itemTextView);
            emptyMessage.setText("No Saved Recipes");
            parentLayout.removeAllViews();
            parentLayout.addView(emptyMessage);
        } else {
            Log.d(TAG, String.format("shared pref in populate:" + currentItemSP));
            Log.d(TAG, String.format("shared pref in populate:" + currentStepSP));

            //if not publish children to parent element
            // Parent layout
            LinearLayout parentLayout = (LinearLayout)findViewById(R.id.list);

            TextView message = (TextView) findViewById(R.id.itemTextView);
            message.setText(currentItemSP);
            TextView stepMessage = (TextView) findViewById(R.id.stepTextView);
            stepMessage.setText(currentStepSP);
            parentLayout.removeAllViews();
            parentLayout.addView(message);
            parentLayout.addView(stepMessage);
        }
    }
}
