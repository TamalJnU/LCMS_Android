package com.tamal.lcmsapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tamal.lcmsapp.CameraActivity;
import com.tamal.lcmsapp.LeaveApplicationActivity;
import com.tamal.lcmsapp.MainActivity;
import com.tamal.lcmsapp.R;
import com.tamal.lcmsapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    CardView attendanceCard;
    CardView leaveApplyCard;
    CardView locationCard;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        attendanceCard = view.findViewById(R.id.cardAttendance);
        leaveApplyCard = view.findViewById(R.id.cardLeaveApplication);

        attendanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getView().getContext(), CameraActivity.class);
                startActivity(intent);
            }
        });

        leaveApplyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getView().getContext(), LeaveApplicationActivity.class);
                startActivity(intent);
            }
        });

        locationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getView().getContext(), MainActivity.class);
                startActivity(intent);
            }
        });


//        btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_gallery, null));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}