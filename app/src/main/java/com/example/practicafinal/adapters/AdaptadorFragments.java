package com.example.practicafinal.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.practicafinal.fragmenst.FragmentCarrito;
import com.example.practicafinal.fragmenst.FragmentJuegos;
import com.example.practicafinal.fragmenst.FragmentOfertas;
import com.example.practicafinal.fragmenst.FragmentWelcome;

import java.util.ArrayList;

public class AdaptadorFragments extends FragmentPagerAdapter {
    private ArrayList<Fragment> listaFragments;

    public AdaptadorFragments (@NonNull FragmentManager fm) {
        super(fm);
        listaFragments = new ArrayList<>();
        listaFragments.add(new FragmentWelcome());
        listaFragments.add(new FragmentJuegos());
        listaFragments.add(new FragmentOfertas());
        listaFragments.add(new FragmentCarrito());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }
}
