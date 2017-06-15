package com.example.administrator.anzhuo_final;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
   // private List<Fragment> fragments;
    //private List<String> titles;
    private String[] mTitles = new String[]{"头条", "体育", "科技","社会"};

    public FragmentAdapter(FragmentManager fm){
        super(fm);
        //this.fragments=fragments;
       // this.titles=titles;
    }



    public  CharSequence getPageTitle(int position)
    {
        return mTitles[position];
    }
    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TabFragment();
        } else if (position == 1) {
            return new TabFragment2();
        }else if (position==2){
            return new TabFragment3();
        }

        return new TabFragment4();
    }


}
