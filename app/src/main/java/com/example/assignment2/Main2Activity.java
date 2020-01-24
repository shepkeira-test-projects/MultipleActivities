package com.example.assignment2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

public class Main2Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        String firstName = getIntent().getStringExtra("text_id");
        String lastName = getIntent().getStringExtra("last_name");
        String stuNum = getIntent().getStringExtra("student_number");
        String gen = getIntent().getStringExtra("Radio_R");
        String spin = getIntent().getStringExtra("Spinner_Major");

        TextView textView = (TextView) findViewById(R.id.name);
        TextView textView1 = (TextView) findViewById(R.id.Student_Number);
        TextView textView2 = (TextView) findViewById(R.id.major);
        TextView textView3 = (TextView) findViewById(R.id.gender_result);

        String name = "Student Name: " + firstName + " " + lastName;
        stuNum = "Student Number: " + stuNum;
        gen = "Gender Identity: " + gen;
        spin = "Major: " + spin;

        textView.setText(name);
        textView1.setText(stuNum);
        textView2.setText(gen);
        textView3.setText(spin);

    }

    public void go_back(View view) {
        MainActivity.instanceOfMainActivity.finish();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onStop() {
        setResult(2);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        setResult(2);
        super.onDestroy();
    }
}
