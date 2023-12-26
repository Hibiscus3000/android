package ru.nsu.fit.sinyukov.android.fragmentapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.BuildCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.ButtonFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity {

    private static final String INITIAL_TEXT = "AAAAAA";

    private BackButtonVisibilityViewModel backButtonVisibilityViewModel;

    @BuildCompat.PrereleaseSdkCheck
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backButtonVisibilityViewModel = new ViewModelProvider(this).get(BackButtonVisibilityViewModel.class);

        if (null == savedInstanceState) {
            final boolean inLandscape = Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation;
            getSupportFragmentManager().beginTransaction()
                    .add(inLandscape ? R.id.containerMenu : R.id.containerItem, new MenuFragment())
                    .commit();
            if (inLandscape) {
                showButtonFragment(INITIAL_TEXT);
            }
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

    private void backPressed() {
        if (0 != getSupportFragmentManager().getBackStackEntryCount()) {
            popBackStack(null);
        } else {
            finish();
        }
    }

    public void popBackStack(String transactionName) {
        backButtonVisibilityViewModel.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().popBackStack(
                transactionName, null == transactionName ? 0 : FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void showButtonFragment(String text) {
        popBackStack(ButtonFragment.ADD_BUTTON);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerItem, ButtonFragment.create(text))
                .addToBackStack(ButtonFragment.ADD_BUTTON)
                .commit();
    }
}