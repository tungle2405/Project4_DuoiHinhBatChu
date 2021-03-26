package com.project4.duoihinhbatchu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KetQuaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ket_qua);

        TextView scoreLabel = (TextView)findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView)findViewById(R.id.highScoreLabel);

        int diem = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(diem + " ");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int diemCao = settings.getInt("HIGH_SCORE",0);

        if(diem > diemCao){
            highScoreLabel.setText("Điểm Cao: " + diem);

            //Lưu
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", diem);
            editor.commit();
        }else {
            highScoreLabel.setText("Điểm Cao: " + diemCao);
        }
    }

    public void choiLai(View view) {
        if(DATA.getData().arrCauDo.size()>0){
            startActivity(new Intent(this,ChoiGameActivity.class));
        }
    }
}