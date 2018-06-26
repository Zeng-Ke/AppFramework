package com.zk.android_lib.service;

import com.zk.android_lib.api.IFirstApi;
import com.zk.android_lib.http.base.BaseService;
import com.zk.android_lib.http.base.observer.BaseObserver;
import com.zk.android_lib.http.base.HttpCreator;
import com.zk.android_lib.http.base.observer.IObserver;
import com.zk.android_lib.http.base.RxUtils;
import com.zk.java_lib.bean.DoubleListBean;
import com.zk.java_lib.bean.PhoneInfoBean;
import com.zk.java_lib.bean.base.BaseBean;
import com.zk.java_lib.bean.base.CommonDataBean;

import java.util.List;

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


    public void getPhoneInfo(String phoneNumber, String apikey, IObserver<BaseBean<List<PhoneInfoBean>>> oberver) {
       /* Observable<BaseBean<List<PhoneInfoBean>>> observable = HttpCreator.createApi(IApi.class).getPhoneInfo(phoneNumber,
                "JQ4iONjs1LBg60Ghgj842cKvjgVE7dDRXfBpxsvWTrgP16hY5RtOaVgqy1Wky7MT");
        enqueue(observable, observer);*/
        mIFirstApi
                .getPhoneInfo(phoneNumber, apikey)
                // .compose(RxUtils.<List<PhoneInfoBean>>io_main())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<>(oberver));


    }

    public void getJob(IObserver<CommonDataBean<DoubleListBean>> oberver) {
        mIFirstApi.getJob()
                .compose(RxUtils.<CommonDataBean<DoubleListBean>>io_main())
                .subscribe(getObserver(oberver));
    }


}
