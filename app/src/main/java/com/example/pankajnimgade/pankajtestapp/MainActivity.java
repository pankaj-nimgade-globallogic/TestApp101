package com.example.pankajnimgade.pankajtestapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import test.activities.CreatePDF101Activity;
import test.activities.CreatePDF103Activity;
import test.activities.CreatePDF104Activity;
import test.activities.CreatePDF105Activity;
import test.activities.CreatePDFTableActivity;
import test.activities.ExampleOneActivity;
import test.activities.GetPackagesActivity;
import test.activities.OpenPDFActivity;
import test.activities.SystemInfoActivity;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initializeUI();
    }

    private void initializeUI() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.MainActivity_CoordinatorLayout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.MainActivity_list_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        final List<MyItem> myItems = new ArrayList<>();
        myItems.add(new MyItem("Example One", "this is to check recyclerView click", ExampleOneActivity.class));
        myItems.add(new MyItem("Get Package", "get list of application installed on the phone", GetPackagesActivity.class));
        myItems.add(new MyItem("System Info", "gives various information about system", SystemInfoActivity.class));
        myItems.add(new MyItem("Open PDF", "tries to open a pdf from assets", OpenPDFActivity.class));
        myItems.add(new MyItem("Create PDF 101", "Create a pdf file 101 test",
                CreatePDF101Activity.class));
        myItems.add(new MyItem("Create PDF table 102", "Create a pdf table 102",CreatePDFTableActivity.class));
        myItems.add(new MyItem("Create PDF 103", "Create a pdf table 103",CreatePDF103Activity.class));
        myItems.add(new MyItem("Create PDF 104", "Create a pdf table 104",CreatePDF104Activity.class));
        myItems.add(new MyItem("Create PDF 105", "Create a pdf table 105",CreatePDF105Activity.class));
        MyListAdapter myListAdapter = new MyListAdapter(getApplicationContext(), myItems);
        recyclerView.setAdapter(myListAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyItem myItem = myItems.get(position);
                Snackbar.make(coordinatorLayout, myItem.getMetadata(),Snackbar.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), myItem.getActivityClass()));
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

        public OnItemClickListener onItemClickListener;
        public GestureDetector gestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && onItemClickListener != null && gestureDetector.onTouchEvent(e)) {
                onItemClickListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }
    }
}



