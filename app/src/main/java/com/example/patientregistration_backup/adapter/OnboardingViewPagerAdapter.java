package com.example.patientregistration_backup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patientregistration_backup.R;
import com.example.patientregistration_backup.model.OnBoardingData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class OnboardingViewPagerAdapter extends PagerAdapter {

    Context context;
    List<OnBoardingData>onBoardingDataList ;

    public OnboardingViewPagerAdapter(Context context, List<OnBoardingData> onBoardingDataList) {
        this.context = context;
        this.onBoardingDataList = onBoardingDataList;
    }

    @Override
    public int getCount() {
        return onBoardingDataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout,null);

        ImageView imageView;
        TextView title,desc;

        imageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(onBoardingDataList.get(position).getImageUr());

        title.setText(onBoardingDataList.get(position).getTitle());
        desc.setText(onBoardingDataList.get(position).getDesc());

        container.addView(view);


        return view;


    }
}
