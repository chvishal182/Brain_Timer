package com.example.brain_trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    Button go,again,b0,b1,b2,b3;
    TextView timer,que,score,ans;
    GridLayout grid;
    int a,b,c,d,up=0,down=0;
    String disQ;
    ArrayList<Integer>options = new ArrayList<Integer>();
    ArrayList<Button>buttons = new ArrayList<Button>();
    CountDownTimer count;
    DecimalFormat formatter = new DecimalFormat("00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.consLay);
        go = findViewById(R.id.btnGo);
        b0 = findViewById(R.id.btnNum0);
        b1 = findViewById(R.id.btnNum1);
        b2 = findViewById(R.id.btnNum2);
        b3 = findViewById(R.id.btnNum3);
        again = findViewById(R.id.btnAgain);
        grid = findViewById(R.id.gLayout);
        timer = findViewById(R.id.txtTimer);
        que = findViewById(R.id.txtQuestion);
        score = findViewById(R.id.txtScore);
        ans = findViewById(R.id.txtRes);



        buttons.add(b0);
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);








    }

    public void chooseAnswer(View view) {

        String check = (String) view.getTag();
        if(options.get(Integer.valueOf(check))==options.get(c))
        {
            ans.setText("Correct ; )");up++;
        }else {ans.setText("Incorrect ; (");}
        down++;
        score.setText(String.valueOf(up)+"/"+String.valueOf(down));
        generateNumbers();
        ans.setVisibility(View.VISIBLE);
    }

    public void start(View view) {



        show();

        count  = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    timer.setText(formatter.format(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                again.setVisibility(View.VISIBLE);
                again.setEnabled(true);
                for(Button bt : buttons)
                {
                    bt.setEnabled(false);
                }
                ans.setText("Time's UP ; (");
                up=0;down=0;

            }
        }.start();


    }

    public void generateNumbers()
    {
        Random random = new Random();
        a = random.nextInt(21)+1;
        b = random.nextInt(31)+1;
        c = (random.nextInt(a+b))%4;

        disQ = String.valueOf(a)+"+"+String.valueOf(b);
        que.setText(disQ);
        for(int i=0;i<3;i++)
        {
            if(i==c)
            {
                options.add(a+b);
            }
            d  = (random.nextInt(41));
            while (d==(a+b)||options.contains(d))
            {  d  = (random.nextInt(41));}
            {options.add(d+(10));}

        }

        for(Button bts:buttons)
        {

            if(bts.getTag().equals(String.valueOf(c)))
            {
                bts.setText(String.valueOf(a+b));
            }
            else
            {
                String abd = (String) bts.getTag();
                bts.setText(String.valueOf(Integer.valueOf(options.get(Integer.valueOf(abd)))));
            }
        }
    }

    public void show()
        {
            generateNumbers();
            go.setVisibility(View.INVISIBLE);
            que.setText(disQ);
            for(Button bt : buttons)
            {
                bt.setEnabled(true);
            }
            grid.setVisibility(View.VISIBLE);
            timer.setVisibility(View.VISIBLE);
            que.setVisibility(View.VISIBLE);
            score.setText(String.valueOf(up)+"/"+String.valueOf(down));
            score.setVisibility(View.VISIBLE);
            again.setVisibility(View.INVISIBLE);
            ans.setText("");

        }
}