package com.example.jaipunjwani.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;
import java.util.HashSet;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class SetupActivity extends AppCompatActivity {

    Intent beginGameIntent;
    Context context = this;
    String names = "JAI PUNJWANI, LAWRENCE DEANGELO, SCOTT WENDELKEN, THOMAS CIAVATTONE, RICHARD GORDONSON, MORGAN ABRAMS, ROBERT BRADY, THOMAS ZORN, ADDLER PIERRE-LOUIS, ANDREW VIOLA, MAXWELL SIROTIN, ZACHARY POURNAZARI, THOMAS MURPHY, KAZI REZWAN, CONSTANTINOS PAPACOSTAS, JOSEPH RACHMUTH, David Chays, JEANENNE CAMPBELL, Nicolas Gomez, Chenyang Su, REBECCA GOTTERBARN, NATALIE SEQUEIRA, BRIAN REISKIN, BRIAN SEIDL, JACK DEMM, HARMIT MINHAS";
    TextView playerOneTextView, playerTwoTextView;
    Button beginGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        this.playerOneTextView = (TextView) findViewById(R.id.playerOneTextView);
        this.playerTwoTextView = (TextView) findViewById(R.id.playerTwoTextView);
        this.beginGameButton = (Button) findViewById(R.id.beginGameButton);
        this.beginGameIntent = new Intent(this, TicTacToeActivity.class);
        this.beginGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(beginGameIntent); // we will add info to our intent
            }
        });
        this.beginGameButton.setEnabled(false); // disable button till we know who our players are

    }

    @Override
    protected void onStart() {
        super.onStart();
        String[] namesArr = names.split(", ");

        Random rand = new Random();
        int playerOneIndex = rand.nextInt(namesArr.length); // returns a random index
        int playerTwoIndex;
        // ensure that we get a different random index
        while ((playerTwoIndex = rand.nextInt(namesArr.length)) == playerOneIndex) {
            continue;
    }

        String playerOne = namesArr[playerOneIndex];
        String playerTwo = namesArr[playerTwoIndex];
        this.playerOneTextView.setText("Player 1: \n" + playerOne);
        this.playerTwoTextView.setText("Player 2: \n" + playerTwo);

        // add players as key-value pairs to our gameplay activity
        beginGameIntent.putExtra("Player 1", playerOne);
        beginGameIntent.putExtra("Player 2", playerTwo);

        // enable button to begin gameplay, now that we have our players
        this.beginGameButton.setEnabled(true);
    }
}
