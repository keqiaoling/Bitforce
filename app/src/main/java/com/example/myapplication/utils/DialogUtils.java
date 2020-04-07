package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;


/**
 * Created by LG on 2017/6/14.
 */

public class DialogUtils {


    private static View viewLoading;

    private static TextView textView;

    private static Dialog mDialogLoading;
    /**
     * 等待的对话框
     */
    public static void showDialogLoading(Context context){
       /* if(mDialogLoading!=null){

           mDialogLoading.show();

        }else{*/
            mDialogLoading=new Dialog(context, R.style.Dialog);

            mDialogLoading.setContentView(setViewLoading(context));
            mDialogLoading.show();
        //}
    }
    public static void showDialogLoading(Context context,String text){
       /* if(mDialogLoading!=null){

           mDialogLoading.show();

        }else{*/
        textView.setText(text);
        mDialogLoading=new Dialog(context,R.style.Dialog);

        mDialogLoading.setContentView(setViewLoading(context));
        mDialogLoading.show();
        //}
    }
    /**
     * 关闭等待对话框
     */
    public static void dismissDialogLoading(){
        if(mDialogLoading!=null){
            mDialogLoading.dismiss();
        }
    }

    /**
     * 等待对话框的view
     * @param context
     * @return
     */
   public static View setViewLoading(Context context){
       viewLoading=LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
       textView = viewLoading.findViewById(R.id.tv_prompt);
       /*ImageView ivLoading= (ImageView) viewLoading.findViewById(R.id.iv_loading);
       Glide.with(context).load(R.drawable.wait).asGif().into(ivLoading);*/
       return viewLoading;
   }

   public static Dialog getBottomDialog(){
        return null;
   }



}
