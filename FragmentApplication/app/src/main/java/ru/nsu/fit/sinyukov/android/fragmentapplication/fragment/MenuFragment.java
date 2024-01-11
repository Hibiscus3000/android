package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.FragmentsViewModel;
import ru.nsu.fit.sinyukov.android.fragmentapplication.R;

public class MenuFragment extends CountedFragment {

    private FragmentsViewModel fragmentsViewModel;

    public MenuFragment() {
        super(R.layout.fragment_menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);

        fragmentsViewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);

        setButtonOnClickListener(view.findViewById(R.id.menuButton1), "111");
        setButtonOnClickListener(view.findViewById(R.id.menuButton2), "222");
        setButtonOnClickListener(view.findViewById(R.id.menuButton3), "333");
        view.findViewById(R.id.backButton).setOnClickListener(v -> fragmentsViewModel.setBackPressed(true));

        fragmentsViewModel
                .getFragmentDescription()
                .observe(getViewLifecycleOwner(), fragmentDescription -> getView()
                        .findViewById(R.id.backButton)
                        .setVisibility((null == fragmentDescription || !fragmentDescription.getFragmentType().isBackVisible())
                                || Configuration.ORIENTATION_PORTRAIT == getResources().getConfiguration().orientation
                                ? View.INVISIBLE : View.VISIBLE));

        return view;
    }

    private void setButtonOnClickListener(Button button, String text) {
        button.setOnClickListener(view -> fragmentsViewModel.setFragmentDescription(
                new FragmentDescription(FragmentType.BUTTON, text, true)));
    }
}
