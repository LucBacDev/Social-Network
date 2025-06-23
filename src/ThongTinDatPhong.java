import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
	
public class ThongTinDatPhong {
	int maKH;
	static int[] maPhong;
	int soNgayThue;
	Date ngayCheckin;
	String trangThai ;
	
	public ThongTinDatPhong(int maKH, int[] maPhong, int soNgayThue, String ngayCheckin,String a) throws ParseException {
		
		this.maKH = maKH;
		this.maPhong = maPhong;
		this.soNgayThue = soNgayThue;
		this.trangThai=a;
		this.ngayCheckin = sdf.parse(ngayCheckin);
		
		}
		
	
	public int getMaKH() {
		return maKH;
	}
	public int[] getMaPhong() {
		return maPhong;
	}
	public int getSoNgayThue() {
		return soNgayThue;
	}
	public Date getNgayCheckin() {
		return ngayCheckin;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public void setMaPhong(int[] maPhong) {
		this.maPhong = maPhong;
	}
	public void setSoNgayThue(int soNgayThue) {
		this.soNgayThue = soNgayThue;
	}
	public void setNgayCheckin(Date ngayCheckin) {
		this.ngayCheckin = ngayCheckin;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public boolean check(int m) {
		for (int i=0;i<maPhong.length;i++) {
			if(maPhong[i]==m) {
				return true;
			}
		}
		return false;
	}
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static Calendar cal= Calendar.getInstance();
	 public boolean checkDate(String checkin) {
		 try {
			String ckin=sdf.format(ngayCheckin);
			if(ckin.equals(checkin)) {
				return true;
			}
			cal.setTime(ngayCheckin);
			cal.add(Calendar.DAY_OF_MONTH,soNgayThue);
			Date checkOut=cal.getTime();
			Date newCheckin=sdf.parse(checkin);
			if(newCheckin.before(checkOut)) {
				return true;
			}
		} catch (DateTimeException |ParseException e) {
			
			e.printStackTrace();
		}
		return false;
		 
	 }
}
