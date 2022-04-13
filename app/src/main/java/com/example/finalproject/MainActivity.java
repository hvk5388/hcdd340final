package com.example.finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "FINAL_PROJECT";

    public static final String EXTRA_RETURN_NEW_ITEM = "RETURN_NEW_ITEM";
    public static final String EXTRA_RETURN_NEW_STEP = "RETURN_NEW_STEP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.recipe);
        button.setOnClickListener(this);

        button = findViewById(R.id.grocery);
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("event source id: %s", eventSourceId));

        if (eventSourceId == R.id.recipe) {
            Log.d(TAG, String.format("main: caught recipe click event"));
            handleRecipeButtonClick();
        } else if (eventSourceId == R.id.grocery) {
            Log.d(TAG, String.format("main: caught grocery click event"));
            handleGroceryButtonClick();
        }
        else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }

    /**
     * Handle the click event for the "Recipe" Button
     */
    private void handleRecipeButtonClick() {
        Intent intent = new Intent(this, ShowRecipeActivity.class);

        recipeGetStatus.launch(intent);
    }

    ActivityResultLauncher<Intent> recipeGetStatus = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        Log.d(TAG, "Result OK");
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "main: Canceled from ShowRecipeActivity");
                    } else {
                        Log.d(TAG, String.format("Unknown return code from ShowRecipeActivity: %s", resultCode));
                    }
                }
            }
    );


    /**
     * Handle the click event for the "Grocery" Button
     */
    private void handleGroceryButtonClick() {
        Intent intent = new Intent(this, ShowGroceryActivity.class);

        groceryGetStatus.launch(intent);
    }

    ActivityResultLauncher<Intent> groceryGetStatus = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        Log.d(TAG, "Result OK");
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled from ShowGroceryActivity");
                    } else {
                        Log.d(TAG, String.format("Unknown return code from ShowGroceryActivity: %s", resultCode));
                    }
                }
            }
    );
}