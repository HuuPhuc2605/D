package lehuuphuc_22713601;

import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private String phai;
	private String phong;
	private double tienLuong;
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
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public String getPhai() {
		return phai;
	}
	public void setPhai(String phai) {
		this.phai = phai;
	}
	public String getPhong() {
		return phong;
	}
	public void setPhong(String phong) {
		this.phong = phong;
	}
	public double getTienLuong() {
		return tienLuong;
	}
	public void setTienLuong(double tienLuong) {
		this.tienLuong = tienLuong;
	}
	public NhanVien(String maNV, String ho, String ten, int tuoi, String phai, String phong, double tienLuong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.phong = phong;
		this.tienLuong = tienLuong;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ho, maNV, phai, phong, ten, tienLuong, tuoi);
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
		return Objects.equals(ho, other.ho) && Objects.equals(maNV, other.maNV) && Objects.equals(phai, other.phai)
				&& Objects.equals(phong, other.phong) && Objects.equals(ten, other.ten)
				&& Double.doubleToLongBits(tienLuong) == Double.doubleToLongBits(other.tienLuong) && tuoi == other.tuoi;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ho=" + ho + ", ten=" + ten + ", tuoi=" + tuoi + ", phai=" + phai
				+ ", phong=" + phong + ", tienLuong=" + tienLuong + "]";
	}

}
