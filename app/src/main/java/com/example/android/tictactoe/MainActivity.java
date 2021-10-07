package com.example.android.tictactoe;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     int gamenumber=1;
    // I am keeping X=1 and O=0 and blank spot as -1
    int currstate[]={-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int currplayer=0;
    int winpos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int taps=0;
    int player1won=0;
    int player2won=0;
    Boolean gamedraw=Boolean.FALSE;







    public void clicked(View view) {
        ++taps;



        ImageView img = (ImageView) view;
        int clickedimg = Integer.parseInt(img.getTag().toString());
        if (currstate[clickedimg] == -1) {


            currstate[clickedimg] = currplayer;

            img.setTranslationY(-50f);

            if (currplayer == 0) {
                ((ImageView)findViewById(R.id.activity1)).setImageResource(0);
                ((ImageView)findViewById(R.id.activity2)).setImageResource(R.drawable.ic_baseline_arrow_back_24);

                img.setImageResource(R.drawable.o);
                currplayer = 1;
            } else {
                ((ImageView)findViewById(R.id.activity2)).setImageResource(0);
                ((ImageView)findViewById(R.id.activity1)).setImageResource(R.drawable.ic_baseline_arrow_back_24);
                img.setImageDrawable(getResources().getDrawable(R.drawable.x));
                currplayer = 0;
            }
            img.animate().translationYBy(50f).setDuration(200);
            Boolean check=checkifwon();
            if(taps==9 && !checkifwon()){

                gamedraw=Boolean.TRUE;
                showWinner("THE GAME ENDED IN A TIE");


            }
        }

    }

    public Boolean checkifwon()
    {
        for(int i=0;i<8;i++) {
            int a = winpos[i][0];
            int b = winpos[i][1];
            int c = winpos[i][2];

            if(currstate[a]==currstate[b] && currstate[b]==currstate[c] && currstate[a]!=-1){
                if(currstate[a]==0){
                    showWinner(getIntent().getStringExtra("Player1")+" HAS WON THE GAME");
                    ++gamenumber;

                }else{
                    showWinner(getIntent().getStringExtra("Player2")+" HAS WON THE GAME");
                }return Boolean.TRUE;
            }
        }return Boolean.FALSE;
    }
    public void showWinner(String comand){
        new AlertDialog.Builder(this
        ).setTitle(comand).setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RestartGame();

            }
        }).setNegativeButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MainActivity.this,home_page.class);
                startActivity(intent);
            }
        }).show();
    }
    public void RestartGame() {
        currstate = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};

        taps = 0;
        if (gamedraw) {
            if (currplayer == 0) {
                ((ImageView) findViewById(R.id.activity2)).setImageResource(0);
                ((ImageView) findViewById(R.id.activity1)).setImageResource(R.drawable.ic_baseline_arrow_back_24);
                 gamedraw=Boolean.FALSE;
            } else {
                ((ImageView) findViewById(R.id.activity1)).setImageResource(0);
                ((ImageView) findViewById(R.id.activity2)).setImageResource(R.drawable.ic_baseline_arrow_back_24);
                gamedraw=Boolean.FALSE;
            }
        } else {
            if (currplayer == 0) {
                String winner = Integer.toString(++player2won);
                ((TextView) findViewById(R.id.player2wins)).setText(winner);

                ((ImageView) findViewById(R.id.activity2)).setImageResource(0);
                ((ImageView) findViewById(R.id.activity1)).setImageResource(R.drawable.ic_baseline_arrow_back_24);


            } else if (currplayer == 1) {
                String winner = Integer.toString(++player1won);
                ((TextView) findViewById(R.id.player1wins)).setText(winner);

                ((ImageView) findViewById(R.id.activity1)).setImageResource(0);
                ((ImageView) findViewById(R.id.activity2)).setImageResource(R.drawable.ic_baseline_arrow_back_24);

            }}
            ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Player1=getIntent().getStringExtra("Player1");
        String Player2=getIntent().getStringExtra("Player2");

        TextView txt1=findViewById(R.id.Player1);
        TextView txt2=findViewById(R.id.Player2);

        txt1.setText(Player1);

        txt2.setText(Player2);

        Button end=findViewById(R.id.newg);
        Button restart=findViewById(R.id.restart);

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,home_page.class));
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamedraw=true;
                RestartGame();
            }
        });

    }

}