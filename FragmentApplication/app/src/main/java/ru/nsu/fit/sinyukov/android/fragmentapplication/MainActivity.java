package ru.nsu.fit.sinyukov.android.fragmentapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.ButtonFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.MenuFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.MenuFragmentActionListener;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.TextFragment;

public class MainActivity extends AppCompatActivity implements MenuFragmentActionListener {

    private static final String INITIAL_TEXT = "Initial text";

    private FragmentsViewModel fragmentsViewModel;
    private boolean inLandscape;

    @BuildCompat.PrereleaseSdkCheck
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentsViewModel = new ViewModelProvider(this).get(FragmentsViewModel.class);

        inLandscape = Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation;

        if (inLandscape || null == savedInstanceState) {
            createMenu();
        }
        if (null != savedInstanceState) {
            showFragmentFromViewModel();
        }
        if (inLandscape && null == savedInstanceState) {
            showButtonFragment(INITIAL_TEXT);
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

    private void createMenu() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container1, MenuFragment.create(this))
                .commit();
    }

    private void backPressed() {
        if (0 != getSupportFragmentManager().getBackStackEntryCount()) {
            popBackStack(null);
        } else {
            finish();
        }
    }

    // could delete this and use backPressed() instead
    @Override
    public void backPressed(String transactionName) {
        popBackStack(transactionName);
    }

    private void popBackStack(String transactionName) {
        fragmentsViewModel.setFragmentType(FragmentsViewModel.FragmentType.BUTTON);
        getSupportFragmentManager().popBackStack(
                transactionName, null == transactionName ? 0 : FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void showFragmentFromViewModel() {
        showFragment(fragmentsViewModel.getFragmentType().getValue(), fragmentsViewModel.getText().getValue());
    }

    private void showFragment(FragmentsViewModel.FragmentType fragmentType, String text) {
        boolean isButton = FragmentsViewModel.FragmentType.BUTTON == fragmentType;

        fragmentsViewModel.setFragmentType(fragmentType);
        fragmentsViewModel.setText(text);
        popBackStack(isButton ? ButtonFragment.ADD_BUTTON : ButtonFragment.BUTTON_TO_TEXT);

        getSupportFragmentManager().beginTransaction()
                .replace(inLandscape ? R.id.container2 : R.id.container1,
                        isButton ? ButtonFragment.create(text) : TextFragment.create(text))
                .addToBackStack(ButtonFragment.ADD_BUTTON)
                .commit();
    }

    @Override
    public void showButtonFragment(String text) {
        showFragment(FragmentsViewModel.FragmentType.BUTTON, text);
    }
}