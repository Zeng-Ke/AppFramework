package com.zk.android_lib.service;

import com.google.gson.reflect.TypeToken;
import com.zk.android_lib.api.IFirstApi;
import com.zk.android_lib.http.HttpCreator;
import com.zk.android_utils.http.BaseService;
import com.zk.android_utils.http.RxCache;
import com.zk.android_utils.http.RxUtils;
import com.zk.android_utils.http.cache.HttpCacheMode;
import com.zk.android_utils.http.callbacker.BaseCallBacker;
import com.zk.android_utils.http.callbacker.ICallbacker;
import com.zk.android_utils.http.interceptor.AsyncCallBackInterceptor;
import com.zk.java_lib.bean.DoubleListBean;
import com.zk.java_lib.bean.PhoneInfoBean;
import com.zk.java_lib.bean.base.BaseBean;
import com.zk.java_lib.bean.base.CommonDataBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author: ZK.
 * date:   On 2018/6/23.
 */
public class FirstService extends BaseService {


    private final IFirstApi mIFirstApi;

    public FirstService() {
        mIFirstApi = HttpCreator.createApi(IFirstApi.class);
    }


    public void getPhoneInfo(String phoneNumber, String apikey, ICallbacker<BaseBean<List<PhoneInfoBean>>> oberver) {
       /* Observable<BaseBean<List<PhoneInfoBean>>> observable = HttpCreator.createApi(IApi.class).getPhoneInfo(phoneNumber,
                "JQ4iONjs1LBg60Ghgj842cKvjgVE7dDRXfBpxsvWTrgP16hY5RtOaVgqy1Wky7MT");
        enqueue(observable, observer);*/
        mIFirstApi
                .getPhoneInfo(phoneNumber, apikey)
                //.compose(RxUtils.<List<PhoneInfoBean>>io_main())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseCallBacker<>(oberver));
    }

    public void getJob(ICallbacker<CommonDataBean<DoubleListBean>> oberver, HttpCacheMode cacheMode) {
        mIFirstApi
                .getJob()
                .compose(RxUtils.io_main(new RxCache()
                        .<CommonDataBean<DoubleListBean>>excute(cacheMode, "job", new
                                TypeToken<CommonDataBean<DoubleListBean>>() {
                                }.getType())))
                // .compose(RxUtils.<CommonDataBean<DoubleListBean>>io_main())
                .subscribe(getCallBacker(oberver));
    }


    public void getJob(AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String> function, ICallbacker<String> oberver) {
        mIFirstApi
                .getJob()
                .compose(RxUtils.io(function))
                .subscribe(getCallBacker(oberver));
    }


    public void getArea(int deep, String parent, AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String> function,
                        ICallbacker<String> oberver) {
        mIFirstApi
                .getArea(deep, parent)
                .delay(3, TimeUnit.SECONDS)
                .compose(RxUtils.io_main(function))
                .subscribe(getCallBacker(oberver));
    }


    public Observable<String> getJob(AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String> function) {
        return mIFirstApi.getJob()
                .compose(RxUtils.io_main(function));
    }

    public Observable<String> getArea(int deep, String parent, AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>
            , String> function) {
        return mIFirstApi.getArea(deep, parent)
                .compose(RxUtils.io_main(function));
    }

}
