package professional.team17.com.professional;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import professional.team17.com.professional.navigation.NavFactory;
import professional.team17.com.professional.navigation.navButton;

/**
 * Created by ag on 2018-03-26.
 */

public abstract  class Navigation extends AppCompatActivity implements ImageView.OnClickListener {


    /**
     * On creation of the activity, assign values to all variables.
     *
     * @param savedInstanceState The activity's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    /**
     * Changes the title at the top of the layout.
     *
     * @param title The title of the activity being displayed.
     */
    public void setActivityTitleRequester(final String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView requesteractivityTitleView = (TextView) findViewById(R.id.requesteractivityTitleView);
                requesteractivityTitleView.setText(title);
            }
        });
    }

    /**
     * Changes the title at the top of the layout.
     *
     * @param title The title of the activity being displayed.
     */
    public void setActivityTitleProvider(final String title) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView provideractivityTitleView = (TextView) findViewById(R.id.provideractivityTitleView);
                provideractivityTitleView.setText(title);
            }
        });
    }
    protected void end(Intent intent){

    }
    /**
     * Handles all click events for the bottom navigation and the user button in the
     * top right corner of the layout.
     *
     * @param view The view object that has been clicked.
     */
    public void onClick(View view) {
        if (view.getId() == R.id.userMenuButton){
            showPopup();
        }
        else if (view.getId()==R.id.taskMapButton){
            if(checkServices()){
                Intent intent = new Intent(this, MapsSearchTasksActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else {
            navButton navButton = NavFactory.makeFor(view.getId(), this);
            startActivity(navButton.getIntent());
            finish();
        }
    }

    private boolean checkServices(){
        String TAG = "ProviderLayout";
        Log.d(TAG, "checkServices - google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (available == ConnectionResult.SUCCESS){
            Toast.makeText(this, "Google Services is OK", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "checkServices - google services is ok");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Toast.makeText(this, "Google Services is NOT Ok", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "checkServices - okay error");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, 9001);
            dialog.show();
        } else {
            Toast.makeText(this, "Cannot make map request.", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
















    /**
     * Creates the popup menu displayed when the user clicks on the userMenuButton.
     */
    protected void showPopup(){
        /* Create Popup Menu */
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.userMenuButton));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //Toast.makeText(ProviderLayout.this, "" + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                if(menuItem.getTitle().equals("My Profile")){
                    Intent intent = new Intent(getApplicationContext(), MyProfileViewActivity.class);
                    startActivity(intent);
                } else if (menuItem.getTitle().equals("Edit My Profile")){
                    Intent intent = new Intent(getApplicationContext(), EditMyProfileActivity.class);
                    startActivity(intent);
                } else if (menuItem.getTitle().equals("My Notifications")){
                    Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        popup.show();
    }



    /**
     * When the activity starts, create the popup menu.
     */
    @Override
    protected void onStart() {
        super.onStart();


    }




}
