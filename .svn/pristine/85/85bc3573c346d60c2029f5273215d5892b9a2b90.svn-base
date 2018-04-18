package com.easybuy.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	
	private TimeUtil(){		
	}
	
	private static Timestamp time=null;
	public static Timestamp getDateTime(Date date){	
		//将时间格式转换成符合Timestamp要求的格式.
		String formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		//把时间转换成Timestamp类型
		time =Timestamp.valueOf(formatTime);
		return time;	
	}
	
	
	
	
	
	
	
}
