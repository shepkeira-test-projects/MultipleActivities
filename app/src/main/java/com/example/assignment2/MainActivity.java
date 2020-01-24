package com.example.assignment2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String first_name_text;
    String last_name_text;
    String student_number_text;
    EditText firstName;
    EditText lastName;
    EditText studentNumber;
    Button mButton;
    RadioGroup gender;
    Boolean checked = false;
    String radio_button;
    Spinner spinner;
    String spinner_text = "COSC";

    public static MainActivity instanceOfMainActivity;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanceOfMainActivity = this;

        firstName = (EditText)findViewById(R.id.first_name);
        lastName = (EditText)findViewById(R.id.last_name);
        studentNumber = (EditText)findViewById(R.id.student_number);
        mButton = (Button)findViewById(R.id.button);
        gender = (RadioGroup)findViewById(R.id.gender_radio);
        spinner = (Spinner) findViewById(R.id.major_spinner);
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_classes, R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            spinner_text = spinner.getItemAtPosition(position).toString();
                        }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                int selectedRadioButtonId = gender.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonId);
                    String selectedRadioButtonText = selectedRadioButton.getText().toString();
                    radio_button = selectedRadioButtonText;
                }
                first_name_text = firstName.getText().toString();
                last_name_text = lastName.getText().toString();
                student_number_text = studentNumber.getText().toString();

                if (checked) {
                    boolean numeric = true;
                    boolean first_char = true;
                    boolean last_char = true;

                    try {
                        Double num = Double.parseDouble(student_number_text);
                    } catch (NumberFormatException e) {
                        numeric = false;
                    }

                    for (int i = 0; i < first_name_text.length(); i++) {
                        if ((Character.isLetter(first_name_text.charAt(i)) == false)) {
                            first_char = false;
                        }
                    }

                    for (int i = 0; i < last_name_text.length(); i++) {
                        if ((Character.isLetter(last_name_text.charAt(i)) == false)) {
                            last_char = false;
                        }
                    }


                    if (first_name_text.isEmpty() || first_name_text.length() == 0 || !first_char) {
                        Toast toast;
                        Toast.makeText(getApplicationContext(), "Invalid First Name", Toast.LENGTH_SHORT).show();
                    }
                    else if (last_name_text.isEmpty() || last_name_text.length() == 0 || !last_char) {
                        Toast toast;
                        Toast.makeText(getApplicationContext(), "Invalid Last Name", Toast.LENGTH_SHORT).show();
                    }
                    else if (student_number_text.isEmpty() || student_number_text.length() != 8 || !numeric) {
                        Toast toast;
                        Toast.makeText(getApplicationContext(), "Invalid Student Number", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                        intent.putExtra("text_id", first_name_text);
                        intent.putExtra("last_name", last_name_text);
                        intent.putExtra("student_number", student_number_text);
                        intent.putExtra("Radio_R", radio_button);
                        intent.putExtra("Spinner_Major", spinner_text);

                        startActivity(intent);

                        firstName.setText("");
                        lastName.setText("");
                        studentNumber.setText("");

                        spinner.setSelection(0);

                        gender.clearCheck();
                        gender.check(R.id.radio_f);
                    }
                }
            }

            });
    }

    public void next_activity(View view) {
        if (checked) {
            first_name_text = firstName.getText().toString();
            last_name_text = lastName.getText().toString();
            student_number_text = studentNumber.getText().toString();
            //String radio_result = gender.toString();

            Intent intent = new Intent(this, Main2Activity.class);

            intent.putExtra("text_id", first_name_text);
            intent.putExtra("last_name", last_name_text);
            intent.putExtra("student_number", student_number_text);
            intent.putExtra("Radio_R", radio_button);
            intent.putExtra("Spinner_Major", spinner_text);


            startActivityForResult(intent, 2);

        } else {

        }
    }
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //
        if (resultCode == 2) {
            MainActivity.this.finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

