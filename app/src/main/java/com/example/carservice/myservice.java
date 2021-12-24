package com.example.carservice;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.carservice.databinding.ActivityMyservice2Binding;
import com.google.android.material.tabs.TabLayout;

public class myservice extends AppCompatActivity {

    private ActivityMyservice2Binding binding;

    ViewPager2 viewPager2;
    TabLayout tabLayout;

    FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myservice2);

       /* binding = ActivityMyservice2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        //tabs.getTabAt(0).setIcon(R.drawable.car);*/

            viewPager2=findViewById(R.id.view_pager);
            tabLayout=findViewById(R.id.tabs);
            FragmentManager fragmentManager=getSupportFragmentManager();
            adapter=new FragmentAdapter(fragmentManager, getLifecycle());
            viewPager2.setAdapter(adapter);

            tabLayout.addTab(tabLayout.newTab().setText("Select Car"));
            tabLayout.addTab(tabLayout.newTab().setText("Operations"));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager2.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    tabLayout.selectTab(tabLayout.getTabAt(position));
                }
            });

    }
}