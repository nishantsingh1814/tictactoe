package com.example.nish.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements ViewGroup.OnClickListener {
    int n=3;
    LinearLayout rows[];
    MyButton buttons[][];
    LinearLayout mainLayout;
    final static int NO_PLAYER=0;
    final static int PLAYER1=1;
    final static int PLAYER2=2;
    final static int PLAYER1_WIN=1;
    final static int PLAYER2_WIN=2;
    final static int INCOMPLETE=3;
    final static int DRAW=4;

    boolean player1Turn=true;
    boolean gameOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mainLayout=(LinearLayout) findViewById(R.id.activity_main);
        setUpBoard();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.newGame){
            resetBoard();
        }
        else if(id == R.id.boardSize_3){

            if(n != 3) {
                n = 3;
                setUpBoard();
            }
            else{
                resetBoard();
            }
        }
        else if(id == R.id.boardSize_4){
            if(n != 4) {
                n = 4;
                setUpBoard();
            }
            else{
                resetBoard();
            }
        }
        else if(id == R.id.boardSize_5){
            if(n != 5) {
                n = 5;
                setUpBoard();
            }
            else{
                resetBoard();
            }
        }

        return true;
    }
    public void setUpBoard(){
        player1Turn = true;
        buttons = new MyButton[n][n];
        rows = new LinearLayout[n];
        gameOver = false;
        mainLayout.removeAllViews();
        for(int i=0;i<n;i++){
            rows[i]=new LinearLayout(this);
            LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);

            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);


            mainLayout.addView(rows[i]);
            View  v=new View(this);
            v.setBackgroundColor(Color.GRAY);
            LinearLayout.LayoutParams par =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,2);
            v.setLayoutParams(par);
            mainLayout.addView(v);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                buttons[i][j]=new MyButton(this);

                LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1);
                buttons[i][j].setPadding(10,10,10,10);

                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setPlayer(NO_PLAYER);
                buttons[i][j].setText("");
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setBackgroundColor(Color.rgb(0, 255, 255));
                rows[i].addView(buttons[i][j]);
                View  v=new View(this);
                v.setBackgroundColor(Color.GRAY);
                LinearLayout.LayoutParams par =new LinearLayout.LayoutParams(2,ViewGroup.LayoutParams.MATCH_PARENT);
                v.setLayoutParams(par);

                rows[i].addView(v);
            }
        }
    }
    private void resetBoard() {

        player1Turn = true;
        gameOver = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setPlayer(NO_PLAYER);
            }
        }
    }

    @Override
    public void onClick(View v) {
        MyButton b=(MyButton) v;
        if(gameOver){
            return;
        }
        if(b.getPlayer()!=NO_PLAYER){
            Toast.makeText(this,"Invalid Move",Toast.LENGTH_SHORT).show();
            return;
        }
        if(player1Turn){
            b.setPlayer(PLAYER1);
            b.setText("O");
            b.setTextSize(60);
        }
        else{
            b.setPlayer(PLAYER2);
            b.setText("X");
            b.setTextSize(60);
        }

        int status=gameStatus();
        if(status==PLAYER1_WIN||status==PLAYER2_WIN){
            Toast.makeText(this,"Player"+status+"win",Toast.LENGTH_SHORT).show();
            gameOver=true;
            return;
        }
        else if(status==DRAW){
            Toast.makeText(this,"GAME DRAW",Toast.LENGTH_SHORT).show();
            gameOver=true;
            return;
        }
        player1Turn=!player1Turn;
    }

    private int gameStatus() {
        //rows
        for(int i=0;i<n;i++){
            boolean flag=true;
            for(int j=0;j<n;j++){
                if(buttons[i][j].getPlayer()==NO_PLAYER||buttons[i][0].getPlayer()!=buttons[i][j].getPlayer()){
                    flag=false;
                    break;
                }
            }
            if(flag){
                if(buttons[i][0].getPlayer()==PLAYER1){
                    return PLAYER1_WIN;
                }
                else{
                    return PLAYER2_WIN;
                }
            }
        }
        //columns
        for(int i=0;i<n;i++){
            boolean flag=true;
            for(int j=0;j<n;j++){
                if(buttons[j][i].getPlayer()==NO_PLAYER||buttons[0][i].getPlayer()!=buttons[j][i].getPlayer()){
                    flag=false;
                    break;
                }

            }
            if(flag){
                if(buttons[0][i].getPlayer()==PLAYER1){
                    return PLAYER1_WIN;
                }
                else{
                    return PLAYER2_WIN;
                }
            }
        }
        //diagonal
        boolean flag=true;
        for(int i=0;i<n;i++){
            if(buttons[i][i].getPlayer()==NO_PLAYER||buttons[0][0].getPlayer()!=buttons[i][i].getPlayer()){
                flag=false;
                break;
            }
        }
        if(flag){
            if(buttons[0][0].getPlayer()==PLAYER1){
                return PLAYER1_WIN;
            }
            else{
                return PLAYER2_WIN;
            }
        }
        flag=true;
        for(int i = n - 1; i >= 0; i--){
            int col = n - 1 - i;
            if (buttons[i][col].getPlayer() == NO_PLAYER || buttons[n - 1][0].getPlayer() != buttons[i][col].getPlayer()) {
                flag = false;
                break;
            }

        }


        if(flag){
            if(buttons[n-1][0].getPlayer()==PLAYER1){
                return PLAYER1_WIN;
            }
            else{
                return PLAYER2_WIN;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(buttons[i][j].getPlayer() == NO_PLAYER){
                    return INCOMPLETE;
                }
            }
        }
        return DRAW;
    }
}
