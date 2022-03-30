package com.example.finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "FINAL_PROJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("event source id: %s", eventSourceId));

        if (eventSourceId == R.id.recipie) {
            handleRecipieButtonClick();
        } else if (eventSourceId == R.id.grocery) {
            handleGroceryButtonClick();
        }
        else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }

    /**
     * Handle the click event for the "Recipie" Button
     */
    private void handleRecipieButtonClick() {
        Intent intent = new Intent(this, ShowRecipieActivity.class);

        mGetStatus.launch(intent);
    }

    ActivityResultLauncher<Intent> mGetStatus = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        Log.d(TAG, "Result OK");
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled from ShowRecipieActivity");
                    } else {
                        Log.d(TAG, String.format("Unknown return code from ShowRecipieActivity: %s", resultCode));
                    }
                }
            }
    );


    /**
     * Handle the click event for the "Grocery" Button
     */
    private void handleGroceryButtonClick() {
        Intent intent = new Intent(this, ShowGroceryActivity.class);

        gGetStatus.launch(intent);
    }

    ActivityResultLauncher<Intent> gGetStatus = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
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