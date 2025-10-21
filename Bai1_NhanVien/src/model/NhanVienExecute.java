package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NhanVienExecute extends JFrame implements ActionListener, MouseListener {

	private Box box;
	private JLabel lblTieuDe;
	private JPanel jP1;
	private JLabel lblMa;
	private JTextField txtMa;
	private JPanel jP2;
	private JLabel lblHo;
	private JTextField txtHo;
	private JLabel lblTen;
	private JTextField txtTen;
	private JPanel jP3;
	private JLabel lblTuoi;
	private JTextField txtTuoi;
	private JLabel lblPhai;
	private JRadioButton jrbNu;
	private JPanel jP4;
	private JLabel lblLuong;
	private JTextField txtLuong;
	private JPanel jP5;
	private JLabel lblPhongban;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel jPanelCenter;
	private JPanel jPanelShoth;
	private JLabel lblTim;

	private JButton btnTim;
	private JTextField txtTim;
	private JButton btnThem;
	private JButton btnXoaT;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnSua;
	private DanhSachNhanVien dsnv;
	private GhiDocFile fi;
	private JComboBox<String> cbbPhongBan;
	private JPanel p;
	private JLabel lbl;
	public static final String tenfile = "data//nhanvien.txt";

	public NhanVienExecute(DanhSachNhanVien danhSachNhanVien) throws Exception {
		super("^-^");

		// Vung nourth
		box = new Box(BoxLayout.Y_AXIS);
		box.add(lblTieuDe = new JLabel("THONG TIN NHAN VIEN"));
		jP1 = new JPanel();
		jP1.add(lblMa = new JLabel("Ma nhan vien: "));
		jP1.add(txtMa = new JTextField(60));
		box.add(jP1);
		jP2 = new JPanel();
		jP2.add(lblHo = new JLabel("Ho: "));
		jP2.add(txtHo = new JTextField(20));
		jP2.add(lblTen = new JLabel("Ten nhan vien: "));
		jP2.add(txtTen = new JTextField(30));
		box.add(jP2);
		jP3 = new JPanel();
		jP3.add(lblTuoi = new JLabel("Tuoi: "));
		jP3.add(txtTuoi = new JTextField(50));
		jP3.add(lblPhai = new JLabel("Phai: "));
		jP3.add(jrbNu = new JRadioButton("Nu"));
		box.add(jP3);
		jP4 = new JPanel();
		jP4.add(lblLuong = new JLabel("Tien luong: "));
		jP4.add(txtLuong = new JTextField(60));
		box.add(jP4);
		jP5 = new JPanel();
		jP5.add(lblPhongban = new JLabel("Phong ban: "));

		String[] phongBan = { "Phong nhan su", "Phong tai chinh", "Phong san xuat", "Phong marketing" };
		cbbPhongBan = new JComboBox<String>(phongBan);

		jP5.add(cbbPhongBan);
		lblHo.setPreferredSize(lblMa.getPreferredSize());
		lblLuong.setPreferredSize(lblMa.getPreferredSize());
		lblTuoi.setPreferredSize(lblMa.getPreferredSize());
		lblHo.setPreferredSize(lblMa.getPreferredSize());
		lblPhongban.setPreferredSize(lblMa.getPreferredSize());
		box.add(jP5);
		add(box, BorderLayout.NORTH);
// Vung center
		jPanelCenter = new JPanel();
		String[] col = "MaNv; Ho; Ten; Phai; Tuoi; Phong; TienLuong".split(";");
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		//
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setPreferredSize(new Dimension(700, 300));
		//
		jPanelCenter.add(jScrollPane);
		add(jPanelCenter, BorderLayout.CENTER);
// Vung south
		jPanelShoth = new JPanel();
		jPanelShoth.add(lblTim = new JLabel("Nhap ma so can tim: "));
		jPanelShoth.add(txtTim = new JTextField(10));
		jPanelShoth.add(btnTim = new JButton("Tim"));
		jPanelShoth.add(btnThem = new JButton("Them"));
		jPanelShoth.add(btnXoaT = new JButton("Xoa Trang"));
		jPanelShoth.add(btnXoa = new JButton("Xoa"));
		jPanelShoth.add(btnLuu = new JButton("Luu"));
		jPanelShoth.add(btnSua = new JButton("Sua"));
		add(jPanelShoth, BorderLayout.SOUTH);
		// Gan su kien
		btnLuu.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaT.addActionListener(this);
		table.addMouseListener(this);
		dsnv = new DanhSachNhanVien();
		fi = new GhiDocFile();
		try {
			dsnv = (DanhSachNhanVien) fi.readFromFile(tenfile);
		} catch (Exception e) {

			e.printStackTrace(); // <-- in chi tiết lỗi thật

		}
		// Dat size va hien thi
		hienTable();
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		switch (((JButton) o).getText()) {
		case "Them" -> themNhanVien();
		case "Luu" -> luuNhanVien();
		case "Sua" -> suaNhanVien();
		case "Xoa" -> xoaNhanVien();
		case "Xoa Trang" -> xoaTrang();
		case "Tim" -> timNhanVien();
		}
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		txtLuong.setText("");
		jrbNu.setSelected(false);
		cbbPhongBan.setSelectedIndex(0);
		txtMa.requestFocus();

	}

	private void timNhanVien() {
		String ma = txtTim.getText().trim();
		NhanVien nv = dsnv.TimKiem(ma);
		if (nv != null) {
			// Chỉ hiện một dòng tìm được
			tableModel.setRowCount(0); // Xóa toàn bộ bảng
			tableModel.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.isPhai() ? "Nữ" : "Nam",
					nv.getTuoi(), nv.getPhongBan(), nv.getTienLuong() });
		} else {
			JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã: " + ma);
		}
	}

	private void suaNhanVien() {
		int row = table.getSelectedRow();
		if (row >= 0) {
			try {
				String ma = txtMa.getText().trim();
				String ho = txtHo.getText().trim();
				String ten = txtTen.getText().trim();
				boolean phai = jrbNu.isSelected();
				int tuoi = Integer.parseInt(txtTuoi.getText().trim());
				String phong = (String) cbbPhongBan.getSelectedItem();
				double luong = Double.parseDouble(txtLuong.getText().trim());

				NhanVien nv = new NhanVien(ma, ho, ten, phai, tuoi, phong, luong);
				if (dsnv.CapNhat(nv)) {

					JOptionPane.showMessageDialog(this, "Cập nhật thành công!");

					// --- Làm mới toàn bộ bảng để hiện tất cả nhân viên ---
					tableModel.setRowCount(0); // Xóa hết các dòng cũ
					hienTable(); // Thêm lại toàn bộ dữ liệu
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên cần cập nhật!");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Lỗi khi sửa: " + ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần sửa!");
		}
	}

	private void xoaNhanVien() {
		int row = table.getSelectedRow();
		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Chọn nhân viên cần xóa!");
			return;
		}

		String ma = tableModel.getValueAt(row, 0).toString();

		int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên " + ma + "?", "Xác nhận xóa",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			// Xóa khỏi danh sách và bảng
			dsnv.XoaNhanVien(ma);
			tableModel.removeRow(row);
			JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
		} else {
			// Nếu NO: làm mới bảng để hiển thị đầy đủ tất cả nhân viên
			tableModel.setRowCount(0);
			hienTable();
		}
	}

	private void themNhanVien() {
		try {
			String ma = txtMa.getText().trim();
			String ho = txtHo.getText().trim();
			String ten = txtTen.getText().trim();
			boolean phai = jrbNu.isSelected();
			String tuoiStr = txtTuoi.getText().trim();
			String luongStr = txtLuong.getText().trim();
			String phong = (String) cbbPhongBan.getSelectedItem();

			// ====== 🧩 KIỂM TRA HỢP LỆ DỮ LIỆU ======

			// 1️⃣ Kiểm tra rỗng
			if (ma.isEmpty() || ho.isEmpty() || ten.isEmpty() || tuoiStr.isEmpty() || luongStr.isEmpty()) {
				JOptionPane.showMessageDialog(this, "❌ Không được để trống thông tin!");
				return;
			}

			// 2️⃣ Regex mã nhân viên (VD: NV001)
			if (!ma.matches("^NV\\d{3}$")) {
				JOptionPane.showMessageDialog(this, "❌ Mã nhân viên phải có dạng NVxxx (VD: NV001)");
				return;
			}

			// 3️⃣ Họ và tên chỉ chứa chữ cái
			if (!ho.matches("^[A-Za-z\\s]+$") || !ten.matches("^[A-Za-z\\s]+$")) {
				JOptionPane.showMessageDialog(this, "❌ Họ và tên chỉ được chứa chữ cái!");
				return;
			}

			// 4️⃣ Tuổi là số và trong khoảng 18–60
			int tuoi;
			try {
				tuoi = Integer.parseInt(tuoiStr);
				if (tuoi < 18 || tuoi > 60) {
					JOptionPane.showMessageDialog(this, "❌ Tuổi phải từ 18 đến 60!");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "❌ Tuổi phải là số nguyên!");
				return;
			}

			// 5️⃣ Lương là số dương
			double luong;
			try {
				luong = Double.parseDouble(luongStr);
				if (luong <= 0) {
					JOptionPane.showMessageDialog(this, "❌ Lương phải lớn hơn 0!");
					return;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "❌ Lương phải là số hợp lệ!");
				return;
			}

			// 6️⃣ Kiểm tra phòng ban
			if (phong == null || phong.trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "❌ Vui lòng chọn phòng ban!");
				return;
			}

			// ====== ✅ Nếu hợp lệ thì thêm vào danh sách ======
			NhanVien nv = new NhanVien(ma, ho, ten, phai, tuoi, phong, luong);
			if (dsnv.ThemNhanVien(nv)) {
				tableModel.addRow(new Object[] { ma, ho, ten, (phai ? "Nữ" : "Nam"), tuoi, phong, luong });
				JOptionPane.showMessageDialog(this, "✅ Thêm thành công!");
			} else {
				JOptionPane.showMessageDialog(this, "⚠️ Mã nhân viên đã tồn tại!");
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "❌ Lỗi khi thêm: " + ex.getMessage());
		}
	}

	private void luuNhanVien() {
		try {
			fi.writeToFile(dsnv, tenfile);
			JOptionPane.showMessageDialog(this, "Lưu dữ liệu thành công!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi lưu: " + e.getMessage());
		}
	}

	public void hienTable() {
		for (NhanVien nv : dsnv.getList()) {
			tableModel.addRow(new Object[] { nv.getMaNV(), nv.getHo(), nv.getTen(), nv.isPhai() ? "Nu" : "Nam",
					nv.getTuoi(), nv.getPhongBan(), nv.getTienLuong() });
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		if (row >= 0) {
			txtMa.setText(tableModel.getValueAt(row, 0).toString());
			txtHo.setText(tableModel.getValueAt(row, 1).toString());
			txtTen.setText(tableModel.getValueAt(row, 2).toString());
			txtTuoi.setText(tableModel.getValueAt(row, 4).toString());
			txtLuong.setText(tableModel.getValueAt(row, 6).toString());
			jrbNu.setSelected(tableModel.getValueAt(row, 3).toString().equals("Nữ"));
			cbbPhongBan.setSelectedItem(tableModel.getValueAt(row, 5).toString());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
