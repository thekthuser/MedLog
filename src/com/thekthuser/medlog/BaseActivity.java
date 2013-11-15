package com.thekthuser.medlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;

public class BaseActivity extends Activity
{
    /** Called when the activity is first created. */
    /*@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                profile();
                return true;
            case R.id.menu_manage:
                manage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void profile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
        }

    public void manage() {
        Intent intent = new Intent(this, Manage.class);
        startActivity(intent);
    }

}
