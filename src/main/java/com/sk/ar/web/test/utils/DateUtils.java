package com.sk.ar.web.test.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public final static String DF_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DF_YMD_PATTERN = "yyyy-MM-dd";


    public static Date stringToDate(String yyyymmdd) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = new Date();
        try {
            to = transFormat.parse(yyyymmdd);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return to;
    }

    public static Date stringToDate2(String yyyymmdd) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to = new Date();
        try {
            to = transFormat.parse(yyyymmdd);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return to;
    }

    public static Date returnNowDate() {
        Date today = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //today = sdf.parse()
        return today;
    }

    public static String dateToString(Date yyyymmddhhmmss) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.format(yyyymmddhhmmss);
    }

    public static String todayToStr() {
        Date today = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.format(today);
    }

    /**
     * 오늘이 두개 비교날짜 사이에 있거나 두날짜보다 작으면 true, 오늘이 두개 날짜보다 전부 크면 false
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBetweenDateFromToday(String startDate, String endDate) throws Exception {
        String today = todayToStr();    //현재날짜String
        Date currentDate; //현재날짜 Date
        Date date2; //시작일
        Date date3; //종료일

        DateFormat df1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        date2 = df1.parse(startDate);
        date3 = df1.parse(endDate);
        currentDate = df1.parse(today);

        int compare = currentDate.compareTo(date2);
        int compare2 = currentDate.compareTo(date3);

        if (compare == 1 && compare2 ==1) {
            return false;
        }
        return true;
    }

    public static String todayToStrKor() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String yyyy =  sdf.format(today);
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        String mm =  sdf2.format(today);
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd");
        String dd =  sdf3.format(today);

        System.out.println(yyyy);
        return yyyy + "년 " + mm + "월 " + dd + "일";
    }

    public static String getYearLastTwo(){
        Date date = new Date();
        SimpleDateFormat formatter =new SimpleDateFormat("yy", Locale.KOREA);
        return formatter.format(date);
    }

    public static String returnNow() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(today);
    }

    public static String returnNowDateByYyyymmddhhmmss() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(today);
    }

    public static String returnNowDateByYYmmddhhmmss() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyMMddHHmmss");
        return sdf.format(today);
    }

    public static String returnNowDateByYyyymmddhhmm() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.format(today);
    }

    public static String returnToDate() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(today);
    }

    /**
     * 일년 전 오늘 리턴
     * @return
     */
    public static String returnToOneYearsAgo(){

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(cal.getTime());

    }

    /**
     * 현재일 기준 주차 계산
     * @return
     */
    public static String returnToPlusAndMinusWeek(Integer paramWeek){

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_MONTH, paramWeek);

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(cal.getTime());

    }

    /**
     * 현재 년도를 리턴
     * @return
     */
    public static String getNowYear() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy");
        return sdf.format(today);
    }

    /**
     * <pre>
     * 1. Comment : yyyy-mm-dd 형식의 값으로 더한 날짜를 구하기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2016. 07. 26
     * </pre>
     * @param yyyy_mm_dd
     * @param type
     * @param plusDay
     * @return type:YYYY => yyyy, type:MM-DD => MM-dd, type:YYYYMMDD => yyyy-MM-dd)
     */
    public static String plusDay(String yyyy_mm_dd, String type, int plusDay) {
        String plusDate = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(yyyy_mm_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, plusDay);
        String strDate = dateFormat.format(cal.getTime());
        String[] str = strDate.split("-");
        if ("MM-DD".equals(type)) {
            plusDate = str[1]+"-"+str[2];
        } else if ("YYYY".equals(type)) {
            plusDate = str[0];
        } else if ("YYYYMMDD".equals(type)) {
            plusDate = strDate;
        } else if ("MM.DD".equals(type)) {
            plusDate = str[1]+"."+str[2];
        }
        return plusDate;
    }

    /**
     * 현재 날짜를 리턴
     * @param dateType 날짜형식(ex : yyyy-MM-dd, yyyyMMdd ....)
     * @return
     */
    public static String returnToDate(String dateType) {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(dateType);
        return sdf.format(today);
    }

//    /**
//     * yyyy-mm-dd -> yyyymmdd 변환
//     */
//    public static String convertDateFormat(String date) throws Exception {
//        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyymmdd");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//        Date originDate = fromDateFormat.parse(date);
//        String newDate = dateFormat.format(originDate);
//        return newDate;
//    }

    /**
     * yyyymmdd ==> yyy-mm-dd 변환
     */
    public static String convertDateFormat(String date) {
        String newDate = "";
        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyymmdd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date originDate = fromDateFormat.parse(date);
            newDate = dateFormat.format(originDate);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return newDate;
    }

    public static Date convertDateTimeFormat(String yyyymmdd) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date to = new Date();
        try {
            to = transFormat.parse(yyyymmdd);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return to;
    }

    public static String convertDateFormat2(String date) {
        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String newDate = null;
        try {
            Date originDate = fromDateFormat.parse(date);
            newDate = dateFormat.format(originDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String convertDateFormat3(String date) {
        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        String newDate = null;
        try {
            Date originDate = fromDateFormat.parse(date);
            newDate = dateFormat.format(originDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 요일구하기
     * @param date
     * @return
     */
    public static String getDayOfWeek(String date) {
        String day = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String[] week = {"일","월","화","수","목","금","토"};
        Calendar cal = Calendar.getInstance();
        Date getDate;
        try {
            getDate = format.parse(convertDateFormat(date));
            cal.setTime(getDate);
            int w = cal.get(Calendar.DAY_OF_WEEK)-1;
            day = week[w];
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * yyyy-mm-dd HH:mm:ss > 초단위 자르기
     */
    public static String splitSecondFromDateTime(String dateTime) {
        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Date originDate = new Date();
        try {
            originDate = fromDateFormat.parse(dateTime);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return dateFormat.format(originDate);
    }

    public static String getNowTime() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(today);
    }

    public static String addSecond(String dateTime, Integer millisecond) {
        Date addDateTime = null;
        if (dateTime != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date standardDateTime = sdf.parse(dateTime);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(standardDateTime);
                calendar.add(Calendar.SECOND, millisecond);
                addDateTime = calendar.getTime();

                return dateToString(addDateTime);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 현재 시간보다 비교시간이 미래이면 true
     * @param afterDate
     * @return
     * @throws ParseException
     */
    public static boolean isAfterNowTime(String afterDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = sdf.parse(returnNow());
        Date after = sdf.parse(afterDate);

        boolean isAfter = after.after(now);
        return isAfter;
    }

    /**
     * yyyy-MM-dd HH:mm:ss >> AM, PM 시:분 으로 변경
     * @param time
     * @return
     * @throws Exception
     */
    public static String convertAmPmTimeFromDateTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("a hh:mm", new Locale("en"));
        Date originTime = new Date();
        try {
            originTime = sdf.parse(time);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        String newDate = sdf2.format(originTime);
        return newDate;
    }

    public static String addMonth(String ymd, int plusDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = sdf.parse(ymd);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, plusDay);
        String strDate = sdf.format(cal.getTime());
        return strDate;
    }

    public static String getNowMonth() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String month =  sdf.format(today);
        return month;
    }

    public static String getNowDay() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String day =  sdf.format(today);
        return day;
    }

    public static String getNowMonthLastDay(String type) {
        Calendar cal = Calendar.getInstance();
        Integer year = Integer.parseInt(getNowYear());
        Integer month = Integer.parseInt(getNowMonth());
        Integer day = Integer.parseInt(getNowDay());
        cal.set(year, month-1, day); //월은 -1해줘야 해당월로 인식

        String monthLastDay = null;
        if ("COMMA".equals(type)) {
         monthLastDay = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH)+1) + "." + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        } else {
            monthLastDay = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        return monthLastDay;

    }

    public static long day2Day(String startDate, String endDate, String format) {
        if (format == null)
            format = "yyyy/MM/dd HH:mm:ss.SSS";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date sDate;
        Date eDate;
        long day2day = 0;
        try {
            sDate = sdf.parse(startDate);
            eDate = sdf.parse(endDate);
            day2day = (eDate.getTime() - sDate.getTime())
                    / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return day2day;
    }

    //밀리세컨 > yyyy-MM-dd HH:mm:ss 변환
    public static String convertDateByMilliSeconds(Long millisecond) {
        String timeInFormat = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date timeInDate = new Date(millisecond);
            timeInFormat = sdf.format(timeInDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeInFormat;
    }

    /**
     * Date형식 체크
     * @param date
     * @param format
     * @return
     */
    public static boolean dateCheck(String date, String format) {
        SimpleDateFormat dateFormatParser = new SimpleDateFormat(format, Locale.KOREA);
        dateFormatParser.setLenient(false);
        try {
            dateFormatParser.parse(date);
            return true;
        } catch (Exception Ex) {
            return false;
        }
    }

    public static String returnNowMonth() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(today);
    }

    public static String returnMonthByPlusMonthParam(String ym, int plusMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();

        try {
            date = sdf.parse(ym);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, plusMonth);
        String strDate = sdf.format(cal.getTime());
        return strDate;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(stringToDate2(convertDateFormat("20210101") + " 23:59:59"));

    }
}
