package com.anim.recyclerviewanimation.ui.home;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anim.recyclerviewanimation.R;

public class HomeSecondFragment extends Fragment {

    private HomeSecondViewModel mViewModel;

    public static HomeSecondFragment newInstance() {
        return new HomeSecondFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("getArguments "+getArguments());
        return inflater.inflate(R.layout.home_second_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeSecondViewModel.class);
        // TODO: Use the ViewModel
    }

}
