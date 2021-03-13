package model;

import com.project4.duoihinhbatchu.ChoiGameActivity;

import java.util.ArrayList;

import object.CauDo;

public class ChoiGameModels {
    ChoiGameActivity c;
    ArrayList<CauDo> arr;
    int cauSo = -1;

    public ChoiGameModels(ChoiGameActivity c){
        this.c = c;
        taoData();
    }
    private void taoData(){
        arr = new ArrayList<>();
        arr.add(new CauDo("Man 4","CuuTuNhatSinh","https://lazi.vn/uploads/dhbc/1512462438_anh.jpg"));
        arr.add(new CauDo("Man 3","ThatTruyen","https://i1-ione.vnecdn.net/2017/11/24/5-1710-1511519373.png?w=680&h=0&q=100&dpr=1&fit=crop&s=hhOCTgtluyoPrpzPz2x-_g"));
        arr.add(new CauDo("Man 2","TatYeu","https://i1-ione.vnecdn.net/2017/11/24/3-6128-1511519373.jpg?w=680&h=0&q=100&dpr=1&fit=crop&s=8hmGacOBK1f0zcVEntx9Cw"));
        arr.add(new CauDo("Man 1","TaiHoa","https://media.vingle.net/images/ca_l/qfv6ylgrno.jpg"));
    }
    public CauDo layCauDo(){
        cauSo++;
        if(cauSo>=arr.size()){
            cauSo=arr.size()-1;
        }
        return arr.get(cauSo);
    }
}
