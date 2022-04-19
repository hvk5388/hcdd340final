package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.material.snackbar.Snackbar;

public class ShowGroceryActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PROJECT_FINAL";

    public static final String EXTRA_RETURN_NEW_ITEM = "RETURN_NEW_ITEM";
    private final static String SHARED_PREF_GROCERY_ITEM = "SHARED_PREF_GROCERY_ITEM";


    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_list);

        ImageButton button = findViewById(R.id.groceryBack);
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

        if(currentSP == ""){
            //if it is have no content say that
//            LinearLayout parentLayout = (LinearLayout)findViewById(R.id.layout);
//            parentLayout.addView("No Groceries Needed :)");
        } else {
              //if not publish children to parent element
//              // Parent layout
//              LinearLayout parentLayout = (LinearLayout)findViewById(R.id.layout);
//
//              // Layout inflater
//              LayoutInflater layoutInflater = getLayoutInflater();
//              View view;
//
//              for (int i = 1; i < 101; i++){
//                  // Add the text layout to the parent layout
//                  view = layoutInflater.inflate(R.layout.text_layout, parentLayout, false);
//
//                  // In order to get the view we have to use the new view with text_layout in it
//                  TextView textView = (TextView)view.findViewById(R.id.text);
//                  textView.setText("Row " + i);
//
//                  // Add the text view to the parent layout
//                  parentLayout.addView(textView);
//              }
        }

        //addView()
    }
}
