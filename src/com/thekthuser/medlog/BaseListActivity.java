package com.thekthuser.medlog;

import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.widget.Toast;

public class BaseListActivity extends ListActivity {

    private MenuHandler mHandler;

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mHandler = new MenuHandler(this);
        }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            mHandler.onCreateOptionsMenu(menu);
            return true;
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            mHandler.onOptionsItemSelected(item);
            return true;
        }
}
