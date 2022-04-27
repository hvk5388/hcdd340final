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

import java.util.HashSet;
import java.util.Set;

public class ShowGroceryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    //public static final Set<String> EXTRA_RETURN_NEW_ITEMS = new HashSet<String>();
    //"RETURN_NEW_ITEM";
    private final static String SHARED_PREF_GROCERY_ITEM = "SHARED_PREF_GROCERY_ITEM";


    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        ImageButton button = findViewById(R.id.addRecipeBack);
        button.setOnClickListener(this);

        Button addButton = findViewById(R.id.addbtn);
        addButton.setOnClickListener(this);

        /*
        Set up shared preferences
         */
        sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_final), MODE_PRIVATE);

        //populate the list
        populateGroceryList();

        Log.d(TAG, "On Create");
    }

    public void onClick(View view) {
        int eventSourceId = view.getId();
        Log.d(TAG, String.format("Clicked on: %s", eventSourceId));

        if (eventSourceId == R.id.addRecipeBack) {
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
                        //Set<String> newItemsFromAdd = result.getData().getExtras().keySet(EXTRA_RETURN_NEW_ITEMS);
                        //Log.d(TAG, String.format("Result OK " + newItemsFromAdd));

                        //positive snackbar
                        Snackbar.make(findViewById(R.id.addbtn), "Grocery Added", Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.green)).show();

                        //get from shared preferences
                        String defVal= "";
                        String newItemFromSP = sharedPreferences.getString("SHARED_PREF_GROCERY_ITEM", defVal);
                        Log.d(TAG, String.format("shared pref:" + newItemFromSP));

                        //add item to list
                        populateGroceryList();

                    } else if (resultCode == RESULT_CANCELED) {
                        Log.d(TAG, "Canceled from AddGroceryActivity");

                        //negative snackbar
                        Snackbar.make(findViewById(R.id.addbtn), "No Groceries Added", Snackbar.LENGTH_LONG).setBackgroundTint(getColor(R.color.red)).show();
                    } else {
                        Log.d(TAG, String.format("Unknown return code from AddGroceryActivity: %s", resultCode));
                    }
                }
            }
    );

    //populate grocery list
    private void populateGroceryList() {
        //check if shared preferences is empty
        String currentSP = sharedPreferences.getString(SHARED_PREF_GROCERY_ITEM, "");

        //use addView
        if(currentSP == ""){
            //if it is have no content say that
            LinearLayout parentLayout = (LinearLayout)findViewById(R.id.list);

            TextView emptyMessage = (TextView) findViewById(R.id.itemTextView);
            emptyMessage.setText("No Groceries Needed");
            parentLayout.removeAllViews();
            parentLayout.addView(emptyMessage);
        } else {
            Log.d(TAG, String.format("shared pref in populate:" + currentSP));

            //if not publish children to parent element
            // Parent layout
            LinearLayout parentLayout = (LinearLayout)findViewById(R.id.list);

//            String defVal= "";
//            String newItemFromSP = sharedPreferences.getString("SHARED_PREF_GROCERY_ITEM", defVal);

//            Set<String> defVals = new HashSet<String>();
//            Set<String> newItemsFromSP = sharedPreferences.getStringSet("SHARED_PREF_GROCERY_ITEM", defVals);

//            for( String item : newItemsFromSP ) {
//                TextView message = (TextView) findViewById(R.id.textView);
//                message.setText(item);
//                parentLayout.removeAllViews();
//                parentLayout.addView(message);
//            }

            TextView message = (TextView) findViewById(R.id.itemTextView);
            message.setText(currentSP);
            parentLayout.removeAllViews();
            parentLayout.addView(message);
        }
    }
}
