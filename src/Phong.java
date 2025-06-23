
public class Phong {
	int maPhong;
	String tenPhong;
	String kieuPhong;
	int giaTien;
	
	public Phong(int maPhong, String tenPhong, String kieuPhong, int giaTien) {
		
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.kieuPhong = kieuPhong;
		this.giaTien = giaTien;
	}
	
	public int getMaPhong() {
		return maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public String getKieuPhong() {
		return kieuPhong;
	}
	public int getGiaTien() {
		return giaTien;
	}
	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public void setKieuPhong(String kieuPhong) {
		this.kieuPhong = kieuPhong;
	}
	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}
	@Override
	public String toString() {
		
		return String.format("%-5d %10s %10s %10d",maPhong,tenPhong,kieuPhong,giaTien);
	}
}
