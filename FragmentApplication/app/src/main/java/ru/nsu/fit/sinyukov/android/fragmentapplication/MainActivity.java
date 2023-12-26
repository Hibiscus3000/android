package ru.nsu.fit.sinyukov.android.fragmentapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.ButtonFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity {

    private String initialText = "AAAAAA";

    private BackButtonVisibilityViewModel backButtonVisibilityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backButtonVisibilityViewModel = new ViewModelProvider(this).get(BackButtonVisibilityViewModel.class);

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerMenu, new MenuFragment())
                    .commit();
            showButtonFragment(initialText);
        }
    }

    public void popBackStack() {
        backButtonVisibilityViewModel.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().popBackStack(
                ButtonFragment.TRANSACTION_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void showButtonFragment(String text) {
        popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerItem, ButtonFragment.create(text))
                .commit();
    }
}