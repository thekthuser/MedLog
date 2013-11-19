package com.thekthuser.medlog;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import java.util.ArrayList;
import android.widget.ExpandableListView;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.widget.ExpandableListView.OnChildClickListener;
import android.view.LayoutInflater;

import android.widget.SimpleExpandableListAdapter;
import java.util.HashMap;
import java.util.List;

public class Manage extends BaseListActivity {

    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.manage);

            SimpleExpandableListAdapter xList = 
                new SimpleExpandableListAdapter(
                    this,
                    createGroupList(),
                    R.layout.manage_medication,
                    new String[] {"Scientific Name", "Brand Name"},
                    new int[] {R.id.scientific_name, R.id.brand_name},
                    createChildList(),
                    R.layout.manage_prescription,
                    new String[] {"Sub Item"},
                    new int[] {R.id.prescription}
                );
            getExpandableListView().setGroupIndicator(null);
            setListAdapter(xList);
        } catch (Exception e) {
            System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEE " + e.getMessage());
        }
    }

    private List createGroupList() {
        ArrayList result = new ArrayList();
        for(int i = 0; i < 4; i++) {
            HashMap m = new HashMap();
                m.put("Scientific Name", "Scientific Name" + Integer.toString(i));
                m.put("Brand Name", "Brand Name" + Integer.toString(i));
                result.add(m);
        }
        return (List) result;
    }

    private List createChildList() {
        ArrayList result = new ArrayList();
        for(int i = 0; i < 4; i++) {
            ArrayList secList = new ArrayList();
            for(int n = 0; n < 3; n++) {
                HashMap child = new HashMap();
                child.put("Sub Item", "Sub" + Integer.toString(n));
            secList.add( child );
            }
        result.add(secList);
        }
        return result;
    }

    /*ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<String> childItem = new ArrayList<String>();
    groupItem.add("ONE");
    groupItem.add("TWO");
    groupItem.add("THREE");
    
    ArrayList<String> child = new ArrayList<String>();
    child.add("1");
    child.add("2");
    child.add("3");
    childItem.add(child);*/


    /*ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);*/
        //setContentView(R.layout.manage);
        /*String[] values = new String[] { "one", "two", "three" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            R.layout.manage_rowlayout, R.id.test, values);
        setListAdapter(adapter);*/
        /*ExpandableListView xList = getExpandableListView();
        xList.setDividerHeight(2);
        xList.setGroupIndicator(null);
        xList.setClickable(true);
        setGroupData();
        setChildGroupData();

        ManageExpandableListAdapter  mAdapter = new ManageExpandableListAdapter(groupItem, childItem);
        mAdapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
        getExpandableListView().setAdapter(mAdapter);
        xList.setOnChildClickListener(this);
    }

    public void setGroupData() {
        groupItem.add("ONE");
        groupItem.add("TWO");
        groupItem.add("THREE");
    }
    public void setChildGroupData() {
        ArrayList<String> child = new ArrayList<String>();
        child.add("1");
        child.add("2");
        child.add("3");
        childItem.add(child);
    }
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Toast.makeText(getBaseContext(), "Clicked", Toast.LENGTH_LONG).show();
        return true;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }*/

    public void toggle_new_med(View view) {
        View new_med = findViewById(R.id.new_med);
        Button new_med_button = (Button) findViewById(R.id.toggle_new_med);
        if (new_med.getVisibility() == View.VISIBLE) {
            new_med.setVisibility(View.GONE);
            new_med_button.setVisibility(View.VISIBLE);
        } else {
            new_med.setVisibility(View.VISIBLE);
            new_med_button.setVisibility(View.GONE);
        }
    }

    public void update_med(View view) {
        EditText brand = (EditText) view.findViewById(R.id.brand_name);
        EditText scientific = (EditText) view.findViewById(R.id.scientific_name);

        toggle_new_med(view);
    }

}
