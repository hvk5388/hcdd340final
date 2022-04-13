package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ShowRecipeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    public static final String EXTRA_RETURN_NEW_ITEM = "RETURN_NEW_ITEM";
    public static final String EXTRA_RETURN_NEW_STEP = "RETURN_NEW_STEP";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_page);

        ImageButton button = findViewById(R.id.recipe_back);
        button.setOnClickListener(this);

        Button addButton = findViewById(R.id.addRecipe);
        addButton.setOnClickListener(this);

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.recipe_back) {
            Log.d(TAG, String.format("show: caught back click event"));
            setResult(RESULT_CANCELED);
            finish();
        } else if (eventSourceId == R.id.addRecipe) {
            Log.d(TAG, String.format("show: caught add click event"));
            handleAddButtonClick();
        }
        else {
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
                        String newItemFromAdd = result.getData().getStringExtra(EXTRA_RETURN_NEW_ITEM);
                        String newStepFromAdd = result.getData().getStringExtra(EXTRA_RETURN_NEW_STEP);
                        Log.d(TAG, String.format("Result OK. Item: " + newItemFromAdd + " Step: " + newStepFromAdd));

                        //positive snackbar
                        Snackbar.make(findViewById(R.id.addRecipe), "Recipe Added", Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.green)).show();

                        //add item to list
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
}
