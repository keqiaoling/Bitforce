package com.example.myapplication.okhttp.callback;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        String body = response.body().string();
        try {
            JSONObject jsonObject = new JSONObject(body);
            if (jsonObject.has("code")){
                int code = jsonObject.getInt("code");
                if (code == 401){

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            return body;
        }


    }

}
