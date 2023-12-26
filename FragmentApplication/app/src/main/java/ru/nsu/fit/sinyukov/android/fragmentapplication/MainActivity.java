package ru.nsu.fit.sinyukov.android.fragmentapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.ButtonFragment;
import ru.nsu.fit.sinyukov.android.fragmentapplication.fragment.MenuFragment;

public class MainActivity extends AppCompatActivity {

    private String initialText = "AAAAAA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerMenu, new MenuFragment())
                    .commit();
            showButtonFragment(initialText);
        }
    }

    public void showButtonFragment(String text) {
        getSupportFragmentManager().popBackStack(
                ButtonFragment.TRANSACTION_NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerItem, ButtonFragment.create(text))
                .commit();
    }
}