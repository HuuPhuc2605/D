package model;

public class Main {
	public static void main(String[] args) throws Exception {
		DanhSachNhanVien dao = new DanhSachNhanVien();
		new NhanVienExecute(dao).setVisible(true);
	}
}
