package com.thekthuser.medlog;

import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;

public class Profile extends BaseActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Intent intent = getIntent();
        setContentView(R.layout.profile);
    }

    public void updateProfile(View view) {
        /*new AlertDialog.Builder(this)
            .setTitle("Title")
            .setMessage("Message")
            .show();*/
    }


}
