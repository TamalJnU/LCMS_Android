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

public class LeaveListActivity extends AppCompatActivity {

    ArrayList leaveList;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_list);

        Database db = new Database(getApplicationContext(), "androiddb", null, 1);
        leaveList = new ArrayList<>();
        leaveList = db.getLeaveList();

        System.out.println(leaveList);

        sa = new SimpleAdapter(this,
                leaveList,
                R.layout.leave_xml,
                new String[]{"userId", "name", "apply_date", "role"},
                new int[]{R.id.layoutListId, R.id.layoutListName, R.id.layoutListApplyDate, R.id.layoutListStatus}
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                ImageView editBtn = v.findViewById(R.id.layoutListEdit);
                ImageView delBtn = v.findViewById(R.id.layoutListDelete);


                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> user = new HashMap<>();

                        try {
//                            System.out.println(v.findViewById(R.id.line_c).toString());

                            user = (HashMap<String, String>) leaveList.get(position);


                            System.out.println(leaveList.get(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(EmpListActivity.this, "Edit button clicked!! + " + position + user, Toast.LENGTH_SHORT).show();
                        System.out.println("EDIT----");
                        Intent intent = new Intent(getApplicationContext(), EditLeaveActivity.class);
                        intent.putExtra("id", user.get("id"));
                        intent.putExtra("userId", user.get("userId"));
                        intent.putExtra("name", user.get("name"));
                        intent.putExtra("email", user.get("email"));
                        intent.putExtra("role", user.get("role"));
                        intent.putExtra("apply_date", user.get("apply_date"));
                        intent.putExtra("leave_from", user.get("leave_from"));
                        intent.putExtra("leave_to", user.get("leave_to"));
                        intent.putExtra("description", user.get("description"));
                        startActivity(intent);
                    }
                });

                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String, String> user = new HashMap<>();

                        user = (HashMap<String, String>) leaveList.get(position);

                        boolean deleted = db.deleteLeave(Integer.parseInt(Objects.requireNonNull(user.get("id"))));
                        if (deleted) {
                            leaveList.remove(position);
                            notifyDataSetChanged();
                        }
                        String message = deleted ? "Successfully deleted" : "Failed to delete";
                        Toast.makeText(LeaveListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
                return v;
            }
        };

        ListView lv = findViewById(R.id.listViewUD);
        lv.setAdapter(sa);

    }
}