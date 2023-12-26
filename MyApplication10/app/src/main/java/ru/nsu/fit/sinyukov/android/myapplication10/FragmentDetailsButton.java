package ru.nsu.fit.sinyukov.android.myapplication10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetailsButton extends Fragment {

    public static final String ARG_TEXT = "text";

    public static FragmentDetailsButton create(String text) {
        FragmentDetailsButton fragmentDetailsButton = new FragmentDetailsButton();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragmentDetailsButton.setArguments(args);
        return fragmentDetailsButton;
    }

    public FragmentDetailsButton() {
        super(R.layout.fragment_details_button);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Button button = view.findViewById(R.id.button);
        String text = getArguments().getString(ARG_TEXT);
        button.setText(text);
        button.setOnClickListener(v -> getParentFragmentManager().beginTransaction()
                .replace(R.id.container_item, MainActivity.FragmentDetailsText.create(text))
                .addToBackStack("BLA")
                .commit());
        return view;
    }
}
