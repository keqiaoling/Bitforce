package com.example.myapplication.utils;

/** 响应码处理工具类
 * Created by LG on 2017/6/13.
 */

public class MessagDealUtils {


    /**
     * 处理响应码，把响应码变成中文并且返回出去给ui界面去提示
     */
    public static String sendAuthCodeMessage(String hint,String resultCode){
        String messag="";
        switch (resultCode){
            case "101":
                messag="请输入"+hint;
                break;
            case "102":
                messag="非会员"+hint;
                break;
            case "103":
                messag="您还没绑"+hint+"";
                break;
            case "4":
                messag= hint + "已被注册";
                break;
            case "105":
                messag=hint+"验证码错误次数过多，请两小时后再试！";
                break;
            case "3":
                messag="图形验证码错误！";
                break;
            case "2":
                messag="不是正确的手机或邮箱";
                break;
            case "5":
                messag="账号不存在";
                break;
            case "6":
                messag="已被禁用，联系管理员";
                break;
            case "1":
                messag="非法操作";
                break;
        }
        if(messag.equals("")){
            messag="发送证码失败";
        }
        return messag;
     }

    /**
     * 二次验证错误码判断
     * @param resultCode
     * @return
     */
    public static String loginValidateMessage(String resultCode){
        String messag="";
        switch (resultCode){
            case "1":
                messag = "用户不存在";
                break;
            case "100":
                messag = "验证码未发送";
                break;
            case "101":
                messag = "操作频繁，2小时候再试";
                break;
            case "102":
                messag = "验证码不正确";
                break;

                //之前遗留
            case "-15":
                messag="验证码为空";
                break;
            case "-20":
                messag="网络异常,请稍后重试";
                break;
            case "110":
                messag="验证码发送次数过多";
                break;
            case "111":
                messag="验证码错误次数过多，请2小时后在试！";
                break;
        }
        if(messag.equals("")){
            messag="网络异常,请稍后重试";
        }
        return messag;
    }

    /**
     * 交易提示语
     * @param resultCode
     * @return
     */
    public static String transactionMessage(String resultCode, String value){
        String messag="";
        switch (resultCode){
            case "-1":
                messag="最小交易数量为：" + value;
                break;
            case "-3":
                messag="最小总额为：" + value;
                break;
            case "-35":
                messag="最大交易额限制：" + value;
                break;
            case "-100":
            case "-101":
                messag="未开放交易";
                break;
            case "-400":
                messag="不在交易时间！";
                break;
            case "-4":
                messag="余额不足！";
                break;
            case "-50":
                messag="交易密码不能为空";
                break;
            case "-2":
                messag="密码错误";
                break;
            case "-200":
                messag="用户钱包异常";
                break;
            case "-900":
                messag="交易价格超出限定范围！";
                break;
        }
        if(messag.equals("")){
            messag="网络异常";
        }
        return messag;
    }

    /**
     * 提现币种提示语
     * @param resultCode
     * @return
     */
    public static String withdrawBtcMessage(String resultCode, String data){
        String messag="";
        switch (resultCode){
            case "2":
                messag="最小提现数量为" + data;
                break;
            case "3":
                messag="请完成高级实名认证提升提币额度";
                break;
            case "5":
                messag="您的余额不足";
                break;
            case "6":
                messag="手续费不足";
                break;
            case "7":
                messag="每日虚拟币提现最多5次，请明天提现";
                break;
            case "106":
                messag="验证码错误多次，请2小时后再试！";
                break;
            case "107":
                messag="验证码错误！您还有2次机会";
                break;
            case "4":
                messag="该币种暂不支持提现";
                break;
            case "1":
                messag="该操作需要认证证件照片，请到手机【财务-安全验证】中进行上传审核！";
                break;
            case "9":
                messag="交易密码错误";
                break;
            case "10":
                messag="谷歌验证码错误";
                break;
            case "100":
                messag="未发送验证码";
                break;
            case "101":
                messag="操作频繁";
                break;
            case "102":
                messag="验证码不正确,剩余次数" + data;
                break;
        }
        if(messag.equals("")){
            messag="提现失败";
        }
        return messag;
    }

    /**
     * 设置地址提示语
     * @param resultCode
     * @return
     */
    public static String  modifyWithdrawBtcAddr(String resultCode, String data){
        String mssage="";
        switch (resultCode){
            case "100":
                mssage="您还没发送验证码！";
                break;
            case "101":
                mssage="验证码错误多次，请2小时后再试！";
                break;
            case "102":
                mssage="验证码错误！，你还有"+data+"次机会";
                break;
            case "104":
                mssage="不能绑定平台内提现地址！";
                break;
            case "106":
                mssage="最多只能绑定5个提现地址";
                break;
        }

        if(mssage.equals("")){
            mssage="绑定提现地址失败！";
        }
        return mssage;
    }

    /**
     * 人民币提示语
     * @param resultCode
     * @return
     */
    public static String withdrawRmbMessage(String resultCode){
        String messag="";
        switch (resultCode){
            case "101":
                messag="最小提现金额为100元";
                break;
            case "102":
                messag="最大提现金额为50000元";
                break;
            case "103":
                messag="您的余额不足！";
                break;
            case "104":
                messag="请先设置您的提现银行卡信息";
                break;
            case "105":
                messag="交易密码错误";
                break;
            case "107":
                messag="每日人民币提现最多7次，请明天提现";
                break;
            case "110":
                messag="验证码错误多次，请2小时后再试！";
                break;
            case "111":
                messag="验证码错误！您还有7次机会";
                break;
        }
        if(messag.equals("")){
            messag="提现失败";
        }
        return messag;
    }

    /**
     * 人民币提示语
     * @param resultCode
     * @return
     */
    public static String transferCoinMessage(String resultCode){
        String messag="";
        switch (resultCode){
            case "201":
                messag="不支持该系统的资金转移";
                break;
            case "202":
                messag="网络异常,请稍后重试";
                break;
            case "203":
                messag="资金转移不能少于0.0001";
                break;
            case "204":
                messag="交易密码错误";
                break;
            case "205":
                messag="不支持改虚拟币资金转移";
                break;
            case "206":
                messag="您还不支持资金转移，请联系客服";
                break;
            case "207":
                messag="您的余额不足";
                break;
            case "210":
                messag="验证码过期或者验证不正确";
                break;
        }
        if(messag.equals("")){
            messag="网络异常，请稍后重试";
        }
        return messag;
    }

}
