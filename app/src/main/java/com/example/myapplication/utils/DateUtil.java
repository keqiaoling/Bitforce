package com.example.myapplication.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by LG on 2017/8/23.
 */

public class DateUtil {

    public static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /***
            * 获取当前java.util.Date型日期
    *
            * @return java.util.Date() 当前日期
    **/
    public static Date getCurrentJavaUtilDate() {

        return new Date();
    }

    /***
            * 按特定的日期格式获取当前字符串型日期
    *
            * @param dateFormatType
    *            String，日期格式<br>
    *            几种日期格式和测试的结果<br>
    *            "yyyy-MM-dd": 2012-08-02<br>
    *            "yyyy-MM-dd hh:mm:ss": 2012-08-02 11:27:41<br>
    *            "yyyy-MM-dd hh:mm:ss EE": 2012-08-02 11:27:41 星期四<br>
    *            "yyyy年MM月dd日 hh:mm:ss EE": 2012年08月02日 11:27:41 星期四<br>
    * @return String 当前字符串型日期
    **/
    public static String getCurrentStrDateBySpecifiedFormatType(
            String dateFormatType) {

        SimpleDateFormat simformat = new SimpleDateFormat(
                dateFormatType);
        return simformat.format(new Date());
    }

    /***
            * 判断今天是不是周末
    *
            * @return true/false
            **/
    public static boolean isTodayWeekend() {

        Calendar c = Calendar.getInstance(); // 获取当前日期
        int day = c.get(Calendar.DAY_OF_WEEK); // 获取当前日期星期，英国算法(周日为一周第一天)
        if (day == 7 || day == 1) { // 如果是周六或周日就返回true
            return true;
        }
        return false;
    }




    /***
            * 将java.util.Date型日期转化指定格式的字符串型日期
    *
            * @param javaUtilDate
    *            java.util.Date,传入的java.util.Date型日期
    * @param dateFormatType
    *            "yyyy-MM-dd"或者<br>
    *            "yyyy-MM-dd hh:mm:ss EE"或者<br>
    *            "yyyy年MM月dd日 hh:mm:ss EE" <br>
    *            (年月日 时:分:秒 星期 ，注意MM/mm大小写)
            * @return String 指定格式的字符串型日期
    **/
    public static String turnJavaUtilDateToStrDate(Date javaUtilDate,
                                                   String dateFormatType) {

        String strDate = "";
        if (javaUtilDate != null) {

            SimpleDateFormat sdf = new SimpleDateFormat(dateFormatType);
            strDate = sdf.format(javaUtilDate);
        }
        return strDate;
    }




    /***
            * 获取当前月第一天的字符串日期
    *
            * @return String 当前月第一天的字符串日期。例如：当前日期是2012-08-2，则返回值为2012-08-1
            **/
    public static String getTheFirstDayOfCurrentMonth() {

        Calendar cal = Calendar.getInstance();// 获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,即是本月第一天
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 格式化
        return df.format(cal.getTime());
    }

    /***
            * 获取当前月最后一天的字符串日期
    *
            * @return String 当前月最后一天的日期。 例如：当前日期是2012-08-2，则返回值为2012-08-31
            **/
    public static String getTheLastDayOfCurrentMonth() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//
        Calendar cal = Calendar.getInstance();// 获取当前日期，例如2012-08-02
        cal.set(Calendar.DAY_OF_MONTH, 1);// 转变为当前月的第一天，例如2012-08-01
        cal.add(Calendar.MONTH, 1);// 转变为下个月的第一天，例如2012-09-01
        cal.add(Calendar.DAY_OF_MONTH, -1);// 下个月第一天，倒数一天，即为当前月的最后一天。例如2012-08-31
        return df.format(cal.getTime());
    }



    /***
    * 获取当前时间16位字符串
    * 小富修改为时间16位+4位随机数2012091811320043154
    * @return String e.g."2012082110290016"
    **/
    public static String getCurrentDateTime16Str() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssss");

        /** 生成随机数 **/
        int[] array = {0,1,2,3,4,5,6,7,8,9};
        Random rand = new Random();
        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }
        int result = 0;
        for(int i = 0; i < 4; i++){
            result = result * 10 + array[i];
        }

        return sdf.format(new Date())+result;
    }


   /***
    * 指定日期、相加月数值、格式，得到相加日期
    * 例如：2011-06-19  2  yyyy-MM-dd 结果：2011-08-19
    *      2011-06-19 12  yyyy-MM-dd 结果：2012-06-19
    * @param date 指定日期
    * @param formtStr 格式
    * @param number 数组
    * @param calender 指定修改日期格式数组
    * @author leiyunshi
    * @return **/

    public static String tragetDate(String date,String formtStr,int number,int calender)
    {
        if(date == null)
        {
            return null;
        }

        if(date.trim().equals(""))
        {
            return null;
        }
        DateFormat df=new SimpleDateFormat(formtStr);//"yyyy-MM-dd"

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtil.toDate(date));
        cal.add(calender, number);
//      System.out.println(df.format(cal.getTime()));
        return df.format(cal.getTime());
    }

   /***
    * 按照"yyyy-MM-dd"格式将字符串date转换为日期对象
    * @param date
    * @author leiyunshi
    * @return
     **/
    public static Date toDate(String date)
    {
        if (date == null) {
            return null;
        }
        if (date.trim().equals("")) {
            return null;
        }
        SimpleDateFormat simformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simformat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /***
            * 取得两个时间段的时间间隔
    * @author color
    * @param t1 时间1
    * @param t2 时间2
    * @return t2 与t1的间隔年数
    * @throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
    **/
    public static int getBetweenYears(String t1,String t2) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if(c1.after(c2)){
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);

        return betweenYears;
    }

    /***
            * 取得两个时间段的时间间隔
    * @author color
    * @param t1 时间1
    * @param t2 时间2
    * @return t2 与t1的间隔天数
    * @throws ParseException 如果输入的日期格式不是0000-00-00 格式抛出异常
    **/
    public static int getBetweenDays(String t1, String t2)
            throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int betweenDays = 0;
        Date d1 = format.parse(t1);
        Date d2 = format.parse(t2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        // 保证第二个时间一定大于第一个时间
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(d1);
        }
        int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i < betweenYears; i++) {
            c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
            betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
        }
        return betweenDays;
    }

    public static boolean timerDifference(String startTimer,String endTimer)
    {
         SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
         Date startDate;
         Date endDate;
        try {
            startDate=sf.parse(startTimer);
            endDate=sf.parse(endTimer);
            if(startDate.getTime()-endDate.getTime()>0){
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
       return false;
    }

    public static String getWeekDays(Date date){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }


}
