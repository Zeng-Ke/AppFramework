package com.zk.android_lib.api;

import com.zk.java_lib.bean.DoubleListBean;
import com.zk.java_lib.bean.base.BaseBean;
import com.zk.java_lib.bean.PhoneInfoBean;
import com.zk.java_lib.bean.base.CommonDataBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: ZK.
 * date:   On 2018/6/22.
 */
public interface IFirstApi {


    @GET("/tools/phone_number_ascription")
    Observable<BaseBean<List<PhoneInfoBean>>> getPhoneInfo(
            @Query("phoneNumber") String phoneNumber,
            @Query("apikey") String apikey);

    @GET("/CommonData/JobClass")
    Observable<BaseBean<CommonDataBean<DoubleListBean>>> getJob();



    @GET("/CommonData/Area")
    Observable<BaseBean<CommonDataBean<DoubleListBean>>> getArea(
            @Query("deep") int deep,
            @Query("parent") String parent);


}
