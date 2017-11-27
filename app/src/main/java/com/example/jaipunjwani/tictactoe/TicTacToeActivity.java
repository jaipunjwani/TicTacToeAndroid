package com.example.jaipunjwani.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class TicTacToeActivity extends AppCompatActivity {

    Context context = this;
    ArrayList<ImageView> board = new ArrayList<>(9);
    TextView gameStatusTextView;
    Player playerOne, playerTwo, currentPlayer;
    boolean gameOver = false;

    // all indices that we have to check to determine if there is a winner
    int[][] winningRows = {
            {0,1,2}, {3,4,5}, {6,7,8},    // horizontal rows
            {0,3,6}, {1,4,7}, {2,5,8},    // vertical rows
            {0,4,8}, {2,4,6}};            // diagonal rows

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        // get player names
        Intent intent = getIntent(); // gets intent that was used to start activity
        String playerOneName = intent.getStringExtra("Player 1");
        String playerTwoName = intent.getStringExtra("Player 2");

        // get icons
        Drawable playerOneDrawable = ContextCompat.getDrawable(context, R.drawable.x);
        Drawable playerTwoDrawable = ContextCompat.getDrawable(context, R.drawable.o);

        // instantiate Player objects
        playerOne = new Player(playerOneName, playerOneDrawable);
        playerTwo = new Player(playerTwoName, playerTwoDrawable);

        // get references to ImageViews (nodes), and add to our board list
        board.add((ImageView) findViewById(R.id.first));
        board.add((ImageView) findViewById(R.id.second));
        board.add((ImageView) findViewById(R.id.third));
        board.add((ImageView) findViewById(R.id.fourth));
        board.add((ImageView) findViewById(R.id.fifth));
        board.add((ImageView) findViewById(R.id.sixth));
        board.add((ImageView) findViewById(R.id.seventh));
        board.add((ImageView) findViewById(R.id.eighth));
        board.add((ImageView) findViewById(R.id.ninth));

        gameStatusTextView = (TextView) findViewById(R.id.gameStatusTextView);
        gameStatusTextView.setText(playerOneName + "'s turn");

        // define what action to do once each node is clicked on
        View.OnClickListener nodeListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOver) {
                    ((ImageView) v).setImageDrawable(currentPlayer.icon);
                    checkBoard();
                    switchTurn();
                }
            }
        };

        // set listener for each node so that it changes to icon of current player's turn
        for (ImageView node: board) {
            node.setOnClickListener(nodeListener);
        }

        currentPlayer = playerOne;
    }

    public void checkBoard() {
        for (int[] row: winningRows) {
            Drawable drawable1 = board.get(row[0]).getDrawable();
            Drawable drawable2 = board.get(row[1]).getDrawable();
            Drawable drawable3 = board.get(row[2]).getDrawable();
            if (drawable1 == drawable2 && drawable2 == drawable3) {
                gameOver = true;
                gameStatusTextView.setText("Winner: " + currentPlayer.name);
                break;
            }
        }

    }

    public void switchTurn() {
        if (!gameOver) {
            if (currentPlayer == playerOne) {
                currentPlayer = playerTwo;
            } else {
                currentPlayer = playerOne;
            }
            gameStatusTextView.setText(currentPlayer.name + "'s turn");
        }
    }

}
