package com.project4.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.project4.duoihinhbatchu.adapter.DapAnAdapter;

import java.util.ArrayList;

public class ChoiGameActivity extends AppCompatActivity {

    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraLoi;
    ArrayList<String> arrDapAn;
    GridView gdvDapAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        init();
        anhXa();
        hienThiCauTraLoi();
        hienThiDapAn();
    }

    private void anhXa(){
        gdvCauTraLoi = findViewById(R.id.gdvCauTraLoi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
    }
    private void init(){
        arrCauTraLoi = new ArrayList<>();
        arrCauTraLoi.add("B");
        arrCauTraLoi.add("B");
        arrCauTraLoi.add("");
        arrCauTraLoi.add("B");
        arrCauTraLoi.add("B");

        arrDapAn = new ArrayList<>();
        arrDapAn.add("T");
        arrDapAn.add("U");
        arrDapAn.add("N");
        arrDapAn.add("G");
        arrDapAn.add("P");
        arrDapAn.add("R");
        arrDapAn.add("O");
    }
    private void hienThiCauTraLoi(){
        gdvCauTraLoi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraLoi.setAdapter(new DapAnAdapter(this,0,arrCauTraLoi));
    }
    private void hienThiDapAn(){
        gdvDapAn.setNumColumns(arrDapAn.size()/2);
        gdvDapAn.setAdapter(new DapAnAdapter(this,0,arrDapAn));
    }
}