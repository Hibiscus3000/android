package ru.nsu.fit.sinyukov.android.fragmentapplication;

import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.ButtonFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.FragmentDescription;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.FragmentType;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.MenuFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.TextFragment;

public class MainActivity extends AppCompatActivity {

    public static final String ADD_TEXT = "Add text";
    public static final String ADD_BUTTON = "Add button";
    private static final String INITIAL_TEXT = "Initial text";

    private FragmentsViewModel fragmentsViewModel;
    private boolean inLandscape;

    @BuildCompat.PrereleaseSdkCheck
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentsViewModel = new ViewModelProvider(this).get(FragmentsViewModel.class);
        setFragmentObservers();

        inLandscape = Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation;

        if (null == savedInstanceState) {
            createMenu();
        } else {
            restoreBackStack();
        }
        if (inLandscape && null == savedInstanceState) {
            addFragmentToViewModel(FragmentType.BUTTON, INITIAL_TEXT);
        }

        if (BuildCompat.isAtLeastT()) {
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                    OnBackInvokedDispatcher.PRIORITY_DEFAULT, this::backPressed);
        } else {
            getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    backPressed();
                }
            });
        }
    }

    private void setFragmentObservers() {
        fragmentsViewModel.getBackPressed().observe(this, backPressed -> {
            if (backPressed) {
                backPressed();
                fragmentsViewModel.setBackPressed(false);
            }
        });
        fragmentsViewModel.getFragmentDescription().observe(this, fragmentDescription -> {
            if (null != fragmentDescription && fragmentDescription.getChangeView()) {
                addFragmentFromViewModel();
            }
        });
        fragmentsViewModel.getFragmentCounter().observe(this, fragmentCounter ->
                ((TextView) findViewById(R.id.fragmentCounterTextView)).setText("Number of fragments = " + fragmentCounter));

    }

    private void createMenu() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1, new MenuFragment())
                .commit();
    }

    private void backPressed() {
        if (inLandscape && getSupportFragmentManager().getBackStackEntryCount() <= 1
                || getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
        } else {
            final FragmentDescription fragmentDescription = fragmentsViewModel.getFragmentDescription().getValue();
            fragmentsViewModel.setFragmentType(fragmentDescription.getFragmentType().getPrevious(), false);
            getSupportFragmentManager().popBackStack();
        }
    }

    private void restoreBackStack() {
        while (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }

        final FragmentDescription fragmentDescription = fragmentsViewModel.getFragmentDescription().getValue();
        if (null == fragmentDescription || FragmentType.NOTHING == fragmentDescription.getFragmentType()) {
            if (inLandscape) {
                addFragmentToViewModel(FragmentType.BUTTON, INITIAL_TEXT);
            }
            return;
        }
        for (FragmentType fragmentType : FragmentType.getSequence()) {
            addFragment(new FragmentDescription(fragmentType, fragmentDescription.getText(), true));
            if (fragmentType == fragmentDescription.getFragmentType()) {
                break;
            }
        }
    }

    private void addFragmentToViewModel(FragmentType fragmentType, String text) {
        fragmentsViewModel.setFragmentDescription(new FragmentDescription(fragmentType, text, true));
    }

    private void addFragmentFromViewModel() {
        addFragment(fragmentsViewModel.getFragmentDescription().getValue());
    }

    private void addFragment(FragmentDescription fragmentDescription) {
        final FragmentType fragmentType = fragmentDescription.getFragmentType();
        final String text = fragmentDescription.getText();

        final boolean isButton = FragmentType.BUTTON == fragmentType;

        final String transactionName = isButton ? ADD_BUTTON : ADD_TEXT;

        getSupportFragmentManager().popBackStack(transactionName, POP_BACK_STACK_INCLUSIVE);

        getSupportFragmentManager().beginTransaction()
                .replace(inLandscape ? R.id.container2 : R.id.container1,
                        isButton ? ButtonFragment.create(text) : TextFragment.create(text))
                .addToBackStack(transactionName)
                .commit();
    }
}