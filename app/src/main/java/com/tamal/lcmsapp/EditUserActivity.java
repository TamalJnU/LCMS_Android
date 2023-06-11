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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tamal.lcmsapp.entity.User;

import java.util.Calendar;

public class EditUserActivity extends AppCompatActivity {

    EditText idUpdateUser;
    EditText userIdUpdateUser;
    EditText nameUpdateUser;
    RadioGroup genderUpdateUser;
    EditText emailUpdateUser;
    EditText addressUpdateUser;
    Spinner roleUpdateUser;
    EditText startingDateUpdateUser;

    Button btnUpdateUser;
    TextView updateCancel;
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        idUpdateUser = findViewById(R.id.userUpdateId);
        userIdUpdateUser = findViewById(R.id.userUpdateUserId);
        nameUpdateUser = findViewById(R.id.userUpdateName);

//        String radioText = "";
//        int selectedId = genderUpdateEmp.getCheckedRadioButtonId();
//        if(selectedId==-1){
//            Toast.makeText(EditEmployee.this,"Nothing selected", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            //                suETGender = findViewById(selectedId);
//
//            RadioButton genderButton = (RadioButton) genderUpdateEmp.findViewById(selectedId);
//            radioText = genderButton.getText().toString();
//        }

        genderUpdateUser = findViewById(R.id.userUpdateGender);
        emailUpdateUser = findViewById(R.id.userUpdateEmail);
        addressUpdateUser = findViewById(R.id.userUpdateAddress);
        roleUpdateUser = findViewById(R.id.spinnerUpdateUserRole);
        startingDateUpdateUser = findViewById(R.id.userUpdateStartDate);

        btnUpdateUser= findViewById(R.id.userUpdateBtn);
        updateCancel = findViewById(R.id.tvCancel);

        idUpdateUser.setKeyListener(null);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String userId = intent.getStringExtra("userId");
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String email = intent.getStringExtra("email");
        String address = intent.getStringExtra("address");
        String role = intent.getStringExtra("role");
        String startdate = intent.getStringExtra("startdate");

        System.out.println(gender);
        idUpdateUser.setText(id);
        userIdUpdateUser.setText(userId);
        nameUpdateUser.setText(name);
        if(gender.contains("Male")){
            genderUpdateUser.check(R.id.userUpdateMale);
        }else{
            genderUpdateUser.check(R.id.userUpdateFemale);
        }
        emailUpdateUser.setText(email);
        addressUpdateUser.setText(address);
//        Integer indexValue = MySpinner.getSelectedItemPosition();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        roleUpdateUser.setAdapter(adapter);

        Integer pos;
        if(role.contains("Police")) {
            pos = adapter.getPosition("Police");
            roleUpdateUser.setSelection(pos);
        } else if (role.contains("Judge")) {
            pos = adapter.getPosition("Judge");
            roleUpdateUser.setSelection(pos);
        } else if (role.contains("PP")) {
            pos = adapter.getPosition("PP");
            roleUpdateUser.setSelection(pos);
        } else if (role.contains("Jailer")) {
            pos = adapter.getPosition("Jailer");
            roleUpdateUser.setSelection(pos);
        }else{
            roleUpdateUser.setSelection(0);
        }

        System.out.println("Date----------------startdate--"+startdate);

        if(startdate != null) {
            System.out.println("Date----------------startdate--"+startdate);
            startingDateUpdateUser.setText(startdate);
        }

        Toast.makeText(this, "Name: " +name + " Gender: " + gender  + " Email: " + email
                + " Address: " + address + " Role: " + role
                + " Start Date: " + startdate, Toast.LENGTH_SHORT).show();
        Database db = new Database(getApplicationContext(), "employee", null,1);

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                Integer id = Integer.parseInt(idUpdateUser.getText().toString());
                String userId = userIdUpdateUser.getText().toString();
                String name = nameUpdateUser.getText().toString();
                String gender = String.valueOf(genderUpdateUser.getCheckedRadioButtonId());
                String email = emailUpdateUser.getText().toString();
                String address = addressUpdateUser.getText().toString();
                String role = (String) roleUpdateUser.getSelectedItem();
                String startdate = startingDateUpdateUser.getText().toString();

                user.setId(id);
                user.setUserId(userId);
                user.setName(name);
                user.setGender(gender);
                user.setEmail(email);
                user.setAddress(address);
                user.setRole(role);
                user.setStartDate(startdate);

                Boolean result = db.updateUser(user);
                String message = result ? "Successfully Updated!" : "Updatation Failed!!";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();//
                Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                startActivity(intent);
            }
        });

        updateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                startActivity(intent);
            }
        });

        startingDateUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePicker = new DatePickerDialog(EditUserActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startingDateUpdateUser.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePicker.show();
            }
        });

    }
}