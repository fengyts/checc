//package testjava;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import ng.bayue.util.DateUtils;
//
//public class TestJava {
//	
//	public static String formatDuring(long mss) {  
//	    long days = mss / (1000 * 60 * 60 * 24);  
//	    long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
//	    long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);  
//	    long seconds = (mss % (1000 * 60)) / 1000;  
//	    return days + " days " + hours + " hours " + minutes + " minutes "  
//	            + seconds + " seconds ";  
//	}
//	
//	public static String formatTime(long ms) {  
//        
//        int ss = 1;  
//        int mi = ss * 60;  
//        int hh = mi * 60;  
//        int dd = hh * 24;  
//
//        long day = ms / dd;  
//        long hour = (ms - day * dd) / hh;  
//        long minute = (ms - day * dd - hour * hh) / mi;  
//        long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
//        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;  
//
//        String strDay = day < 10 ? "0" + day : "" + day; //天  
//        String strHour = hour < 10 ? "0" + hour : "" + hour;//小时  
//        String strMinute = minute < 10 ? "0" + minute : "" + minute;//分钟  
//        String strSecond = second < 10 ? "0" + second : "" + second;//秒  
//        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒  
//        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;  
//         
//        String r = strDay + "天" + strHour + "时" + strMinute + "分钟 " + strSecond + "秒";  
//        System.out.println(r);
//        return r;
//} 
//	
//	public static void main(String[] args) throws Exception {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar calStart = Calendar.getInstance();
////		calStart.set(2017, 11, 13, 10, 0, 0);
//		String dateStr1 = "2017-12-13 11:31:26";
//		Date date1 = sdf.parse(dateStr1);
//		calStart.setTime(date1);
//		System.out.println(DateUtils.formatDateTime(calStart.getTime()));
//		
//		//2017-12-18 10:31:25
//		Calendar calEnd = Calendar.getInstance();
////		calEnd.set(2017, 11, 13, 11, 0, 0);
//		String dateStr = "2017-12-17 11:32:27";
//		Date date = sdf.parse(dateStr);
//		calEnd.setTime(date);
//		System.out.println(calEnd.getTimeInMillis());//1513481485000
//		System.out.println(DateUtils.formatDateTime(calEnd.getTime()));
//		
//		long start = calStart.getTimeInMillis();
//		long end = calEnd.getTimeInMillis();
//		long mss = end - start;//351084983
//		mss = mss/1000;
//		System.out.println(mss);
//		
////		String str = formatDuring(mss);
////		System.out.println(str);
//		
//		for(; mss >0;mss--){
//			Thread thread = new Thread();
//			thread.sleep(1000);
//			formatTime(mss);
//		}
//	}
//
//}
