package lehuuphuc_22713601;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuanLyNhanVien {
private ArrayList<NhanVien> list;
public QuanLyNhanVien() {
	list = new ArrayList<>();
}
public boolean ThemNhanVien(NhanVien nhanVien) {
	for (NhanVien nhanVien2 : list) {
		if(nhanVien2.getMaNV().equalsIgnoreCase(nhanVien.getMaNV())) {
			return false;
		}
	}
	list.add(nhanVien);
	return true;
}
}
