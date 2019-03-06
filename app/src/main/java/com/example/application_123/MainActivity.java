package com.example.application_123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // blue:0, red:1, empty:2

    int activePlayer = 0;
    boolean gameActive = true;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int winningPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6} };

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0 ) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(400);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    // Someone has won..!

                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "blue"; // as activeplayer had been changed.
                    } else
                        winner = "red";

                    Button button = findViewById(R.id.button);
                    TextView winnerTextview = findViewById(R.id.winnerTextview);
                    winnerTextview.setText(winner + " has won..!");

                    winnerTextview.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);

                }
            }

        }
        else
        {
            if (gameState[tappedCounter] != 2){

                Button button = findViewById(R.id.button);
                TextView winnerTextview = findViewById(R.id.winnerTextview);
                winnerTextview.setText("no one has won..!");

                winnerTextview.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
            }
        }

    }

    public void playAgain(View view){

        Button button = (Button)findViewById(R.id.button);
        TextView winnerTextview = (TextView)findViewById(R.id.winnerTextview);

        winnerTextview.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++){

            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0; i<gameState.length; i++){
            gameState[i]=2;
        }

        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}