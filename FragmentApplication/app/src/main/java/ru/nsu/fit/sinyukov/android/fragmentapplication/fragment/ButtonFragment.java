package ru.nsu.fit.sinyukov.android.fragmentapplication.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.FragmentsViewModel;
import ru.nsu.fit.sinyukov.android.fragmentapplication.R;

public class ButtonFragment extends Fragment {

    private static String ARG_TEXT = "text";
    public static String BUTTON_TO_TEXT = "Button to text";
    public static String ADD_BUTTON = "Add button";

    private FragmentsViewModel visibilityViewModel;

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

        visibilityViewModel = new ViewModelProvider(requireActivity()).get(FragmentsViewModel.class);

        button.setOnClickListener(v -> {
            boolean inLandscape = Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation;
            getParentFragmentManager().beginTransaction()
                    .replace(inLandscape ? R.id.container2 : R.id.container1, TextFragment.create(text))
                    .addToBackStack(BUTTON_TO_TEXT)
                    .commit();
            visibilityViewModel.setFragmentType(FragmentsViewModel.FragmentType.TEXT);
        });
        return view;
    }
}
