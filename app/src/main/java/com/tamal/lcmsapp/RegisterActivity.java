package com.tamal.lcmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tamal.lcmsapp.entity.User;

import java.text.DateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText userIdUser;
    EditText nameUser;
    RadioGroup genderUser;
    EditText emailUser;
    EditText addressUser;
    Spinner roleUser;
    EditText startingDateUser;
    Button btnCreateUser;
    DatePickerDialog datePicker;
    TextView listOfUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userIdUser = findViewById(R.id.userId);
        nameUser = findViewById(R.id.userName);
        genderUser = findViewById(R.id.userGender);
        emailUser = findViewById(R.id.userEmail);
        addressUser = findViewById(R.id.userAddress);
        roleUser = findViewById(R.id.spinnerUserRole);
        startingDateUser = findViewById(R.id.userStartDate);
        listOfUser = findViewById(R.id.tvListOfUser);

        btnCreateUser = findViewById(R.id.userCreateBtn);

        Database db = new Database(getApplicationContext(), "androiddb", null, 1);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String userId = userIdUser.getText().toString();
                String name = nameUser.getText().toString();

                String radioText = "";
                int selectedId = genderUser.getCheckedRadioButtonId();
                if(selectedId==-1){
                    Toast.makeText(RegisterActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    //                suETGender = findViewById(selectedId);

                    RadioButton genderButton = (RadioButton) genderUser.findViewById(selectedId);
                    radioText = genderButton.getText().toString();
                }

                String email = emailUser.getText().toString();
                String address = addressUser.getText().toString();
                String role = roleUser.getSelectedItem().toString();
                String startDate = startingDateUser.getText().toString();

                Toast.makeText(getApplicationContext(), "Name: " + name + "UserId: " + userId + " Gender: " + radioText +
                        " Email: " + email + " Address: " + address +
                        " Role: " + role + " Start Date:" + startDate, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

                user.setUserId(userId);
                user.setName(name);
                user.setGender(radioText);
                user.setEmail(email);
                user.setAddress(address);
                user.setRole(role);
                user.setStartDate(startDate);

                db.addNewUser(user);
            }
        });

        startingDateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                //String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
                // date picker dialog
                datePicker = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startingDateUser.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePicker.show();
            }
        });

        listOfUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
                startActivity(intent);
            }
        });


    }
}