package com.doan.MyWechat.com.untils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Until {
	static public Timestamp getDateTimeNow() {

		LocalDateTime now = LocalDateTime.now() ;
		Timestamp dateSql = Timestamp.valueOf(now);
		return dateSql;
	}
	static public Timestamp format(Timestamp timestamp) {
		SimpleDateFormat spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strTime = spf.format(timestamp);
		return Timestamp.valueOf(strTime);
		
	}
	public static Object compareTimeWithNow(LocalDateTime lcCreate) {
		Timestamp now = Until.getDateTimeNow();
		LocalDateTime lcDateTimeNow = now.toLocalDateTime();
		if((lcDateTimeNow.getYear()-lcCreate.getYear())>0) {
			return lcCreate.getDayOfMonth()+" tháng "+lcCreate.getMonthValue()+" "+lcCreate.getYear();
		}else if((lcDateTimeNow.getMonthValue()-lcCreate.getMonthValue())>0) {
			return lcDateTimeNow.getMonthValue()-lcCreate.getMonthValue()+ " tháng trước";
		}else if((lcDateTimeNow.getDayOfMonth()-lcCreate.getDayOfMonth())>0) {
			return lcDateTimeNow.getDayOfMonth()-lcCreate.getDayOfMonth()+ " ngày trước";
		}else if((lcDateTimeNow.getHour()-lcCreate.getHour())==1&&60+lcDateTimeNow.getMinute()-lcCreate.getMinute()>0&&60+lcDateTimeNow.getMinute()-lcCreate.getMinute()<60) {
			return 60+lcDateTimeNow.getMinute()-lcCreate.getMinute()+ " phút trước";
		}else if((lcDateTimeNow.getHour()-lcCreate.getHour())>0) {
			return lcDateTimeNow.getHour()-lcCreate.getHour()+ " giờ trước";
		}	
		else if((lcDateTimeNow.getMinute()-lcCreate.getMinute())>0) {
			return lcDateTimeNow.getMinute()-lcCreate.getMinute()+ " phút trước";
		}else if((lcDateTimeNow.getSecond()-lcCreate.getSecond())>0) {
			return lcDateTimeNow.getSecond()-lcCreate.getSecond()+ " giây trước";
		}else {
			return "Vừa xong";
		}
	}
	
}
