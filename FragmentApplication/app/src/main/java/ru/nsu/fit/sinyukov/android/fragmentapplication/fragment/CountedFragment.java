package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.FragmentsViewModel;

public abstract class CountedFragment extends Fragment {

    private FragmentsViewModel fragmentsViewModel;

    public CountedFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentsViewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentsViewModel.incrementCounter();
    }

    @Override
    public void onPause() {
        super.onPause();
        fragmentsViewModel.decrementCounter();
    }
}
