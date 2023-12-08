package ru.nsu.fit.sinyukov.android.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        TextView helloTextView = findViewById(R.id.helloTextView);
        EditText nameEditText = findViewById(R.id.nameEditText);
        String name = nameEditText.getText().toString();
        if (name.isEmpty()) {
            name = "Stranger";
        }
        helloTextView.setText("Hello, " + name + "!!!");
    }
}