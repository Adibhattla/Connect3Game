package com.example.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 =yellow && 1= red
    int activePlayer = 0;
    boolean gameIsActive = true;
    //2 means unplayed
    int[]gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
public void dropIn (View view){
    ImageView counter = (ImageView)view;

    int tappedCounter = Integer.parseInt(counter.getTag().toString());
    if(gameState[tappedCounter]==2 && gameIsActive) {
        gameState[tappedCounter] = activePlayer;
        counter.setTranslationY(-1000f);
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.red);
            activePlayer = 0;
        }
        counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);
        for(int[]win:winningPositions){
            if(gameState[win[0]]==gameState[win[1]] &&gameState[win[1]]==gameState[win[2]]
            && gameState[win[0]]!=2){
                gameIsActive=false;
                String winner = "Red";
              if((gameState[win[0]]==0)) winner = " Yellow";
                // someone has won make the layout appear

                TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                winnerMessage.setText(winner+" has won!");
                LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            } else{
                boolean gameIsOver = true;
                for(int counterState :gameState){
                    if(counterState == 2) gameIsOver = false;
                }
                if(gameIsOver){
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText("This Game is a Draw");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
    public void playAgain(View view){
    gameIsActive=true;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for(int i =0;i<gameState.length;i++){
            gameState[i]=2;
        }
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for(int i =0;i<grid.getChildCount();i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
