package com.thekthuser.medlog;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;
import android.app.Activity;

import android.os.Bundle;
import android.view.View;

public class MenuHandler {
    private Activity activity;

    public MenuHandler (Activity activity) {
        this.activity = activity;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = activity.getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                profile();
                return true;
            case R.id.menu_manage:
                manage();
                return true;
            default:
                return activity.onOptionsItemSelected(item);
        }
    }

    public void profile() {
        Intent intent = new Intent(activity, Profile.class);
        activity.startActivity(intent);
    }

    public void manage() {
        Intent intent = new Intent(activity, Manage.class);
        activity.startActivity(intent);
    }
	
}
