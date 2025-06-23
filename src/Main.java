import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;



public class Main {
	static int chon=0;
	static int chon1=0;
	static Scanner sc = new Scanner(System.in);
	static File phongFile= new File("E:/phong.txt");
	static File fileKH = new File("E:/khachhang.txt");
	static File fileDP = new File("E:/datphong.txt");
	static ArrayList<Phong> dsPhong = new ArrayList<>();
	static ArrayList<KhachHang> dsKhachHang=new ArrayList<>();
	static ArrayList<ThongTinDatPhong> dsDatPhong=new ArrayList<>();
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		docFilePhong();
		try {
		docDsKhacHang();
		}catch(Exception e) {
			System.out.println("chưa có file khách hàng:tạo file mới");
			dsKhachHang=new ArrayList<>();
		}
		try {
		docDsDatPhong();
		}catch(Exception e) {
			System.out.println("chưa có file danh sách đặt phòng:tạo file mới");
			dsDatPhong=new ArrayList<>();
		}
		
		do {
			hamHienThiMenu();
			System.out.println("nhập lựa chọn của bạn");
			chon=sc.nextInt();
			sc.nextLine();
			switch(chon) {
			case 1:	hienThiDs();
					break;
			case 2:	sapXepDs();
					hienThiDs();
					break;
			case 3:	
				try {
					nhapThongTinDatPhong();
				} catch (java.text.ParseException e) {
					System.out.println("nhập sai");
					
				}
					break;
			
				
			case 4:	hienThiChucNang();
					chon1=sc.nextInt();
					sc.nextLine();
					switch(chon1) {
					case 1: timThongTinKhach();
						break;
					case 2: timThongTinPhong();
						break;
					case 0: break;
					default: System.out.println("bạn đã chọn sai");
					break;
					}
					break;
			case 5:nhapThongTinCheckIn();
					break;
			case 6:nhapThongTinCheckOut();
					break;
			
			case 0: break;
			default: System.out.println("Bạn đã nhập sai,hãy nhập lại");
			}
		}while(chon!=0);
		hamLuuKhachHang();
		hamLuuDatPhong();
	}

	private static void nhapThongTinCheckOut() {
			Calendar cal = Calendar.getInstance();
			int day=cal.get(Calendar.DAY_OF_MONTH);
			int month=cal.get(Calendar.MONTH)+1;
			int year=cal.get(Calendar.YEAR);
			String now =day+"/"+month+"/"+year;
			Date a= new Date();
			Date b= new Date();
			try {
				a = sdf.parse(now);
				
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		System.out.println("Nhập số điện thoại khách hàng");
		String soDT=sc.nextLine();
		System.out.println("Nhập ngày checkIn");
		String inputDay=sc.nextLine();
		try {
			b=sdf.parse(inputDay);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		for (KhachHang khach : dsKhachHang) {
			if(khach.getSoDT().equals(soDT)) {
				int ma=khach.getMaKh();
				timThongTinKhach(khach.getMaKh());
				System.out.println("Xác nhận đúng thông tin của khách");
				System.out.println("đúng nhập 1 sai nhập 0");
				int nhap=sc.nextInt();
				if(nhap==1) {
				for (int i=0;i<dsDatPhong.size();i++) {
					ThongTinDatPhong tt = dsDatPhong.get(i);
					
					if(tt.getMaKH()==ma&&(tt.getNgayCheckin().equals(b))) {
						String checkIn=sdf.format(tt.getNgayCheckin());
						String ngayCheckIn=checkIn.substring(0,checkIn.indexOf("/"));
						String A=sdf.format(a);
						String ngayA=A.substring(0,A.indexOf("/"));
						int kq=(Integer.parseInt(ngayA)-Integer.parseInt(ngayCheckIn));
						if(kq==0) {
							kq=1;
					}
						tt.setSoNgayThue(kq);
						tt.setTrangThai("Đã checkOut");
					
						dsDatPhong.set(i, tt);
					}
				}
				System.out.println("Đã checkOut");
				hamTinhTien(khach.getMaKh());
			}
				else {
					break;
				}
		}
		
		}
		
	}



		private static void hamTinhTien(int m) {
			int giaTien=0;
			for (ThongTinDatPhong tt : dsDatPhong) {
				if(tt.getMaKH()==m) {
					for (int i = 0; i < tt.getMaPhong().length; i++) {
						int ma=tt.getMaPhong()[i];
						Phong p=layPhong(ma);
						giaTien=giaTien+tt.getSoNgayThue()*p.getGiaTien();
					}
					
				}
			}
			System.out.println("-------------------Hóa Đơn----------------");
			for (KhachHang kh : dsKhachHang) {
				if(kh.getMaKh()==m) {
					System.out.println("Mã khách hàng: "+kh.getMaKh());
					System.out.println("Tên khách hàng: "+kh.getTen());
					System.out.println("Số điện thoại khách hàng: "+kh.getSoDT());
					System.out.println("Số chứng minh thư: "+kh.getSoCMND());
				}
			}
			for (ThongTinDatPhong tt : dsDatPhong) {
				if(tt.getMaKH()==m) {
					System.out.println("danh sách phòng khách đã đặt");
					for (int i = 0; i < tt.maPhong.length; i++) {
						int ma = tt.maPhong[i];
						Phong p=layPhong(ma);
						System.out.println("Mã phòng: "+p.getMaPhong());
						System.out.println("Tên phòng: "+p.getTenPhong());
						System.out.println("Kiểu phòng: "+p.getKieuPhong());
						System.out.println("Giá Tiền 1 ngày: "+p.getGiaTien());
					}
					System.out.println("Số ngày thuê "+tt.getSoNgayThue());
					System.out.println("Ngày CheckIn "+tt.getNgayCheckin());
					
				}}
					System.out.println("Tổng tiền: "+giaTien);
					System.out.println("----------Cảm ơn quý khách----------");
		}



		private static void nhapThongTinCheckIn() {
			Calendar cal = Calendar.getInstance();
			int day=cal.get(Calendar.DAY_OF_MONTH);
			int month=cal.get(Calendar.MONTH)+1;
			int year=cal.get(Calendar.YEAR);
			String now =day+"/"+month+"/"+year;
			Date a= new Date();
			try {
				a = sdf.parse(now);
				
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		System.out.println("Nhập số điện thoại khách hàng");
		String soDT=sc.nextLine();
		for (KhachHang khach : dsKhachHang) {
			if(khach.getSoDT().equals(soDT)) {
				int ma=khach.getMaKh();
				for (int i=0;i<dsDatPhong.size();i++) {
					ThongTinDatPhong tt = dsDatPhong.get(i);
					
					if(tt.getMaKH()==ma&&(tt.getNgayCheckin().equals(a))) {
						tt.setTrangThai("Đã checkin");
					
						dsDatPhong.set(i, tt);
					}
				} 
			}
		}
		System.out.println("Đã checkin");
	}


		private static void hamThayDoiTrangThaiPhong(ThongTinDatPhong tt) {
			for (int i = 0; i < tt.maPhong.length; i++) {
				int ma =tt.maPhong[i];
				Phong p =layPhong(ma);
			}
			
		}


		private static void hienThiChucNang(){
			System.out.println("chọn chức năng muốn dùng");
			System.out.println("1.tra cưu thông tin khách hàng");
			System.out.println("2.tra cứu thông tin phòng");
			System.out.println("0.thoát");
		}
		private static void timThongTinPhong() {
		System.out.println("nhập ngày checkIn");
		String ngayCheckIn = sc.nextLine();
		taoDsPhong(ngayCheckIn);
	}
		private static void timThongTinKhach(int m) {
			
			for (KhachHang kh : dsKhachHang) {
				if(kh.getMaKh()==m) {
					System.out.println("Mã khách hàng: "+kh.getMaKh());
					System.out.println("Tên khách hàng: "+kh.getTen());
					System.out.println("Số điện thoại khách hàng: "+kh.getSoDT());
					System.out.println("Số chứng minh thư: "+kh.getSoCMND());
				}
			}
			for (ThongTinDatPhong tt : dsDatPhong) {
				if(tt.getMaKH()==m) {
					System.out.println("Danh sách phòng khách đã đặt");
					for (int i = 0; i < tt.getMaPhong().length; i++) {
						int ma = tt.getMaPhong()[i];
						Phong p=layPhong(ma);
						System.out.println("Mã phòng: "+p.getMaPhong());
						System.out.println("Tên phòng: "+p.getTenPhong());
						System.out.println("Kiểu phòng: "+p.getKieuPhong());
						System.out.println("Giá Tiền: "+p.getGiaTien());
					}
					System.out.println("Số ngày thuê "+tt.getSoNgayThue());
					System.out.println("Ngày CheckIn "+tt.getNgayCheckin());
					System.out.println("Trạng thái "+tt.getTrangThai());
				}
			}	
		}


		private static void timThongTinKhach() {
		System.out.println("nhập mã khách hàng");
		int maKH=sc.nextInt();
		for (KhachHang kh : dsKhachHang) {
			if(kh.getMaKh()==maKH) {
				System.out.println("Mã khách hàng: "+kh.getMaKh());
				System.out.println("Tên khách hàng: "+kh.getTen());
				System.out.println("Số điện thoại khách hàng: "+kh.getSoDT());
				System.out.println("Số chứng minh thư: "+kh.getSoCMND());
			}
		}
		for (ThongTinDatPhong tt : dsDatPhong) {
			if(tt.getMaKH()==maKH) {
				System.out.println("danh sách phòng khách đã đặt");
				for (int i = 0; i < tt.getMaPhong().length; i++) {
					int ma = tt.getMaPhong()[i];
					Phong p=layPhong(ma);
					System.out.println("Mã phòng: "+p.getMaPhong());
					System.out.println("Tên phòng: "+p.getTenPhong());
					System.out.println("Kiểu phòng: "+p.getKieuPhong());
					System.out.println("Giá Tiền: "+p.getGiaTien());
				}
				System.out.println("Số ngày thuê "+tt.getSoNgayThue());
				System.out.println("Ngày CheckIn "+tt.getNgayCheckin());
				System.out.println("Trạng thái "+tt.getTrangThai());
			}
		}
	}



		private static void hamLuuDatPhong() {
			FileOutputStream fileOut=null;
			ObjectOutputStream ojbOut=null;
			try {
				fileOut = new FileOutputStream(fileDP);
				ojbOut = new ObjectOutputStream(fileOut);
				ojbOut.writeObject(dsDatPhong);
			}catch(Exception e) {
				
			}finally {
				if(ojbOut!=null)
					try {
						ojbOut.close();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
			}
		
	}



		private static void hamLuuKhachHang() {
		FileOutputStream fileOut=null;
		ObjectOutputStream ojbOut=null;
		try {
			fileOut = new FileOutputStream(fileKH);
			ojbOut = new ObjectOutputStream(fileOut);
			ojbOut.writeObject(dsKhachHang);
		}catch(Exception e) {
			
		}finally {
			if(ojbOut!=null)
				try {
					ojbOut.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
	}



		private static void nhapThongTinDatPhong() throws java.text.ParseException {
		System.out.println("nhập tên khách hàng");
		String ten =sc.nextLine();
		System.out.println("nhập số điện thoại");
		String soDT=sc.nextLine();
		System.out.println("nhập số chứng minh nhân dân");
		String soCMND=sc.nextLine();
		KhachHang a = new KhachHang(ten,soDT,soCMND);
		dsKhachHang.add(a);
		int maKH=a.getMaKh();
		System.out.println("nhập loại phòng muốn thuê");
		String kieu=sc.nextLine();
		System.out.println("nhập ngày checkin(dd/MM/yyyy)");
		String ngayCheckIn=sc.nextLine();
		inDsPhongTrong(kieu,ngayCheckIn);
		System.out.println("nhập số phòng khách muốn thuê");
		int n=sc.nextInt();
		System.out.println("nhập số ngày khách muốn thuê");
		int soNgay=sc.nextInt();
		int maPhong[]= new int[n];
		for (int i=0 ;i<maPhong.length;i++) {
			System.out.println("nhập mã phòng thứ "+(i+1));
			maPhong[i]=sc.nextInt();
		}
		
		String a1="Chưa nhận phòng";
		ThongTinDatPhong tt= new ThongTinDatPhong(maKH, maPhong, soNgay, ngayCheckIn,a1);
		
		dsDatPhong.add(tt);
	}
		private static void nhapThongTinDatPhong2() throws java.text.ParseException {
			boolean kq = false;
			int maKH=0;
			
			System.out.println("nhập số chứng minh nhân dân");
			String soCMND=sc.nextLine();
			for (KhachHang k : dsKhachHang) {
				if(soCMND.equals(k.getSoCMND())) {
					kq=true;
					maKH=k.getMaKh();
				}
			}
			if(kq!=true) {
				System.out.println("nhập tên khách hàng");
				String ten =sc.nextLine();
				System.out.println("nhập số điện thoại");
				String soDT=sc.nextLine();
			
			KhachHang a = new KhachHang(ten,soDT,soCMND);
			dsKhachHang.add(a);
			 maKH=a.getMaKh();
			}
			System.out.println("nhập loại phòng muốn thuê");
			String kieu=sc.nextLine();
			System.out.println("nhập ngày checkin(dd/MM/yyyy)");
			String ngayCheckIn=sc.nextLine();
			inDsPhongTrong(kieu,ngayCheckIn);
			System.out.println("nhập số phòng khách muốn thuê");
			int n=sc.nextInt();
			System.out.println("nhập số ngày khách muốn thuê");
			int soNgay=sc.nextInt();
			int maPhong[]= new int[n];
			for (int i=0 ;i<maPhong.length;i++) {
				System.out.println("nhập mã phòng thứ "+(i+1));
				maPhong[i]=sc.nextInt();
			}
			
			String a1="Chưa nhận phòng";
			ThongTinDatPhong tt= new ThongTinDatPhong(maKH, maPhong, soNgay, ngayCheckIn,a1);
			
			dsDatPhong.add(tt);	
		}
		private static void taoDsPhong(String ngayCheckIn) {

			ArrayList<Phong> ds_3=new ArrayList<>();
			for (int i = 0; i < dsPhong.size(); i++) {
				Phong a=dsPhong.get(i);
				ds_3.add(a);
				
			}
			ArrayList<ThongTinDatPhong> ds_2=new ArrayList<>();
			for (ThongTinDatPhong p : dsDatPhong) {
				int ma[] =p.getMaPhong();
				for (int i = 0; i < ma.length; i++) {
					int m=ma[i];
					Phong p1 = layPhong(m);
					if(p1!=null) {
						ds_2.add(p);
					}
				}
			}
			ArrayList<Phong> ds_phongkin = new ArrayList<>();
			for (Phong p : ds_3) {
				int m=p.getMaPhong();
				for (ThongTinDatPhong tt : ds_2) {
					if(tt.check(m)) {
						if(tt.checkDate(ngayCheckIn))
						{
							ds_phongkin.add(p);
						}
					}
				}
			}
			ds_3.removeAll(ds_phongkin);
			hienThiDs(ds_3);
				
		}
		private static void inDsPhongTrong(String kieu, String ngayCheckIn) {
			ArrayList<Phong> ds_1=new ArrayList<>();
			for (int i = 0; i < dsPhong.size(); i++) {
				Phong a=dsPhong.get(i);
				if(a.getKieuPhong().equals(kieu)) {
					ds_1.add(a);
				}
			}
			ArrayList<ThongTinDatPhong> ds_2=new ArrayList<>();
			for (ThongTinDatPhong p : dsDatPhong) {
				int ma[] =p.getMaPhong();
				for (int i = 0; i < ma.length; i++) {
					int m=ma[i];
					Phong p1 = layPhong(m);
					if(p1.getKieuPhong().equals(kieu)&&p1!=null) {
						ds_2.add(p);
					}
				}
			}
			ArrayList<Phong> ds_phongkin = new ArrayList<>();
			for (Phong p : ds_1) {
				int m=p.getMaPhong();
				for (ThongTinDatPhong tt : ds_2) {
					if(tt.check(m)) {
						if(tt.checkDate(ngayCheckIn))
						{
							ds_phongkin.add(p);
						}
					}
				}
			}
			ds_1.removeAll(ds_phongkin);
			hienThiDs(ds_1);
				}
			
		

		
		private static Phong layPhong(int m) {
			for (Phong phong : dsPhong) {
				if(phong.getMaPhong()==m) {
					return phong;
				}
				
			}
			return null;
			
			
		}

		private static void docDsDatPhong() throws IOException, ClassNotFoundException {
			FileInputStream fileIn =null;
			ObjectInputStream ojbIn=null;
			
				fileIn = new FileInputStream(fileDP);
				ojbIn = new ObjectInputStream(fileIn);
				 Object a= ojbIn.readObject();
				 dsDatPhong=(ArrayList<ThongTinDatPhong>) a;
		
	}

		private static void docDsKhacHang() throws IOException, ClassNotFoundException {
		FileInputStream fileIn =null;
		ObjectInputStream ojbIn=null;
		
			fileIn = new FileInputStream(fileKH);
			ojbIn = new ObjectInputStream(fileIn);
			 Object a= ojbIn.readObject();
			 dsKhachHang=(ArrayList<KhachHang>) a;
		
		
	}

		private static void sapXepDs() {
		for (int i = 0; i < dsPhong.size()-1; i++) {
				Phong pI = dsPhong.get(i);
			for (int j = i+1; j < dsPhong.size(); j++) {
				Phong pJ = dsPhong.get(j);
				if(pI.giaTien>pJ.giaTien) {
					Phong p=pI;
					pI=pJ;
					pJ=p;
					dsPhong.set(i, pI);
					dsPhong.set(j, pJ);
				}
			}
			
		}
		
	}

		private static void hienThiDs() {
			System.out.format("%-5s %10s %10s %10s%n","Mã","Tên Phòng","Loại Phòng","Giá");
		for (Phong phong : dsPhong) {
			System.out.println(phong);
		}
		
	}
		private static void hienThiDs(ArrayList<Phong> dsP) {
			System.out.println("----------danh sách phòng trống-----------");
			System.out.format("%-5s %10s %10s %10s%n","Mã","Tên Phòng","Loại Phòng","Giá");
		for (Phong p : dsP) {
			System.out.println(p);
		}
		
	}
		private static void docFilePhong() {
		FileReader fileR = null;
		BufferedReader buffR = null;
		try {
			fileR = new FileReader(phongFile);
			buffR= new BufferedReader(fileR);
			String line;
			while((line=buffR.readLine())!=null) {
				
				if(line.startsWith("#"))continue;
				else {
					String[] data = line.split(";");
					int maPhong= Integer.parseInt(data[0]);
					String tenPhong=data[1];
					String kieuPhong=data[2];
					int giaTien=Integer.parseInt(data[3]);
					Phong p = new Phong(maPhong,tenPhong,kieuPhong,giaTien);
					dsPhong.add(p);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fileR!=null)
				try {
					fileR.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}
		
	}

		private static void hamHienThiMenu() {
		System.out.println("------------------------");
		System.out.println("Các chức năng của app");
		System.out.println("1.Hiển thị danh sách các phòng");
		System.out.println("2.Hiển thị danh sách các phòng theo giá tiền tăng dần");
		System.out.println("3.Đặt phòng");
		System.out.println("4.Tra cứu thông tin của khách hàng,của phòng");
		System.out.println("5.check in");
		System.out.println("6.check out và thanh toán");
		System.out.println("0.Thoát app");
	}
}
