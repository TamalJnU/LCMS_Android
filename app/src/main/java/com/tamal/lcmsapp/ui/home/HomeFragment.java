package com.tamal.lcmsapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.tamal.lcmsapp.CameraActivity;
import com.tamal.lcmsapp.R;
import com.tamal.lcmsapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    CardView attendanceCard;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        attendanceCard = view.findViewById(R.id.cardAttendance);

        attendanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CameraActivity.class);
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