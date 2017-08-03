package com.example.nish.tictactoe;

import android.content.Context;
import android.widget.Button;



public class MyButton extends Button {
    private int player;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public MyButton(Context context) {
        super(context);
    }
}
