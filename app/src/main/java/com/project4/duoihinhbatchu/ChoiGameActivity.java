package com.project4.duoihinhbatchu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.project4.duoihinhbatchu.adapter.DapAnAdapter;

import java.util.ArrayList;
import java.util.Random;

import model.ChoiGameModels;
import object.CauDo;

public class ChoiGameActivity extends AppCompatActivity {

    ChoiGameModels models;
    CauDo cauDo;

    private String dapAn="CaTinh";

    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraLoi;
    ArrayList<String> arrDapAn;
    GridView gdvDapAn;

    ImageView imgAnhCauDo;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choi_game);
        init();
        anhXa();
        setOnClick();
        hienCauDo();
    }
    private void anhXa(){
        gdvCauTraLoi = findViewById(R.id.gdvCauTraLoi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
        imgAnhCauDo = findViewById(R.id.imgAnhCauDo);

    }
    private void init(){
        models = new ChoiGameModels(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();

    }
    private void hienCauDo(){
        cauDo = models.layCauDo();
        dapAn = cauDo.dapAn;
        bamData();
        hienThiCauTraLoi();
        hienThiDapAn();
        Glide.with(this)
                .load(cauDo.anh)
                .into(imgAnhCauDo);
    }
    private void hienThiCauTraLoi(){
        gdvCauTraLoi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraLoi.setAdapter(new DapAnAdapter(this,0,arrCauTraLoi));
    }
    private void setOnClick(){
        gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String)parent.getItemAtPosition(position);
                if(s.length()!=0 && index<arrCauTraLoi.size()){
                    for(int i=0;i<arrCauTraLoi.size();i++){
                        if(arrCauTraLoi.get(i).length()==0){
                            index = i;
                            break;
                        }
                    }
                    arrDapAn.set(position,"");
                    arrCauTraLoi.set(index,s);
                    index++;
                    hienThiCauTraLoi();
                    hienThiDapAn();
                    checkWin();
                }
            }
        });
        gdvCauTraLoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String)parent.getItemAtPosition(position);
                if(s.length() != 0){
                    index = position;
                    arrCauTraLoi.set(position,"");
                    for(int i=0; i<arrDapAn.size();i++){
                        if(arrDapAn.get(i).length()==0){
                            arrDapAn.set(i,s);
                            break;
                        }
                    }
                    hienThiCauTraLoi();
                    hienThiDapAn();
                }
            }
        });
    }
    private void hienThiDapAn(){
        gdvDapAn.setNumColumns(arrDapAn.size()/3);
        gdvDapAn.setAdapter(new DapAnAdapter(this,0,arrDapAn));
    }
    private void bamData(){
        index = 0;
        arrCauTraLoi.clear();
        arrDapAn.clear();
        Random r = new Random();
        for (int i=0;i<dapAn.length();i++){
            arrCauTraLoi.add("");
            String s = ""+(char)(r.nextInt(26) + 65);
            arrDapAn.add(s);
            String s1 = ""+(char)(r.nextInt(26) + 65);
            arrDapAn.add(s1);
        }
        for (int i=0;i<dapAn.length();i++){
            String s = ""+dapAn.charAt(i);
            arrDapAn.set(i,s.toUpperCase());
        }
        for (int i=0;i<arrDapAn.size();i++){
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i,arrDapAn.get(vt));
            arrDapAn.set(vt,s);
        }
    }
    private void checkWin(){
        String s="";
        for (String s1:arrCauTraLoi){
            s=s+s1;
        }
        s=s.toUpperCase();
        if(s.equals(dapAn.toUpperCase())){
            Toast.makeText(this,"Bạn đã chiến thắng!!!",Toast.LENGTH_SHORT).show();
            hienCauDo();
        }
    }
}