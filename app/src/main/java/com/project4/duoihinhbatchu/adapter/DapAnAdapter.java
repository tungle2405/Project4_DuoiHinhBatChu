package com.project4.duoihinhbatchu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project4.duoihinhbatchu.R;

import java.util.ArrayList;
import java.util.List;

public class DapAnAdapter extends ArrayAdapter<String> {
    private Context myContext;
    private ArrayList<String> arr;
    public DapAnAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.myContext = context;
        this.arr = new ArrayList<>(objects);
    }

    //Chia các chữ ngẫu nhiên có từ câu đố để thêm vào các TextView câu trả lời
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_tra_loi, null);
        }
        TextView txvCauTraLoi = convertView.findViewById(R.id.txvCauTraLoi);
        txvCauTraLoi.setText(this.arr.get(position));
        return convertView;
    }
}
