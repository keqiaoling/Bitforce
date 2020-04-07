package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * sharedPreferences的工具类
 */
public class SharedPreferencesUtils {
    private static final String FILE_NAME = "app_data";
    public static final String REFUSE_MESSAGE = "refuse_message";
    public static final String TOP_MESSAGE = "top_message";
    public static final String TEMPLATE_SELECT = "template_select";
    public static final String QUICK_ANSWER = "quick_answer";
    public static final String USER_INFO = "user_info";
    public static final String USER_RESUME = "user_resume";
    private static SharedPreferences mSharedPreferences;// 单例
    private static SharedPreferencesUtils instance;// 单例
    private SharedPreferences newsSharedPrefrences;

    private SharedPreferencesUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        newsSharedPrefrences = context.getSharedPreferences("news_data",
                Context.MODE_PRIVATE);


        //利空利好最多存2000条，多于2000条吧之前的删了
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map map = newsSharedPrefrences.getAll();
                Set<String> keySet= map.keySet();
                if (keySet.size() > 2000) {
                    List<Integer> keyList = new ArrayList<>(keySet.size());
                    for (String key : keySet) {
                        keyList.add(Integer.valueOf(key));
                    }
                    Collections.sort(keyList, new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o1 - o2;
                        }
                    });
                    for (int i = 0;i < (keyList.size() - 2000);i++){
                        removeInfo(keyList.get(i)+"");
                    }
                }
            }
        }).start();

    }
    /**
     * 初始化单例
     * @param context
     */
    public static synchronized SharedPreferencesUtils init(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtils(context);
        }
        return instance;
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("class should init!");

        }
        return instance;
    }

    /**
     * 保存数据
     *
     * @param key
     * @param data
     */
    public void saveData(String key, Object data) {
        String type = data.getClass().getSimpleName();

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }

    /**
     *  传入多个数据，并且保存
     * @param map 数据集合
     */
    public void WriteDataOver(Map<String,Object> map){
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        for (String key:map.keySet()){
            String type = map.get(key).getClass().getSimpleName();
            if ("Integer".equals(type)) {
                editor.putInt(key, (Integer) map.get(key));
            } else if ("Boolean".equals(type)) {
                editor.putBoolean(key, (Boolean) map.get(key));
            } else if ("String".equals(type)) {
                editor.putString(key, (String) map.get(key));
            } else if ("Float".equals(type)) {
                editor.putFloat(key, (Float)map.get(key));
            } else if ("Long".equals(type)) {
                editor.putLong(key, (Long)map.get(key));
            }
        }
        editor.commit();
    }

    /**
     *  得到多个数据的返回
     * @param map
     * @return
     */
    public Map<String,Object> ReadDataOver(Map<String,Object> map){
        Map<String,Object> MapData= new HashMap<>();
        for (String key:map.keySet()){
            String type= map.get(key).getClass().getSimpleName();
            if ("Integer".equals(type)) {
                MapData.put(key,mSharedPreferences.getInt(key, (Integer) map.get(key)));
            } else if ("Boolean".equals(type)) {
                MapData.put(key,mSharedPreferences.getBoolean(key, (Boolean) map.get(key)));
            } else if ("String".equals(type)) {
                MapData.put(key,mSharedPreferences.getString(key, (String) map.get(key)));
            } else if ("Float".equals(type)) {
                MapData.put(key,mSharedPreferences.getFloat(key, (Float) map.get(key)));
            } else if ("Long".equals(type)) {
                MapData.put(key,mSharedPreferences.getLong(key, (Long) map.get(key)));
            }
        }
        return MapData;
    }

    /**
     * 得到数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public Object getData(String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return mSharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return mSharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return mSharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return mSharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return mSharedPreferences.getLong(key, (Long) defValue);
        }

        return null;
    }
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key,defValue);

    }

    public void saveString(String key, String data) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(key,data).commit();
    }

    public void removeString(String key){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key);
    }
    public void removeOne(String key,String value){
        Set<String> set = mSharedPreferences.getStringSet(key, null);
        if (set == null){
           return;
        }
        set.remove(value);
        mSharedPreferences.edit().putStringSet(key,set).commit();

    }
    public void saveOne(String key,String value){
        Set<String> set = mSharedPreferences.getStringSet(key, null);
        if (set == null){
            set = new HashSet<>();
        }
        set.add(value);
        mSharedPreferences.edit().putStringSet(key,set).commit();
    }

    public boolean isContains(String key,String value){
        Set<String> set = mSharedPreferences.getStringSet(key, null);
        if (set == null){
           return false;
        }else {
            return set.contains(value);
        }
    }

    public void saveNewsInfo(String id, int goodOrBad){
        SharedPreferences.Editor editor = newsSharedPrefrences.edit();
        editor.putInt(id, goodOrBad);
        editor.commit();
    }

    /**
     *
     * @param id
     * @return 0 默认值 1 点击利好 2 点击利空
     */
    public int getNewsInfo(String id){
        return newsSharedPrefrences.getInt(id, 0);
    }

    public void removeInfo(String id){
        SharedPreferences.Editor editor = newsSharedPrefrences.edit();
        editor.remove(id);
        editor.commit();
    }



}
