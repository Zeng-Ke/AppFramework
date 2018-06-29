package com.zk.android_lib.presenters;

import com.zk.android_lib.http.base.interceptor.AsyncCallBackInterceptor;
import com.zk.android_lib.http.base.callbacker.AsyncCallBacker;
import com.zk.android_lib.http.base.transformer.RequestTransformer;
import com.zk.android_lib.presenters.base.UnbinderPresenter;
import com.zk.android_lib.service.FirstService;
import com.zk.android_utils.base.IView;
import com.zk.android_utils.executors.ThreadExecutor;
import com.zk.java_lib.bean.DoubleListBean;
import com.zk.java_lib.bean.PhoneInfoBean;
import com.zk.java_lib.bean.base.BaseBean;
import com.zk.java_lib.bean.base.CommonDataBean;
import com.zk.java_utils.log.LogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
public class HomePageActivityPresenter extends UnbinderPresenter<HomePageActivityPresenter.IndexView> {


    public HomePageActivityPresenter(IndexView iView) {
        super(iView);
    }

    public interface IndexView extends IView {
        void onGetDataSuccess(String info);
    }


    public void getPhoneInfo(String phoneNumber) {

        FirstService firstService = new FirstService();
        firstService.getPhoneInfo(phoneNumber, "JQ4iONjs1LBg60Ghgj842cKvjgVE7dDRXfBpxsvWTrgP16hY5RtOaVgqy1Wky7MT",
                new AsyncCallBacker<BaseBean<List<PhoneInfoBean>>>(this,true) {
                    @Override
                    public void onCallBack(BaseBean<List<PhoneInfoBean>> phoneInfoBeans) {
                        if (phoneInfoBeans != null && phoneInfoBeans.data.size() > 0)
                            getView().onGetDataSuccess(phoneInfoBeans.data.get(0).toString());
                    }
                });

    }

    public void getData1() {
        FirstService firstService = new FirstService();
        firstService.getJob(new AsyncCallBacker<CommonDataBean<DoubleListBean>>(this,true) {
            @Override
            public void onCallBack(CommonDataBean<DoubleListBean> commonDataBeanBaseBean) {
                if (commonDataBeanBaseBean != null && commonDataBeanBaseBean.items.size() > 0)
                    getView().onGetDataSuccess(commonDataBeanBaseBean.items.get(0).name);
            }
        });
    }

    public void getJob() {
        FirstService firstService = new FirstService();
        firstService
                .getJob(
                        new AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String>() {
                            @Override
                            public String apply(CommonDataBean<DoubleListBean> doubleListBeanCommonDataBean) throws Exception {
                                LogUtil.d("======", "getJob");
                                return doubleListBeanCommonDataBean.items.get(2).name;
                            }
                        }
                        , new AsyncCallBacker<String>(this,true) {
                            @Override
                            public void onCallBack(String s) {
                                getArea(s);
                            }
                        });
    }

    public void getArea(final String str) {
        FirstService firstService = new FirstService();
        firstService
                .getArea(1, "0001"
                        , new AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String>() {
                            @Override
                            public String apply(CommonDataBean<DoubleListBean> doubleListBeanCommonDataBean) throws Exception {
                                LogUtil.d("======", "getArea");
                                return doubleListBeanCommonDataBean.items.get(2).name;
                            }
                        }, new AsyncCallBacker<String>(this,true) {
                            @Override
                            public void onCallBack(String s) {
                                getView().onGetDataSuccess(s + str);
                            }

                        });

    }

    public void getData() {
        final FirstService firstService = new FirstService();
        firstService
                .getJob(new AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String>() {
                    @Override
                    public String apply(CommonDataBean<DoubleListBean> doubleListBeanCommonDataBean) throws Exception {
                        return doubleListBeanCommonDataBean.items.get(2).name;
                    }
                })
                .flatMap(new RequestTransformer<String, String>() {
                    @Override
                    public ObservableSource<String> transform(final String s) throws Exception {
                        return firstService.getArea(1, "0001", new AsyncCallBackInterceptor<CommonDataBean<DoubleListBean>, String>() {
                            @Override
                            public String apply(CommonDataBean<DoubleListBean> doubleListBeanCommonDataBean) throws Exception {
                                return doubleListBeanCommonDataBean.items.get(2).name + "==" + s;
                            }
                        });
                    }
                })
                .subscribe(firstService.getCallBacker(new AsyncCallBacker<String>(this,true) {
                    @Override
                    public void onCallBack(String s) {
                        getView().onGetDataSuccess(s);
                    }
                }));
    }

}