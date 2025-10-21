package model;

import java.io.Serializable;
import java.util.Objects;

public class NhanVien implements Serializable {
	private String maNV;
	private String ho;
	private String ten;
	private boolean phai;
	private int tuoi;
	private String phongBan;
	private double tienLuong;

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ho, maNV, phai, phongBan, ten, tienLuong, tuoi);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(ho, other.ho) && Objects.equals(maNV, other.maNV) && phai == other.phai
				&& Objects.equals(phongBan, other.phongBan) && Objects.equals(ten, other.ten)
				&& Double.doubleToLongBits(tienLuong) == Double.doubleToLongBits(other.tienLuong) && tuoi == other.tuoi;
	}

	public NhanVien(String maNV, String ho, String ten, boolean phai, int tuoi, String phongBan, double tienLuong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.phai = phai;
		this.tuoi = tuoi;
		this.phongBan = phongBan;
		this.tienLuong = tienLuong;
	}

	public NhanVien() {
		super();
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getPhongBan() {
		return phongBan;
	}

	public void setPhongBan(String phongBan) {
		this.phongBan = phongBan;
	}

	public double getTienLuong() {
		return tienLuong;
	}

//	@Override
//	public String toString() {
//		return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", phai=" + phai + ", tuoi=" + tuoi
//				+ ", phongBan=" + phongBan + ", tienLuong=" + tienLuong + "]";
//	}

	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}

	@Override
	public String toString() {
		return maNV + ";" + ho + ";" + ten + ";" + phai + ";" + tuoi + ";" + phongBan + ";" + tienLuong;
	}

}
