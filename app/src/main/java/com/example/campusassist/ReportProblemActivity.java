package com.example.campusassist;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ReportProblemActivity extends AppCompatActivity {

    EditText titleEditText;
    EditText locationEditText;
    EditText descriptionEditText;

    Spinner categorySpinner;

    RadioGroup priorityRadioGroup;

    Button dateButton;
    Button submitButton;

    String selectedDate = "";

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_report_problem);

        titleEditText = findViewById(R.id.titleEditText);
        locationEditText = findViewById(R.id.locationEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);

        categorySpinner = findViewById(R.id.categorySpinner);

        priorityRadioGroup = findViewById(R.id.priorityRadioGroup);

        dateButton = findViewById(R.id.dateButton);
        submitButton = findViewById(R.id.submitButton);

        databaseHelper = new DatabaseHelper(this);

        // Category dropdown
        String[] categories = {
                "Select Category",
                "Electricity",
                "Water",
                "Cleanliness",
                "Classroom",
                "Laboratory",
                "Other"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categories
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categorySpinner.setAdapter(adapter);

        // Date Picker
        dateButton.setOnClickListener(view -> {

            Calendar calendar = Calendar.getInstance();

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            ReportProblemActivity.this,
                            (datePicker, selectedYear, selectedMonth, selectedDay) -> {

                                selectedDate = selectedDay + "/" +
                                        (selectedMonth + 1) + "/" +
                                        selectedYear;

                                dateButton.setText(selectedDate);
                            },
                            year,
                            month,
                            day
                    );

            datePickerDialog.show();
        });

        // Submit Complaint
        submitButton.setOnClickListener(view -> {

            String title = titleEditText
                    .getText()
                    .toString()
                    .trim();

            String location = locationEditText
                    .getText()
                    .toString()
                    .trim();

            String description = descriptionEditText
                    .getText()
                    .toString()
                    .trim();

            String category =
                    categorySpinner.getSelectedItem().toString();

            int selectedId =
                    priorityRadioGroup.getCheckedRadioButtonId();

            // Validate title
            if (title.isEmpty()) {
                titleEditText.setError("Enter problem title");
                return;
            }

            // Validate category
            if (category.equals("Select Category")) {

                Toast.makeText(
                        this,
                        "Please select a category",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Validate location
            if (location.isEmpty()) {
                locationEditText.setError("Enter location");
                return;
            }

            // Validate description
            if (description.isEmpty()) {
                descriptionEditText.setError("Enter description");
                return;
            }

            // Validate priority
            if (selectedId == -1) {

                Toast.makeText(
                        this,
                        "Please select priority",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Validate date
            if (selectedDate.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please select date",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            RadioButton selectedRadioButton =
                    findViewById(selectedId);

            String priority =
                    selectedRadioButton.getText().toString();

            // Save complaint to SQLite
            boolean inserted = databaseHelper.insertComplaint(
                    title,
                    category,
                    location,
                    description,
                    priority,
                    selectedDate
            );

            if (inserted) {

                Toast.makeText(
                        this,
                        "Complaint Submitted Successfully!",
                        Toast.LENGTH_LONG
                ).show();

                // Clear form
                titleEditText.setText("");
                locationEditText.setText("");
                descriptionEditText.setText("");

                categorySpinner.setSelection(0);

                priorityRadioGroup.clearCheck();

                selectedDate = "";
                dateButton.setText("SELECT DATE");

            } else {

                Toast.makeText(
                        this,
                        "Failed to submit complaint",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}