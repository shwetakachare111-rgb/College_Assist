package com.example.campusassist;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyComplaintsActivity extends AppCompatActivity {

    ListView complaintsListView;

    DatabaseHelper databaseHelper;

    ArrayList<String> complaintsList;
    ArrayList<Integer> complaintIds;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_complaints);

        complaintsListView =
                findViewById(R.id.complaintsListView);

        databaseHelper = new DatabaseHelper(this);

        complaintsList = new ArrayList<>();
        complaintIds = new ArrayList<>();

        loadComplaints();

        // UPDATE STATUS
        complaintsListView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    int complaintId =
                            complaintIds.get(position);

                    showUpdateDialog(complaintId);
                }
        );

        // DELETE
        complaintsListView.setOnItemLongClickListener(
                (parent, view, position, id) -> {

                    int complaintId =
                            complaintIds.get(position);

                    showDeleteDialog(complaintId);

                    return true;
                }
        );
    }

    private void loadComplaints() {

        complaintsList.clear();
        complaintIds.clear();

        Cursor cursor = databaseHelper
                .getReadableDatabase()
                .query(
                        DatabaseHelper.TABLE_COMPLAINTS,
                        null,
                        null,
                        null,
                        null,
                        null,
                        DatabaseHelper.COLUMN_ID + " DESC"
                );

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_ID
                        )
                );

                String title = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_TITLE
                        )
                );

                String category = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_CATEGORY
                        )
                );

                String location = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_LOCATION
                        )
                );

                String description = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_DESCRIPTION
                        )
                );

                String priority = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_PRIORITY
                        )
                );

                String date = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_DATE
                        )
                );

                String status = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                                DatabaseHelper.COLUMN_STATUS
                        )
                );

                String complaint =
                        "Problem: " + title +
                                "\nCategory: " + category +
                                "\nLocation: " + location +
                                "\nDescription: " + description +
                                "\nPriority: " + priority +
                                "\nDate: " + date +
                                "\nStatus: " + status;

                complaintsList.add(complaint);
                complaintIds.add(id);

            } while (cursor.moveToNext());
        }

        cursor.close();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                complaintsList
        );

        complaintsListView.setAdapter(adapter);
    }

    private void showUpdateDialog(int complaintId) {

        String[] statuses = {
                "Pending",
                "In Progress",
                "Resolved"
        };

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setTitle("Update Complaint Status");

        builder.setItems(
                statuses,
                (dialog, which) -> {

                    String selectedStatus =
                            statuses[which];

                    boolean updated =
                            databaseHelper.updateStatus(
                                    complaintId,
                                    selectedStatus
                            );

                    if (updated) {

                        Toast.makeText(
                                this,
                                "Status Updated",
                                Toast.LENGTH_SHORT
                        ).show();

                        loadComplaints();

                    } else {

                        Toast.makeText(
                                this,
                                "Update Failed",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        builder.show();
    }

    private void showDeleteDialog(int complaintId) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this);

        builder.setTitle("Delete Complaint");

        builder.setMessage(
                "Are you sure you want to delete this complaint?"
        );

        builder.setPositiveButton(
                "YES",
                (dialog, which) -> {

                    boolean deleted =
                            databaseHelper.deleteComplaint(
                                    complaintId
                            );

                    if (deleted) {

                        Toast.makeText(
                                this,
                                "Complaint Deleted",
                                Toast.LENGTH_SHORT
                        ).show();

                        loadComplaints();

                    } else {

                        Toast.makeText(
                                this,
                                "Delete Failed",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        builder.setNegativeButton(
                "NO",
                null
        );

        builder.show();
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (databaseHelper != null) {
            loadComplaints();
        }
    }
}