package com.Rai.studycenter.adapters;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.Rai.studycenter.gridSelect.fragments.Fyscit;
import com.Rai.studycenter.gridSelect.fragments.Sybscit;
import com.Rai.studycenter.gridSelect.fragments.Tybscit;

public class Study_material_Adpater extends FragmentPagerAdapter  {
    private String[] titles= new String[]{"First Year", "Second Year","Third Year"};

    public Study_material_Adpater(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return  titles.length ;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fyscit();
            case 1:
                return new Sybscit();
            case 2:
                return new Tybscit();
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }

}