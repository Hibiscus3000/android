package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.nsu.fit.sinyukov.android.fragmentapplication.MainActivity;
import ru.nsu.fit.sinyukov.android.fragmentapplication.R;

public class MenuFragment extends Fragment {

    public MenuFragment() {
        super(R.layout.fragment_menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        setButtonOnClickListener(view.findViewById(R.id.menuButton1), "111");
        setButtonOnClickListener(view.findViewById(R.id.menuButton2), "222");
        setButtonOnClickListener(view.findViewById(R.id.menuButton3), "333");
        return view;
    }

    private void setButtonOnClickListener(Button button, String text) {
        button.setOnClickListener(view -> {
            ((MainActivity) getActivity()).showButtonFragment(text);
        });
    }
}
