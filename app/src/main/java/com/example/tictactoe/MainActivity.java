package com.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0; //0-> cross 1->circle
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean isGameActive = true;




    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && isGameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;

            }


            counter.setTranslationY(-1500);
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    isGameActive = false;
                    if (gameState[winningPosition[0]] == 0) {
                        Toast.makeText(this, "Circle have won", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Cross have won", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        }


    }
    public void Reset(View view){
        activePlayer = 0;
        isGameActive = true;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;

        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null); // remove the X/O image
        }

        Toast.makeText(this, "Game reset!", Toast.LENGTH_SHORT).show();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
}