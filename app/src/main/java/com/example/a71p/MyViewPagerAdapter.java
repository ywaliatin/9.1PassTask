package com.example.a71p;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new AddItemFragment();
            case 1:
                return new ShowItemFragment();
            case 2:
                return new MapsFragment();
        }
        // This line will never be reached, as all possible positions have been handled above
        throw new IllegalArgumentException("Invalid position: " + position);
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}

