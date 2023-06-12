package com.tamal.lcmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tamal.lcmsapp.entity.Leave;

import java.util.Calendar;

public class EditLeaveActivity extends AppCompatActivity {


    private EditText leaveId;
    private EditText userID;
    private EditText userName;
    private EditText userEmail;
    private Spinner userDepartment;
    private EditText leaveApplyDate;
    private EditText leaveDescription;
    Button btnSubmit;
    private TextView editTextDateFrom;
    private TextView editTextDateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_leave);

        leaveId = findViewById(R.id.editId);
        userID = findViewById(R.id.editUserId);
        userName = findViewById(R.id.editUserName);
        userEmail = findViewById(R.id.editUserEmail);
        userDepartment = findViewById(R.id.editSpinnerRole);
        leaveApplyDate = findViewById(R.id.editApplyDate);
        editTextDateFrom = findViewById(R.id.editDateFrom);
        editTextDateTo = findViewById(R.id.editDateTo);
        leaveDescription = findViewById(R.id.editDescription);
        btnSubmit = findViewById(R.id.buttonEditSubmit);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        leaveApplyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(EditLeaveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth +"/" + month + "/" + year;
                        leaveApplyDate.setText(date);
                    }
                },year, month, day);
                dialog.show();
            }
        });

        editTextDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(EditLeaveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth +"/" + month + "/" + year;
                        editTextDateFrom.setText(date);
                    }
                },year, month, day);
                dialog.show();
            }
        });

        editTextDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(EditLeaveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth +"/" + month + "/" + year;
                        editTextDateTo.setText(date);
                    }
                },year, month, day);
                dialog.show();
            }
        });

        leaveId.setKeyListener(null);

        Intent intent = getIntent();
        String leave_id = intent.getStringExtra("id");
        String user_id = intent.getStringExtra("userId");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_department = intent.getStringExtra("role");
        String apply_date = intent.getStringExtra("apply_date");
        String leave_from = intent.getStringExtra("leave_from");
        String leave_to = intent.getStringExtra("leave_to");
        String leave_description = intent.getStringExtra("description");

        leaveId.setText(leave_id);
        userID.setText(user_id);
        userName.setText(user_name);
        userEmail.setText(user_email);
//        empDepartment.setAdapter();
        leaveApplyDate.setText(apply_date);
        editTextDateFrom.setText(leave_from);
        editTextDateTo.setText(leave_to);
        leaveDescription.setText(leave_description);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        userDepartment.setAdapter(adapter);
        Integer pos;
        if(user_department.contains("Police")) {
            pos = adapter.getPosition("Police");
            userDepartment.setSelection(pos);
        } else if (user_department.contains("Court")) {
            pos = adapter.getPosition("Court");
            userDepartment.setSelection(pos);
        } else if (user_department.contains("Judge")) {
            pos = adapter.getPosition("Judge");
            userDepartment.setSelection(pos);
        } else if (user_department.contains("PP")) {
            pos = adapter.getPosition("PP");
            userDepartment.setSelection(pos);
        } else if (user_department.contains("Jailer")) {
            pos = adapter.getPosition("Jailer");
            userDepartment.setSelection(pos);
        }else{
            userDepartment.setSelection(0);
        }

        Database db = new Database(getApplicationContext(), "androiddb", null,1);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.nav_gallery);

                Leave leave = new Leave();
                Integer lid = Integer.parseInt(leaveId.getText().toString());
                String id = userID.getText().toString();
                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String department = (String) userDepartment.getSelectedItem();
                String applyDate = leaveApplyDate.getText().toString();
                String from = editTextDateFrom.getText().toString();
                String to = editTextDateTo.getText().toString();
                String description = leaveDescription.getText().toString();

                leave.setId(lid);
                leave.setUserId(id);
                leave.setName(name);
                leave.setEmail(email);
                leave.setRole(department);
                leave.setApply_date(applyDate);
                leave.setLeave_from(from);
                leave.setLeave_to(to);
                leave.setDescription(description);

                Boolean result = db.updateLeave(leave);
                String message = result ? "Successfully Updated!" : "Failed to Update.";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();//
                Intent intent = new Intent(getApplicationContext(), LeaveListActivity.class);
                startActivity(intent);
            }
        });
    }
}