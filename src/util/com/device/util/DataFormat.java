package com.device.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DataFormat {
	private static SimpleDateFormat compactDate = new SimpleDateFormat(
			"yyyyMMdd");

	private static SimpleDateFormat ordDate = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat ordSecond = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat chDate = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");

	private static SimpleDateFormat compactHmSecond = new SimpleDateFormat(
			"HHmmss");

	private static SimpleDateFormat yearDate = new SimpleDateFormat("yyyy");

	private static SimpleDateFormat onlyDate = new SimpleDateFormat("MM-dd");

	private static SimpleDateFormat sqlDate = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");
	
	private static SimpleDateFormat sogouDate = new SimpleDateFormat(
	"yyyy-MM-dd HH:mm");

	public static Date parseDate(String strDate) {
		Date newDate = new Date();
		try {
			newDate = sqlDate.parse(yearDate(newDate) + "/" + strDate);
		} catch (Exception e) {
		}
		return newDate;
	}
	
	public static Date parseSogouDate(String strDate){
		Date newDate = null;
		try {
			newDate = sogouDate.parse(strDate);
		} catch (Exception e) {
		}
		return newDate;
	}

	public static String chDate(java.sql.Date srcDate) {
		return chDate.format(new Date(srcDate.getTime()));
	}

	public static String yearDate(Date srcDate) {
		return yearDate.format(srcDate);
	}

	public static String compactDate(Date srcDate) {
		return compactDate.format(srcDate);
	}

	public static String ordDate(Date srcDate) {
		if (srcDate == null) {
			return null;
		}
		String retDate = "1970-01-01";
		try {
			retDate = ordDate.format(srcDate);
		} catch (Exception e) {
		}
		return retDate;
	}

	public static String onlyDate(Date srcDate) {
		if (srcDate == null) {
			return null;
		}
		return onlyDate.format(srcDate);
	}

	public static String ordSecond(Date srcDate) {
		return ordSecond.format(srcDate);
	}

	public static String compactHmSecond(Date srcDate) {
		return compactHmSecond.format(srcDate);
	}

	public static SimpleDateFormat getCompactDate() {
		return compactDate;
	}

	public static void setCompactDate(SimpleDateFormat compactDate) {
		DataFormat.compactDate = compactDate;
	}

	public static SimpleDateFormat getCompactHmSecond() {
		return compactHmSecond;
	}

	public static void setCompactHmSecond(SimpleDateFormat compactHmSecond) {
		DataFormat.compactHmSecond = compactHmSecond;
	}

	public static SimpleDateFormat getOrdDate() {
		return ordDate;
	}

	public static void setOrdDate(SimpleDateFormat ordDate) {
		DataFormat.ordDate = ordDate;
	}

	public static SimpleDateFormat getOrdSecond() {
		return ordSecond;
	}

	public static void setOrdSecond(SimpleDateFormat ordSecond) {
		DataFormat.ordSecond = ordSecond;
	}
	
	public static String formatMoney(Double input){
		if(input == null) return "--";
		if(input.doubleValue() < -999998) return "--";
		return String.format("%.4f", input);
	}
	
	public static String formatMoney2(Double input){
		if(input == null) return "--";
		if(input.doubleValue() < -999998) return "--";
		return String.format("%.2f", input);
	}
	
	public static String formatMoney2(Object input){
		if(input == null) return "--";
		double db = 0;
		if(input instanceof BigDecimal ){
			db = ((BigDecimal)input).doubleValue();
		}else if(input instanceof Double){
			db = ((Double)input).doubleValue();
		}else {
			return "--";
		}
		if(db < -999998) return "--";
		return String.format("%.2f", db);
	}
	
	/**
	 * 带放大倍数的
	 * @param input
	 * @param fangda
	 * @return
	 */
	public static String formatMoney2(Object input,double fangda){
		if(input == null) return "--";
		double db = 0;
		if(input instanceof BigDecimal ){
			db = ((BigDecimal)input).doubleValue();
		}else if(input instanceof Double){
			db = ((Double)input).doubleValue();
		}else {
			return "--";
		}
		if(db < -999998) return "--";
		db = db*fangda;
		return String.format("%.2f", db);
	}
	
	public static String formatMoney(Object input,double fangda){
		if(input == null) return "--";
		double db = 0;
		if(input instanceof BigDecimal ){
			db = ((BigDecimal)input).doubleValue();
		}else if(input instanceof Double){
			db = ((Double)input).doubleValue();
		}else {
			return "--";
		}
		if(db < -999998) return "--";
		db = db*fangda;
		return String.format("%.0f", db);
	}
	
	public static String formatInteger2(Object input,double fangda){
		if(input == null) return "--";
		double db = 0;
		if(input instanceof BigDecimal ){
			db = ((BigDecimal)input).doubleValue();
		}else if(input instanceof Double){
			db = ((Double)input).doubleValue();
		}else {
			return "--";
		}
		if(db < -999998) return "--";
		db = db*fangda;
		return String.format("%.0f", db);
	}
	
	
	public static String formatPercent(Double input){
		if(input == null) return "--";
		return String.format("%.2f%%", input);
	}
	
	public static String formatPercent(Object input){
		if(input == null) return "--";
		double db = 0;
		if(input instanceof BigDecimal ){
			db = ((BigDecimal)input).doubleValue();
		}else if(input instanceof Double){
			db = ((Double)input).doubleValue();
		}else {
			return "--";
		}
		if(db < -999998) return "--";
		return String.format("%.2f%%", db);
	}
	
	public static String formatPercentWithColor(Object input){
		if(input == null) return "--";
		double db = 0;
		if(input instanceof BigDecimal ){
			db = ((BigDecimal)input).doubleValue();
		}else if(input instanceof Double){
			db = ((Double)input).doubleValue();
		}else {
			return "--";
		}
		if(db < -999998) return "--";
		if(db > 0 ){
			return String.format("<span class=red >%.2f%%</span>", db);
		}else if(db < 0){
			return String.format("<span class=green >%.2f%%</span>", db);
		}else{
			return String.format("%.2f%%", db);	
		}
	}
	
	
	public static String formatDate(Date srcDate){
		if (srcDate == null) {
			return "--";
		}
		String retDate = "1970-01-01";
		try {
			retDate = ordDate.format(srcDate);
		} catch (Exception e) {
		}
		return retDate;
	}
	
	/**
	 * 
	 * @param yyyy_mm_dd
	 * @return
	 */
	public static Date getYYYY_MM_DDDate(String yyyy_mm_dd)
	{
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 
		 Date date = null;
		try {
			date = format.parse(yyyy_mm_dd);
		} catch (ParseException e) {
            date = new Date();
			e.printStackTrace();
		}
		 return date;
		
	}
	
	/**
	 * 给指定的日期增加多少小时
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHour(Date date,int hours)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.HOUR_OF_DAY, hours);
		
		return cal.getTime();
	}
	
	/**
	 * 给指定的日期增加多少分钟
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addMinute(Date date,int minute)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		
		cal.add(Calendar.MINUTE, minute);
		
		return cal.getTime();
	}

	public static void main(String[] args) {
		System.out.println(DataFormat.formatMoney2(new Double(234234234.23), 1/100f));
		System.out.println(23432432f * (1f/100f));
	}
}
