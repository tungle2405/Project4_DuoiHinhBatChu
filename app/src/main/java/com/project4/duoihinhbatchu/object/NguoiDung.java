package com.project4.duoihinhbatchu.object;

import android.content.Context;
import android.content.SharedPreferences;

public class NguoiDung {
    public String nameData = "dhbcData";
    public int diem;

    public void saveTT(Context ct) {
        SharedPreferences settings = ct.getSharedPreferences(nameData, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("diem",diem);
        editor.commit();
    }
    public void getTT(Context ct){
        SharedPreferences settings = ct.getSharedPreferences(nameData,0);
        diem = settings.getInt("diem",100);
    }
}
