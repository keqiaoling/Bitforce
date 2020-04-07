package com.example.myapplication.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by LG on 2017/6/16.
 */

public class MoneyUtils {
    /**
     * 金额0.00。
     */
    public static final BigDecimal ZERO = BigDecimal.valueOf(0.00);

    /**
     * 金额100.00。
     */
    public static final BigDecimal HUNDRED = new BigDecimal(100.00);

    private MoneyUtils() {
        super();
    }


    /**
     * 四舍五入保留1位小数（四舍六入五成双）。
     *
     * @param amount 金额。
     * @return  四舍五入保留1位小数后金额。
     */
    public static BigDecimal decimal0ByUp(BigDecimal amount) {
        return amount.setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }
    /**
     * 四舍五入保留1位小数（四舍六入五成双）。
     *
     * @param amount 金额。
     * @return  四舍五入保留1位小数后金额。
     */
    public static BigDecimal decimal1ByUp(BigDecimal amount) {
        return amount.setScale(1, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 四舍五入保留2位小数（四舍六入五成双）。
     *
     * @param amount 金额。
     * @return  四舍五入保留2位小数后金额。
     */
    public static BigDecimal decimal2ByUp(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 动态保留小数
     * @param decimal  保留小数的位数
     * @param amount
     * @return
     */
    public static BigDecimal decimalByUp(int decimal, BigDecimal amount) {
        return amount.setScale(decimal, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 四舍五入保留4位小数（四舍六入五成双）。
     *
     * @param amount 金额。
     * @return 四舍五入保留4位小数后金额。
     */
    public static BigDecimal decimal4ByUp(BigDecimal amount) {
        return amount.setScale(4, BigDecimal.ROUND_HALF_EVEN);
    }


    /**
     * 除法，四舍五入保留2位小数。
     *
     * @param divideAmount 金额。
     *  @param dividedAmount 被除的金额。
     * @return  四舍五入保留2位小数后金额。
     */
    public static BigDecimal divide2ByUp(BigDecimal divideAmount, BigDecimal dividedAmount) {
        return divideAmount.divide(dividedAmount,2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 除法，四舍五入保留4位小数。
     *
     * @param divideAmount 金额。
     *  @param dividedAmount 被除的金额。
     * @return  四舍五入保留2位小数后金额。
     */
    public static BigDecimal divide4ByUp(BigDecimal divideAmount, BigDecimal dividedAmount) {
        return divideAmount.divide(dividedAmount,4, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal divideByUp(BigDecimal divideAmount, int decimal, BigDecimal dividedAmount){
        return divideAmount.divide(dividedAmount, decimal, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 除法，四舍五入保留4位小数。
     *
     * @param divideAmount 金额。
     *  @param dividedAmount 被除的金额。
     * @return  四舍五入保留2位小数后金额。
     */
    public static BigDecimal divide5ByUp(BigDecimal divideAmount, BigDecimal dividedAmount) {
        return divideAmount.divide(dividedAmount,5, BigDecimal.ROUND_HALF_EVEN);
    }

    public static String div(String v1, String v2, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException("The scale must be a positive integer or ");

        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_EVEN).toString();//ROUND_UNNECESSARY ROUND_HALF_UP ROUND_DOWN

    }
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    /**
     * 是否大于0。
     *
     * @param amount 金额。
     * @return true：大于0；false；小于等于0。
     */
    public static boolean isGreaterThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 1;
    }

    /**
     * 是否小于0。
     *
     * @param amount 金额。
     * @return true：小于0；false：大于等于0。
     */
    public static boolean isLessThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == -1;
    }

    /**
     * 是否等于0。
     *
     * @param amount 金额。
     * @return true：等于0；false：大于小于0。
     */
    public static boolean isEqualZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }


    /**
     * 第一个是否大于第二个。
     *
     * @param first  金额。
     * @param second 金额。
     * @return true：first>second；false：first<=second。
     */
    public static boolean isGreaterThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) == 1;
    }

    /**
     * 第一个是否小于第二个。
     *
     * @param first  金额。
     * @param second 金额。
     * @return true：first<second；false：first >=second。
     */
    public static boolean isLessThan(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) == -1;
    }

    /**
     * 第一个是否等于第二个。
     *
     * @param first  金额。
     * @param second 金额。
     * @return true：first=second；false：first ><second。
     */
    public static boolean isEqual(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) == 0;
    }

    public static  BigDecimal POINT_BILLION = new BigDecimal("100000000");
    public static  BigDecimal TEN_THOUSANDS = new BigDecimal("10000");

    /**
     * 金额格式化为,###.00的金额,即保留两位小数。
     *
     * @param amount 需要显示成字符传,###.###的金额。
     * @return  返回String，格式为,###.###。
     */
    public static String formatAmountAsString(BigDecimal amount) {
        return amount != null ? new DecimalFormat(",##0.00").format(amount) : "0.00";
    }

    /**
     * 将格式化的字符串金额反转为{@link BigDecimal}。
     *
     * @param amount 格式为,###.###。
     * @return {@link BigDecimal}。
     */
    public static BigDecimal parseAmount(String amount) {
        return new BigDecimal(amount.replaceAll(",", ""));
    }

    /**
     * 截取小数点后4位
     * @param moeny
     * @return
     */
    public static String Intercept4Position(double moeny)
    {
        DecimalFormat formater = new DecimalFormat();
        formater.setMaximumFractionDigits(4);
        formater.setGroupingSize(0);
        formater.setRoundingMode(RoundingMode.FLOOR);
        return formater.format(moeny);
    }

    public static String removeScience(String moeny){

         if(moeny.contains("E")||moeny.contains("e")){
             BigDecimal db = new BigDecimal(moeny);
             return db.toPlainString();
         }

        return moeny;
    }


    public static String accuracy4Position(String moeny){
         if(moeny.indexOf(".")==-1){
             return moeny+".0000";
         }else{
             String interceptPoint[]=moeny.split("\\.");
             if(interceptPoint[1].length()==4){
                 return removeScience(moeny);
             }else{
                 for (int i=0;i<4-interceptPoint[1].length();i++){
                     moeny=moeny+"0";
                 }
                 return moeny;
             }
         }
    }

    /**
     * 换算成万
     * @param moeny
     * @return
     */
    public static String conversion(double moeny)
    {
        if(moeny<10000){
           return decimal2ByUp(new BigDecimal(moeny)).toString();
        }else {
            return decimal2ByUp(new BigDecimal(moeny/10000)).toString()+"万";
        }

    }


}
