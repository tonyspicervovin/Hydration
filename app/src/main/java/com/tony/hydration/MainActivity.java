package com.tony.hydration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MAIN_ACTIVITY";

    private WaterViewModel waterViewModel;

    private static final String [] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"
    , "Saturday", "Sunday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create viewmodel associate with this activity

        waterViewModel = new WaterViewModel(getApplication());

        //debugging
        waterViewModel.getAllRecords().observe(this, new Observer<List<WaterRecord>>() {
            @Override
            public void onChanged(List<WaterRecord> waterRecords) {
                Log.d(TAG, "Water records are: "+ waterRecords);
            }
        });

        for (String day: DAYS) {
            WaterRecord record = new WaterRecord(day, 0);
            Log.d(TAG, "Inserting "+record);
            waterViewModel.insert(record);
        }

        //create viewpager using the days array to create a page for each day in the days array

        ViewPager viewPager = findViewById(R.id.water_view_pager);
        WaterViewPagerAdapter waterViewPagerAdapter = new WaterViewPagerAdapter(getSupportFragmentManager(), DAYS);
        viewPager.setAdapter(waterViewPagerAdapter);
    }
}
