package com.zk.appframework.presenters;

import com.zk.android_lib.http.base.observer.MOberver;
import com.zk.android_lib.service.FirstService;
import com.zk.android_utils.base.BasePresenter;
import com.zk.android_utils.base.IView;
import com.zk.java_lib.bean.DoubleListBean;
import com.zk.java_lib.bean.PhoneInfoBean;
import com.zk.java_lib.bean.base.BaseBean;
import com.zk.java_lib.bean.base.CommonDataBean;

import java.util.List;

/**
 * author: ZK.
 * date:   On 2018/6/11.
 */
public class IndexPresenter extends BasePresenter<IndexPresenter.IndexView> {


    public IndexPresenter(IndexView iView) {
        super(iView);
    }

    public interface IndexView extends IView {
        void onGetDataSuccess(String info);
    }


    public void getPhoneInfo(String phoneNumber) {

        FirstService firstService = new FirstService();
        firstService.getPhoneInfo(phoneNumber, "JQ4iONjs1LBg60Ghgj842cKvjgVE7dDRXfBpxsvWTrgP16hY5RtOaVgqy1Wky7MT",
                new MOberver<BaseBean<List<PhoneInfoBean>>>() {
                    @Override
                    public void onNext(BaseBean<List<PhoneInfoBean>> phoneInfoBeans) {
                        if (phoneInfoBeans != null && phoneInfoBeans.data.size() > 0)
                            getView().onGetDataSuccess(phoneInfoBeans.data.get(0).toString());
                    }
                });

    }

    public void getData() {
        FirstService firstService = new FirstService();
        firstService.getJob(new MOberver<CommonDataBean<DoubleListBean>>() {
            @Override
            public void onNext(CommonDataBean<DoubleListBean> commonDataBeanBaseBean) {
                if (commonDataBeanBaseBean != null && commonDataBeanBaseBean.items.size() > 0)
                    getView().onGetDataSuccess(commonDataBeanBaseBean.items.get(0).name);
            }
        });
    }


}
