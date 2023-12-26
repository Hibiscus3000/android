package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.nsu.fit.sinyukov.android.fragmentapplication.R;

public class ButtonFragment extends Fragment {

    private static String ARG_TEXT = "text";
    public static String TRANSACTION_NAME = "B2T";

    public static ButtonFragment create(String text) {
        final ButtonFragment buttonFragment = new ButtonFragment();
        final Bundle textBundle = new Bundle();
        textBundle.putString(ARG_TEXT, text);
        buttonFragment.setArguments(textBundle);
        return buttonFragment;
    }

    public ButtonFragment() {
        super(R.layout.fragment_button);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        final Button button = view.findViewById(R.id.button);
        final String text = getArguments().getString(ARG_TEXT);
        button.setText(text);
        button.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.containerItem, TextFragment.create(text))
                    .addToBackStack(TRANSACTION_NAME)
                    .commit();
        });
        return view;
    }
}
