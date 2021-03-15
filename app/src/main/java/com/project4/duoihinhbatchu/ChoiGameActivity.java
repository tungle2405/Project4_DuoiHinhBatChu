package com.project4.duoihinhbatchu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
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

    private String dapAn = "CaTinh";

    ArrayList<String> arrCauTraLoi;
    GridView gdvCauTraLoi;
    ArrayList<String> arrDapAn;
    GridView gdvDapAn;

    ImageView imgAnhCauDo;
    TextView txvDiemNguoiDung;

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

    //
    private void anhXa() {
        gdvCauTraLoi = findViewById(R.id.gdvCauTraLoi);
        gdvDapAn = findViewById(R.id.gdvDapAn);
        imgAnhCauDo = findViewById(R.id.imgAnhCauDo);
        txvDiemNguoiDung = findViewById(R.id.txvDiemNguoiDung);
    }

    //Khởi tạo mới câu trả lời, đáp án và gọi đến models để tạo ra câu hỏi
    private void init() {
        models = new ChoiGameModels(this);
        arrCauTraLoi = new ArrayList<>();
        arrDapAn = new ArrayList<>();

    }

    //Hiển thị câu đố bằng glide với
    private void hienCauDo() {
        cauDo = models.layCauDo();
        dapAn = cauDo.dapAn;
        bamData();
        hienThiCauTraLoi();
        hienThiDapAn();
        Glide.with(this)
                .load(cauDo.anh)
                .into(imgAnhCauDo);
        models.layThongTin();
        txvDiemNguoiDung.setText("Điểm: " + models.nguoiDung.diem);
    }

    //Hiển thị câu trả lời cho người dùng nhập
    private void hienThiCauTraLoi() {
        gdvCauTraLoi.setNumColumns(arrCauTraLoi.size());
        gdvCauTraLoi.setAdapter(new DapAnAdapter(this, 0, arrCauTraLoi));
    }

    //Sự kiện nhấn nút
    private void setOnClick() {
        //Sự kiện nhấn vào GridView gdvDapAn
        gdvDapAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0 && index < arrCauTraLoi.size()) {
                    for (int i = 0; i < arrCauTraLoi.size(); i++) {
                        if (arrCauTraLoi.get(i).length() == 0) {
                            index = i;
                            break;
                        }
                    }
                    //Di chuyển các chữ từ phần đáp án lên câu trả lời
                    arrDapAn.set(position, "");
                    arrCauTraLoi.set(index, s);
                    index++;
                    hienThiCauTraLoi();
                    hienThiDapAn();
                    checkWin();
                }
            }
        });
        //Sự kiện nhấn vào GridView gdvCauTraLoi
        gdvCauTraLoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) parent.getItemAtPosition(position);
                if (s.length() != 0) {
                    index = position;
                    //Di chuyển các chữ từ câu trả lời quay lại đáp án
                    arrCauTraLoi.set(position, "");
                    for (int i = 0; i < arrDapAn.size(); i++) {
                        if (arrDapAn.get(i).length() == 0) {
                            arrDapAn.set(i, s);
                            break;
                        }
                    }
                    hienThiCauTraLoi();
                    hienThiDapAn();
                }
            }
        });
    }

    //Hiện thị các chữ có trong đáp án
    private void hienThiDapAn() {
        gdvDapAn.setNumColumns(arrDapAn.size() / 3);
        gdvDapAn.setAdapter(new DapAnAdapter(this, 0, arrDapAn));
    }

    //Random các chữ để người chơi chọn làm câu trả lời, trong đó có các chữ có trong đáp án
    private void bamData() {
        index = 0;
        arrCauTraLoi.clear();
        arrDapAn.clear();
        Random r = new Random();
        for (int i = 0; i < dapAn.length(); i++) {
            arrCauTraLoi.add("");
            //Theo bảng mã ASCII đển chọn ngẫu nhiên các chữ in hoa có độ dài chữ gấp 2 lần đáp án
            String s = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s);
            String s1 = "" + (char) (r.nextInt(26) + 65);
            arrDapAn.add(s1);
        }
        //Chia đáp án thành các chữ riêng lẻ in hoa
        for (int i = 0; i < dapAn.length(); i++) {
            String s = "" + dapAn.charAt(i);
            arrDapAn.set(i, s.toUpperCase());
        }
        //Thay đổi vị trí ngẫu nhiên các chữ có đáp án
        for (int i = 0; i < arrDapAn.size(); i++) {
            String s = arrDapAn.get(i);
            int vt = r.nextInt(arrDapAn.size());
            arrDapAn.set(i, arrDapAn.get(vt));
            arrDapAn.set(vt, s);
        }
    }

    //Kiểm tra điều kiện chiến thắng để qua màn tiếp theo
    private void checkWin() {
        String s = "";
        for (String s1 : arrCauTraLoi) {
            s = s + s1;
        }
        s = s.toUpperCase();
        //Nếu trả lời đúng đáp án
        if (s.equals(dapAn.toUpperCase())) {
            Toast.makeText(this, "Bạn đã qua màn mới!!!", Toast.LENGTH_SHORT).show();
            //Lấy điểm đang có hiện tại cộng thêm điểm sau khi qua màn rồi lưu lại thông tin
            models.layThongTin();
            models.nguoiDung.diem = models.nguoiDung.diem + 10;
            models.luuThongTin();
            //Hiện thị câu đố tiếp theo
            hienCauDo();
        }
    }

    //Hàm tạo gợi ý cho người dùng
    public void moGoiY(View view) {
        //Kiểm tra xem người dùng còn điểm để dùng gợi ý hay ko
        models.layThongTin();
        if(models.nguoiDung.diem < 10){
            Toast.makeText(this, "Bạn đã hết điểm", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = 1;
        //Kiểm tra người dùng chưa trả lời đc ở câu nào
        for (int i = 0; i < arrCauTraLoi.size(); i++) {
            if (arrCauTraLoi.get(i).length()==0) {
                id = i;
                break;
            }
        }
        //Kiểm tra điều kiện người dùng trả lời đúng hết
        if (id == -1) {
            for (int i = 0; i < arrCauTraLoi.size(); i++) {
                String s = dapAn.toUpperCase().charAt(i) + "";
                if (!arrCauTraLoi.get(i).toUpperCase().equals(s)) {
                    id = i;
                    break;
                }
            }
            for (int i = 0; i < arrDapAn.size(); i++) {
                if (arrDapAn.get(i).length() == 0) {
                    arrDapAn.set(i, arrCauTraLoi.get(id));
                }
            }
        }
        String goiY = "" + dapAn.charAt(id);
        goiY.toUpperCase();
        //Kiểm tra trường hợp có kí tự trong câu trả lời trùng với gợi ý thì phải xoá nó đi
        for (int i = 0; i<arrCauTraLoi.size();i++){
            if(arrCauTraLoi.get(i).toUpperCase().equals(goiY)){
                arrCauTraLoi.set(i,"");
                break;
            }
        }
        //Lấy ra vị trí người dùng đang cần gợi ý
        for (int i = 0; i < arrDapAn.size(); i++) {
            if (goiY.equals(arrDapAn.get(i))) {
                arrDapAn.set(i, "");
                break;
            }
        }
        arrCauTraLoi.set(id, goiY);
        hienThiCauTraLoi();
        hienThiDapAn();
        models.layThongTin();
        models.nguoiDung.diem = models.nguoiDung.diem - 5;
        models.luuThongTin();
        txvDiemNguoiDung.setText("Điểm: " + models.nguoiDung.diem);

    }
}