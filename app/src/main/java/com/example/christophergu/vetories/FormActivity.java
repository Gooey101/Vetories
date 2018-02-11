package com.example.christophergu.vetories;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class FormActivity extends AppCompatActivity {

    // Firebase Instance Variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private EditText mFormName;
    private EditText mFormStoryTitle;
    private EditText mFormStoryContent;
    private RadioGroup mFormGender;
    private TextView mFormDOB;
    private EditText mFormVeteranExperience;
    private EditText mFormEmail;
    private EditText mFormPhone;
    private ImageView mFormYesButton;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference= mFirebaseDatabase.getReference("stories");

        // Initialize references to views
        mFormName = findViewById(R.id.form_name_edit);
        mFormStoryTitle = findViewById(R.id.form_story_title);
        mFormStoryContent = findViewById(R.id.form_story_content);
        mFormGender = findViewById(R.id.form_gender);
        mFormDOB = findViewById(R.id.form_dob);
        mFormVeteranExperience = findViewById(R.id.form_veteran_experience);
        mFormEmail = findViewById(R.id.form_email);
        mFormPhone = findViewById(R.id.form_phone);
        mFormYesButton = findViewById(R.id.form_yes_button);

        mFormYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mFormName.getText() == null || mFormStoryTitle.getText() == null ||
                        mFormStoryContent.getText() == null || gender == null ||
                        mFormDOB.getText() == null || mFormVeteranExperience.getText() == null ||
                        mFormEmail.getText() == null || mFormPhone.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Please fill out the entire form!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Vetory vetory = new Vetory(mFormName.getText().toString(),
                            mFormStoryTitle.getText().toString(), mFormStoryContent.getText().toString(),
                            gender, mFormDOB.getText().toString(), mFormVeteranExperience.getText().toString(),
                            mFormEmail.getText().toString(), mFormPhone.getText().toString());
                    mDatabaseReference.push().setValue(vetory);

                    // Clear Input Boxes
                    mFormName.setText("");
                    mFormStoryTitle.setText("");
                    mFormStoryContent.setText("");
                    mFormVeteranExperience.setText("");
                    mFormEmail.setText("");
                    mFormPhone.setText("");

                    finish();
                }
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the piker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }


        public void onDateSet(DatePicker view, int year, int month, int day) {
            String formattedDate = (month + 1) + "/" + day + "/" + year;
            ((TextView) this.getActivity().findViewById(R.id.form_dob)).setText(formattedDate);
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.option_male:
                if (checked)
                    gender = "male";
                break;
            case R.id.option_female:
                if (checked)
                    gender = "female";
                break;
            case R.id.option_other:
                if (checked)
                    gender = "other";
                break;
        }
    }
}