package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GhiDocFile {
	public static void writeToFile(DanhSachNhanVien dsnv, String file) throws Exception {
		ObjectOutputStream out = null;
		// new FileOutputStream(file): mở luồng ghi byte,
		// nếu file chưa tồn tại tạo file mới
		// ObjectOutputStream: là “công cụ” giúp chuyển đổi đối tượng
		// Java thành dạng nhị phân để lưu vào file.
		out = new ObjectOutputStream(new FileOutputStream(file));
		// writeObject(dsnv): Ghi đối tượng vào file
		out.writeObject(dsnv);
		out.close();
	}

	public Object readFromFile(String file) throws Exception {
		// FileInputStream(file): Mở luồng đọc byte từ file.
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		Object list = ois.readObject();
		ois.close();
		return list;
	}
}
