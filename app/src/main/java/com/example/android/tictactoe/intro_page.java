package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class intro_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_page);

        EditText txt1=findViewById(R.id.player1name);
        EditText txt2=findViewById(R.id.Plaer2name);
        Button btn2=findViewById(R.id.letsplay);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(intro_page.this,MainActivity.class);
                String Player1=txt1.getText().toString();
                String Player2=txt2.getText().toString();
                intent.putExtra("Player1",Player1+" (O)");
                intent.putExtra("Player2",Player2+" (X)");
                startActivity(intent);
            }
        });


    }}
