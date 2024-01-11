package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.nsu.fit.sinyukov.android.fragmentapplication.R;

public class TextFragment extends CountedFragment {

    private static String ARG_TEXT = "text";

    public static TextFragment create(String text) {
        final TextFragment textFragment = new TextFragment();
        final Bundle textBundle = new Bundle();
        textBundle.putString(ARG_TEXT, text);
        textFragment.setArguments(textBundle);
        return textFragment;
    }

    public TextFragment() {
        super(R.layout.fragment_text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        final TextView textView = view.findViewById(R.id.textView);
        textView.setText(getArguments().getString(ARG_TEXT));
        return view;
    }
}
