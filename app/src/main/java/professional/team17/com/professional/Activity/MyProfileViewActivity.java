/*
 * MyProfileViewActivity
 *
 * March 13, 2018
 *
 * Copyright @ 2018 Team 17, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */
package professional.team17.com.professional.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import professional.team17.com.professional.R;
import professional.team17.com.professional.Controllers.ServerHelper;

/**
 * MyProfileViewActivity displays information of current user
 * @author Hailan
 * @version 2.0 Last updated: Mar.13, 2018
 * @see ProfileViewActivity
 * @see EditMyProfileActivity
 */
public class MyProfileViewActivity extends ProfileViewActivity {
    private ImageView profilePic;
    private ServerHelper serverHelper;
    /**
     * Upon selecting UserMenuButton --> "My Profile", info is displayed until doneButton is selected
     * User can edit their info by clicking on their profile picture (goes to EditMyProfileActivity)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp();
    }

    /**
     * setUp uses initiates everything identified in the layout
     * Is used in onCreate and onResume
     */
    private void setUp() {
        currentMode.setText("My Profile");
        serverHelper = new ServerHelper(this);

        /* profilePic and doneButton selection treatments */
        profilePic = (ImageView) findViewById(R.id.profilePicButton);
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyProfileViewActivity.this, EditMyProfileActivity.class);
                startActivity(intent);
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        final String theUserName = pref.getString("username", "error");

        setInfo(theUserName);

    }

    /**
     * uses the setUp method (to possibly refresh and show edited profile information)
     */
    protected void onResume(){
        super.onResume();
        setUp();
    }


}

