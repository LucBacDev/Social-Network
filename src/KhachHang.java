
public class KhachHang {
	private int maKh;
	private String ten;
	private String soDT,soCMND;
	static int ma = 100;
	
	public KhachHang( String ten, String soDT, String soCMND) {
		this.maKh = ma;
		this.ten = ten;
		this.soDT = soDT;
		this.soCMND = soCMND;
		ma++;
	}
	public int getMaKh() {
		return maKh;
	}
	public String getTen() {
		return ten;
	}
	public String getSoDT() {
		return soDT;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public static int getMa() {
		return ma;
	}
	public void setMaKh(int maKh) {
		this.maKh = maKh;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public void setSpCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public static void setMa(int ma) {
		KhachHang.ma = ma;
	}
	
}
