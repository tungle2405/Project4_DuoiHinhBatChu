package object;

//Tạo lớp CauDo có các thành phần của câu đố là tên, đáp án và ảnh của câu đố
public class CauDo {
    public String ten, dapAn, anh;
    public CauDo(){

    }
    public CauDo(String ten, String dapAn, String anh) {
        this.ten = ten;
        this.dapAn = dapAn;
        this.anh = anh;
    }
}
