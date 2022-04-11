package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ShowGroceryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    public static final String EXTRA_RETURN_NEW_ITEM = "RETURN_NEW_ITEM";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        ImageButton button = findViewById(R.id.groceryBack);
        button.setOnClickListener(this);

        Button addButton = findViewById(R.id.addbtn);
        addButton.setOnClickListener(this);

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.groceryBack) {
            setResult(RESULT_CANCELED);
            finish();
        } else if (eventSourceId == R.id.addbtn) {
            Log.d(TAG, String.format("caught add click event"));
            handleAddButtonClick();
        } else {
            Log.d(TAG, String.format("Unknown click event source: %s", eventSourceId));
        }
    }

    /**
     * Handle the click event for the "Grocery" Button
     */
    private void handleAddButtonClick() {
        Intent intent = new Intent(this, AddGroceryActivity.class);

        addGroceryGetStatus.launch(intent);
    }

    ActivityResultLauncher<Intent> addGroceryGetStatus = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == RESULT_OK) {
                        assert result.getData() != null;
                        String newItemFromAdd = result.getData().getStringExtra(EXTRA_RETURN_NEW_ITEM);
                        Log.d(TAG, String.format("Result OK " + newItemFromAdd));

//                        // has the state changed?
//                        SwitchCompat inOfficeSwitch = findViewById(R.id.switch_in_office);
//                        if (inOfficeSwitch.isChecked() != inOfficeStatusFromDetails) {
//                            Log.d(TAG, String.format("In-office state has changed from %s to %s", inOfficeSwitch.isChecked(), inOfficeStatusFromDetails));
//                            storeInOfficeStatus(getCurrentProfileId(), inOfficeStatusFromDetails); // update shared preference
//                            inOfficeSwitch.setChecked(inOfficeStatusFromDetails); // update UI
//                        }
                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled from AddGroceryActivity");
                    } else {
                        Log.d(TAG, String.format("Unknown return code from AddGroceryActivity: %s", resultCode));
                    }
                }
            }
    );
}
