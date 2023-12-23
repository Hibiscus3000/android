package ru.nsu.fit.sinyukov.android.countapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static String COUNTER_KEY = "COUNTER_KEY";

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (null != savedInstanceState) {
            counter = savedInstanceState.getInt(COUNTER_KEY);
        }

        Button clearEditTextButton = findViewById(R.id.clearEditTextButton);
        clearEditTextButton.setOnClickListener(v -> ((EditText) findViewById(R.id.changeAmountEditText)).setText(""));

        Button changeButton = findViewById(R.id.changeButton);
        Button incrementButton = findViewById(R.id.incrementButton);
        Button decrementButton = findViewById(R.id.decrementButton);
        Button showButton = findViewById(R.id.showButton);

        changeButton.setOnClickListener(v -> changeCounter(Integer
                .parseInt(((EditText) findViewById(R.id.changeAmountEditText)).getText().toString())));
        incrementButton.setOnClickListener(v -> changeCounter(1));
        decrementButton.setOnClickListener(v -> changeCounter(-1));

        showButton.setOnClickListener(v -> Toast.makeText(this, "Counter = " + counter, Toast.LENGTH_LONG).show());
    }

    private void changeCounter(int changeAmount) {
        if ((long) changeAmount + (long) counter >= Integer.MAX_VALUE) {
            counter = Integer.MAX_VALUE;
            return;
        }
        if ((long) changeAmount + (long) counter <= Integer.MIN_VALUE) {
            counter = Integer.MIN_VALUE;
            return;
        }
        counter += changeAmount;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNTER_KEY, counter);
    }
}