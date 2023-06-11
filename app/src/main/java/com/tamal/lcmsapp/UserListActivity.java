package com.tamal.lcmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UserListActivity extends AppCompatActivity {

    ArrayList userList;
    Button btnCreateUser;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Database db = new Database(getApplicationContext(), "user", null, 1);
        userList = new ArrayList<>();
        userList = db.getAllUsers();

        btnCreateUser = findViewById(R.id.btnEmpListCreateUser);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        sa = new SimpleAdapter(this, userList, R.layout.user_list,
                new String[]{"id", "userId", "name", "email", "address", "role"},
                new int[]{R.id.layoutUserListId, R.id.layoutUserListUserId, R.id.layoutUserListName, R.id.layoutUserListEmail,
                        R.id.layoutUserListAddress, R.id.layoutUserListRole}
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ImageView editImg = v.findViewById(R.id.layoutUserListEdit);
                ImageView deleteImg = v.findViewById(R.id.layoutUserListDelete);

                editImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println(position);
                        HashMap<String, String> user = new HashMap<>();
                        try {
                            user = (HashMap<String, String>) userList.get(position);
                            System.out.println(userList.get(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
                        intent.putExtra("id", user.get("id"));
                        intent.putExtra("userId", user.get("userId"));
                        intent.putExtra("name", user.get("name"));
                        intent.putExtra("gender", user.get("gender"));
                        intent.putExtra("email", user.get("email"));
                        intent.putExtra("address", user.get("address"));
                        intent.putExtra("role", user.get("role"));
                        intent.putExtra("startdate", user.get("startdate"));
                        startActivity(intent);
                    }
                });

                deleteImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> user = new HashMap<>();
                        user = (HashMap<String, String>) userList.get(position);
                        boolean deleted = db.deleteUser(Integer.parseInt(Objects.requireNonNull(user.get("id"))));
                        if(deleted) {
                            userList.remove(position);
                            notifyDataSetChanged();
                        }
                        String message = deleted ? "Successfully Deleted!!" : "Deletation Failed!!";
                        Toast.makeText(UserListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
                return v;
            }
        };

        ListView lv = findViewById(R.id.lvUserList);
        lv.setAdapter(sa);


    }
}