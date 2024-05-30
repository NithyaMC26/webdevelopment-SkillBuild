package com.example.a3tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int chance = 0;
    private Button[] buttons = new Button[9];
    private Button reset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
            buttons[i].setOnClickListener(this);
        }

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().toString().equals("")) {
            if (chance == 0) {
                clickedButton.setText("O");
                chance = 1;
            } else {
                clickedButton.setText("X");
                chance = 0;
            }
            if (checkForWin()) {
                resetGame();
            }
        }
    }

    private boolean checkForWin() {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winningCombinations) {
            String a = buttons[combination[0]].getText().toString();
            String b = buttons[combination[1]].getText().toString();
            String c = buttons[combination[2]].getText().toString();

            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                Toast.makeText(this, "Player " + a + " wins", Toast.LENGTH_SHORT).show();
                return true;
            }
        }

        // Check for draw
        boolean draw = true;
        for (Button button : buttons) {
            if (button.getText().toString().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            Toast.makeText(this, "It's a draw", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }


    private void resetGame() {
        for (Button button : buttons) {
            button.setText("");
        }
        chance = 0;
    }
}
