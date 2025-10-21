package model;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachNhanVien implements Serializable {
	private ArrayList<NhanVien> arrayList;

	public DanhSachNhanVien() {
		arrayList = new ArrayList<NhanVien>();
	}

	public boolean ThemNhanVien(NhanVien nhanVien) {
		for (NhanVien nhanVien2 : arrayList) {
			if (nhanVien2.getMaNV().equalsIgnoreCase(nhanVien.getMaNV())) {
				return false;
			}
		}
		arrayList.add(nhanVien);
		return true;
	}

	public boolean XoaNhanVien(String maNV) {
		return arrayList.removeIf(nv -> nv.getMaNV().equalsIgnoreCase(maNV));
	}

	public NhanVien TimKiem(String maNV) {
		for (NhanVien nhanVien : arrayList) {
			if (nhanVien.getMaNV().equalsIgnoreCase(maNV))
				return nhanVien;
		}
		return null;

	}

	public boolean CapNhat(NhanVien nhanVien) {
		for (int i = 0; i < arrayList.size(); i++) {
			NhanVien nhanVien2 = arrayList.get(i);
			if (nhanVien.getMaNV().equalsIgnoreCase(nhanVien2.getMaNV())) {
				nhanVien2.setTen(nhanVien.getTen());
				nhanVien2.setHo(nhanVien.getHo());
				nhanVien2.setPhai(nhanVien.isPhai());
				nhanVien2.setPhongBan(nhanVien.getPhongBan());
				nhanVien2.setTienLuong(nhanVien.getTienLuong());
				nhanVien2.setTuoi(nhanVien.getTuoi());
				return true;
			}
		}
		return false;
	}

	public NhanVien getNhanVien(int i) {
		return (i >= 0 && i < arrayList.size()) ? arrayList.get(i) : null;
	}

	public int tong() {
		return arrayList.size();
	}

	public ArrayList<NhanVien> getList() {
		return arrayList;
	}
}
