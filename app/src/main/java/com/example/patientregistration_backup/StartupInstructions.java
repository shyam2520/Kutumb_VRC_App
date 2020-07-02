package com.example.patientregistration_backup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.patientregistration_backup.adapter.OnboardingViewPagerAdapter;
import com.example.patientregistration_backup.model.OnBoardingData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class StartupInstructions extends AppCompatActivity {

        OnboardingViewPagerAdapter onboardingViewPagerAdapter;
        TabLayout tabLayout;
        ViewPager onBoardingViewPager;
        TextView nextButton;
        int postion;
        SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //getSupportActionBar().hide();

            if (restorePrefData()){
                Intent i = new Intent(StartupInstructions.this,navigator.class);
                startActivity(i);
            }


            setContentView(R.layout.activity_startup_main);

            nextButton = findViewById(R.id.next);

            tabLayout = findViewById(R.id.tabIndia);



            final List<OnBoardingData> onBoardingDataList = new ArrayList<>();
            onBoardingDataList.add(new OnBoardingData("Doctors On Demand","We have doctors who are always willing to help you",R.drawable.b));
            onBoardingDataList.add(new OnBoardingData("Meet your nearest doctor","Meet your nearest doctor through our app",R.drawable.a));
            onBoardingDataList.add(new OnBoardingData("Professional Doctors","Professional experts working arounf the corner",R.drawable.c));

            setOnboardingViewPagerAdapter(onBoardingDataList);

            postion = onBoardingViewPager.getCurrentItem();


            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (postion < onBoardingDataList.size()){

                        postion++;
                        onBoardingViewPager.setCurrentItem(postion);

                    }

                    if (postion == onBoardingDataList.size()){
                        savePrefData();
                        Intent i = new Intent(StartupInstructions.this,navigator.class);
                        startActivity(i);
                        finish();
                    }

                }
            });

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    postion = tab.getPosition();
                    if (tab.getPosition() == onBoardingDataList.size() - 1){

                        nextButton.setText("Get Started");

                    }else {
                        nextButton.setText("Next");
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }

        private void setOnboardingViewPagerAdapter(List<OnBoardingData> onBoardingDataList){

            onBoardingViewPager = findViewById(R.id.screenPager);

            onboardingViewPagerAdapter = new OnboardingViewPagerAdapter(this,onBoardingDataList);
            onBoardingViewPager.setAdapter(onboardingViewPagerAdapter);
            tabLayout.setupWithViewPager(onBoardingViewPager);

        }

        private void savePrefData(){

            sharedPreferences = getApplicationContext().getSharedPreferences("onboardingpref",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstTimeLaunch",true);
            editor.apply();

        }

        private Boolean restorePrefData(){

            sharedPreferences = getApplicationContext().getSharedPreferences("onboardingpref",MODE_PRIVATE);
            return sharedPreferences.getBoolean("isFirstTimeLaunch",false);

        }



}