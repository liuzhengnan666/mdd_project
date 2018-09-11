package com.example.administrator.llc_p.service;


import android.util.Log;

import com.example.administrator.llc_p.bean.RegisterBean;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;



public abstract class BaseObserver<T> implements Observer<T>{
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            Response<?> response = httpException.response();
            int code = response.code();
            String strFromUniCode = null;
            try {
                String  string = response.errorBody().string();
                Gson gson = new Gson();
                RegisterBean bean = gson.fromJson(string, RegisterBean.class);
//                String data = bean.getMsg();
//                strFromUniCode = Tool.getStrFromUniCode(data);
                Log.d("myInfor:我有一只小雪人，一直都在骑", "code:"+code+"-------msg:"+string+"----------strFromUniCode"+strFromUniCode);
                onFail(strFromUniCode);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(String s);
}
