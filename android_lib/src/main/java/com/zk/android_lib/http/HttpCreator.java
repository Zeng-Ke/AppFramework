package com.zk.android_lib.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zk.android_lib.api.IFirstApi;
import com.zk.android_lib.api.ISecondApi;
import com.zk.android_utils.http.HttpConstants;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: ZK.
 * date:   On 2018/6/22.
 */
public class HttpCreator {

    private static final Map<String, Retrofit> mRetrofitMap = new ConcurrentHashMap<>();
    private static final Map<String, Object> mIApiMap = new ConcurrentHashMap<>();


    private static Retrofit createRetrofit(ApiConfig config) {

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new RequestHeaderInterceptor(config))
                .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLogger()).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(config.baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }


    public static <T> T createApi(Class<T> tClass) {
        String apiName = tClass.getName();
        T t = (T) mIApiMap.get(apiName);
        if (t == null) {
            ApiConfig apiConfig = getConfig(tClass);
            Retrofit retrofit = mRetrofitMap.get(apiConfig.baseUrl);
            if (retrofit == null) {
                retrofit = createRetrofit(apiConfig);
                mRetrofitMap.put(apiConfig.baseUrl, retrofit);
            }
            mIApiMap.put(apiName, t = retrofit.create(tClass));
        }
        return t;
    }

    private static ApiConfig getConfig(Class cls) {
        if (cls.isAssignableFrom(IFirstApi.class))
            return new ApiConfig(HttpConstants.URL_1);
        else if (cls.isAssignableFrom(ISecondApi.class))
            return new ApiConfig(HttpConstants.URL_2);
        else return new ApiConfig(HttpConstants.URL_BASE);

    }


    private static class RequestHeaderInterceptor implements Interceptor {

        private ApiConfig apiConfig;

        public RequestHeaderInterceptor(ApiConfig apiConfig) {
            this.apiConfig = apiConfig;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            Request request = builder.cacheControl(CacheControl.FORCE_NETWORK)
                    //.addHeader("Version",apiConfig.apiversion) //添加api版本
                    .build();
            return chain.proceed(request);
        }
    }


    private static class ApiConfig {

        public String baseUrl;

        public ApiConfig(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }

}
