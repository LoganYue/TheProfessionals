package professional.team17.com.professional.Activity;


import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import professional.team17.com.professional.Dialogs.DatePickerFragment;
import professional.team17.com.professional.R;
import professional.team17.com.professional.Controllers.RequesterTaskController;

/**
 * @author Lauren, Hailan
 * @see RequesterAddTaskActivity
 * @see RequesterEditTaskActivity
 * @see RequesterTaskController
 */
public abstract class RequesterTaskActivity extends Navigation {
    private static final String TAG = "RequesterTaskActivity";
    /* Layout objects */
    protected EditText nameField;
    protected EditText descriptionField;
    protected EditText locationField;
    protected TextView textualDateView;
    protected TextView photoTextView;
    protected ImageButton addPhotoButton;
    protected ImageButton selectDateButton;
    protected MapView mapView;
    protected Button submitButton;
    /* other variables */

    protected String dateString;
    protected String locationString;
    protected LatLng latLng;
    protected String message;
    protected ArrayList<String> photos;
    protected String photo;


    protected RequesterTaskController requesterTaskController;

    /**
     * On creation of the activity, set all view objects and onClickListeners.
     * @param savedInstanceState The activity's previously saved state.
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_requester_task);
        setTitle();
        setController();
        /* Set all view objects */
        nameField = (EditText) findViewById(R.id.TaskNameField);
        descriptionField = (EditText) findViewById(R.id.taskDescriptionField);
        locationField = (EditText) findViewById(R.id.textualAddressField);
        textualDateView = (TextView) findViewById(R.id.textualDateView);
        addPhotoButton = (ImageButton) findViewById(R.id.addPhotoButton);
        selectDateButton = (ImageButton) findViewById(R.id.calendarButton);
        mapView = (MapView) findViewById(R.id.map_task);
        submitButton = (Button) findViewById(R.id.submitButton);
        photoTextView = (TextView) findViewById(R.id.photoTextView);
        photos = new ArrayList<String>();

        /* Set all onClickListeners */

            addPhotoButton.setOnClickListener(new ImageButton.OnClickListener() {
             @Override
                public void onClick(View v) {
                //changeActivity(TaskPhotoActivity.class);
                 Intent intent = new Intent( RequesterTaskActivity.this, TaskPhotoActivity.class);
                 intent.putStringArrayListExtra("photos", photos);
                 startActivityForResult(intent, 0);



             }});


        selectDateButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Show the DatePickerDialog */
                displayDatePicker();
                /* Get the formatted date */

            }
        });

        locationField.setInputType(InputType.TYPE_NULL);
        locationField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsSearchLocationActivity.class);
                if (locationField.getText() != null){
                    intent.putExtra("addressTyped", locationField.getText().toString());
                    Log.d(TAG, "Num1 onClick: " + locationField.getText());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("latLonGot", latLng);
                    intent.putExtras(bundle);
                }
                startActivityForResult(intent, 1);
            }
        });

        setSubmitButtonOnClickListener();

        getTask();

    }

    /**
     * Upon clicking on add button, check task info before adding to server.
     */
    public void setSubmitButtonOnClickListener() {
        submitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Convert user entered values to strings */
                String title = nameField.getText().toString();
                String description = descriptionField.getText().toString();
                locationString = locationField.getText().toString();
                dateString = (String) textualDateView.getText();


                if (title.length() > 30) {
                    /* if the title is too long */
                    message = "Title cannot be longer than 30 characters.";
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                } else if (description.length() > 300) {
                    /* if the description is too long */
                    message = "Description cannot be longer than 300 characters.";
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                } else if (title.isEmpty()) {
                    /* if the title is empty */
                    message = "You must include a title.";
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                } else if (description.isEmpty()) {
                    /* if the title is empty */
                    message = "You must include a description.";
                    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    /* Create an intent and bundle and store all task info */
                    addToServer(title, description);

                    /* Activity finished, start RequesterViewListActivity */
                    endActivity();
                }

            }
        });
    }

    /**
     * Abstract methods
     */
    public abstract void setController();
    public abstract void getTask();
    public abstract void endActivity();
    public abstract void setTitle();
    public abstract void addToServer(String title, String description);

    /**
     * Displays the DatePickerDialog fragment, allowing the user to select a date.
     */
    private void displayDatePicker(){
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
    }

    /**
     * Get data from MapsSearchLocationActivity.java or TaskPhotoActivity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                latLng = intent.getParcelableExtra("taskLatLng");
                locationField.setText(intent.getStringExtra("taskAddress"));
            }
        }
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                photos = intent.getStringArrayListExtra("yourImage");
            }
        }
    }

}
