package ru.nsu.fit.sinyukov.android.registrationform;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        setText("Name", R.id.nameEditText, R.id.nameTextView);
        setText("Surname", R.id.surnameEditText, R.id.surnameTextView);
        setText("Phone", R.id.phoneEditText, R.id.phoneTextView);
        setText("Email", R.id.emailEditText, R.id.emailTextView);
    }

    private void setText(String prefix, @IdRes Integer editTextId, @IdRes Integer textViewId) {
        final TextView textView = findViewById(textViewId);
        final EditText editText = findViewById(editTextId);

        final String text = editText.getText().toString();
        if (!text.isEmpty()) {
            textView.setText(prefix + ": " + text);
        } else {
            textView.setText(prefix);
        }
    }
}