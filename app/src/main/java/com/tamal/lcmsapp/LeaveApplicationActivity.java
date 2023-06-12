package com.tamal.lcmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tamal.lcmsapp.entity.Leave;

import java.util.Calendar;

public class LeaveApplicationActivity extends AppCompatActivity {


    private EditText userID;
    private EditText userName;
    private EditText userEmail;
    private Spinner userDepartment;
    private EditText leaveApplyDate;
    private EditText leaveDescription;

    Button btnSubmit;

    //    private TextView textViewSelectDateFrom;
    private EditText editTextDateFrom;

    //    private TextView textViewSelectDateTo;
    private EditText editTextDateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application);

        userID = findViewById(R.id.editTextUserId);
        userName = findViewById(R.id.editTextUserName);
        userEmail = findViewById(R.id.editTextUserEmail);
        userDepartment = findViewById(R.id.spinnerRole);
        leaveApplyDate = findViewById(R.id.editTextApplyDate);

//        textViewSelectDateFrom = findViewById(R.id.textViewSelectDateFrom);
        editTextDateFrom = findViewById(R.id.editTextDateFrom);
//        textViewSelectDateTo = findViewById(R.id.textViewSelectDateTo);
        editTextDateTo = findViewById(R.id.editTextDateTo);
        leaveDescription = findViewById(R.id.editTextDescription);

        btnSubmit = findViewById(R.id.buttonSubmit);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        leaveApplyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(LeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        leaveApplyDate.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

//        textViewSelectDateFrom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dialog = new DatePickerDialog(LeaveForm.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        month = month + 1;
//                        String date = dayOfMonth +"/" + month + "/" + year;
//                        editTextDateFrom.setText(date);
//                    }
//                },year, month, day);
//                dialog.show();
//            }
//        });
//
        editTextDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(LeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        editTextDateFrom.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
//
//        textViewSelectDateFrom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dialog = new DatePickerDialog(LeaveForm.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        month = month+1;
//                        String date = dayOfMonth +"/" + month + "/" + year;
//                        editTextDateFrom.setText(date);
//                    }
//                },year, month, day);
//                dialog.show();
//            }
//        });
//
        editTextDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(LeaveApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        editTextDateTo.setText(date);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leave leave = new Leave();
                String userId = userID.getText().toString();
                int userIdGet = Integer.parseInt(userId);
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String role = userDepartment.getSelectedItem().toString();
                String applyDate = leaveApplyDate.getText().toString();
                String fromDate = editTextDateFrom.getText().toString();
                String toDate = editTextDateTo.getText().toString();
                String description = leaveDescription.getText().toString();

                Database db = new Database(getApplicationContext(), "androiddb", null, 1);

                if (name.length() == 0 || email.length() == 0 || role.length() == 0 || applyDate.length() == 0 || fromDate.length() == 0 || toDate.length() == 0 || description.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill the all data field", Toast.LENGTH_SHORT).show();
                } else {
                    if (true) {
                        db.addLeaveApplication(leave);
                        Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(LeaveForm.this, LeaveList.class));
                    } else {
//                        Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                    }
                }


//                Toast.makeText(getApplicationContext(),"ID" + empIdGet +
//                        "Name" + empNameGet +
//                        "Email" + empEmailGet +
//                        "Department" + empDepartGet +
//                        "Apply" + empApplyDateGet +
//                        "From" + fromDateGet +
//                        "To" + toDateGet +
//                        "Description" + leaveDescrip, Toast.LENGTH_SHORT).show();
            }
        });

    }
}