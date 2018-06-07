package com.zk.android_utils;

import com.zk.android_utils.base.BasePresenter;
import com.zk.android_utils.base.IView;
import com.zk.android_utils.base.PresenterActivity;
import com.zk.java_utils.log.LogUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author: ZK.
 * date:   On 2018/6/7.
 */
public class PresenterUtil {


    public static <T extends BasePresenter<E>, E extends IView> T createPresenter(Class clz, E view) {
        Type[] type = ((ParameterizedType) clz.getGenericSuperclass()).getActualTypeArguments();

        Class<T> presenterClz = (Class<T>) type[0];
        Class<E> viewClz = (Class<E>) type[1];
        T presenter = null;
        try {
            Constructor<T> constructor = presenterClz.getConstructor(viewClz);
            presenter = constructor.newInstance(view);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("Can not create this view's presenter");
        }
        return presenter;
    }


    public static <T extends BasePresenter<E>, E extends IView> T createPresenter(PresenterActivity<T, E> activity) {
        return createPresenter(activity.getClass(), (E)activity);
    }
}
