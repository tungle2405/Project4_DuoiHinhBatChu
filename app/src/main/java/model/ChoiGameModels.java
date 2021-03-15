package model;

import com.project4.duoihinhbatchu.ChoiGameActivity;

import java.util.ArrayList;

import object.CauDo;
import object.NguoiDung;

public class ChoiGameModels {
    ChoiGameActivity c;
    ArrayList<CauDo> arr;
    int cauSo = -1;
    public NguoiDung nguoiDung;

    //Khởi tạo dữ liệu câu đố
    public ChoiGameModels(ChoiGameActivity c) {
        this.c = c;
        nguoiDung = new NguoiDung();
        taoData();
    }

    //Tạo dữ liệu câu đố
    private void taoData() {
        arr = new ArrayList<>();
        arr.add(new CauDo("Man 4", "CuuTuNhatSinh", "https://lazi.vn/uploads/dhbc/1512462438_anh.jpg"));
        arr.add(new CauDo("Man 3", "ThatTruyen", "https://i1-ione.vnecdn.net/2017/11/24/5-1710-1511519373.png?w=680&h=0&q=100&dpr=1&fit=crop&s=hhOCTgtluyoPrpzPz2x-_g"));
        arr.add(new CauDo("Man 2", "TatYeu", "https://i1-ione.vnecdn.net/2017/11/24/3-6128-1511519373.jpg?w=680&h=0&q=100&dpr=1&fit=crop&s=8hmGacOBK1f0zcVEntx9Cw"));
        arr.add(new CauDo("Man 1", "TaiHoa", "https://media.vingle.net/images/ca_l/qfv6ylgrno.jpg"));
    }

    //Lấy câu đố để chuẩn bị hiển thị ra màn hình
    public CauDo layCauDo() {
        cauSo++;
        if (cauSo >= arr.size()) {
            cauSo = arr.size() - 1;
        }
        return arr.get(cauSo);
    }

    //Tạo hàm tương tác với người dùng (lấy thông tin và lưu thông tin người dùng)
    public void layThongTin(){
        nguoiDung.getTT(c);
    }
    public void luuThongTin(){
        nguoiDung.saveTT(c);
    }
}
