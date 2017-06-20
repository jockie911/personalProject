package jockie.site.personalproject.net;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jockie.site.personalproject.bean.ALlBean;
import jockie.site.personalproject.bean.CategoryBean;
import jockie.site.personalproject.istatic.Constants;
import jockie.site.personalproject.net.api.Api;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by yc on 2017/5/25.
 */

public class RestClient {

    private static final String TAG = "RestClient";

    private static volatile RestClient sInstance;
    private Api mApi;

    private RestClient() {}

    public static RestClient instance() {
        if (sInstance == null) {
            synchronized (RestClient.class) {
                if (sInstance == null) {
                    sInstance = new RestClient();
                }
            }
        }
        return sInstance;
    }

    private Api api() {
        if (mApi == null) {
            synchronized (Api.class) {
                mApi = create();
            }
        }
        return mApi;
    }

    private Api create() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        client.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
//                HttpUrl url = request.url().newBuilder()
//                        .addQueryParameter("package_name", App.sInstance.getPackageName())
//                        .addQueryParameter("version_code", String.valueOf(Utils.getVersionCode(App.sInstance)))
//                        .addQueryParameter("version_name", String.valueOf(Utils.getVersionName(App.sInstance)))
//                        .addQueryParameter("channel", String.valueOf(Utils.getUmengChannel(App.sInstance)))
//                        .build();
//                request = request.newBuilder().url(url).build();
                request = request.newBuilder().build();

                return chain.proceed(request);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_ALL + "/")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        return api;
    }

    public Observable get(String url, Map<String,String> params){
        return api().get(url,params);
    }

    public Observable post(String url, Map<String,String> params){
        return api().post(url,params);
    }

    public Observable<ALlBean> getAll(String url,String key){
        return api().getAll(url,key);
    }

    public Observable<CategoryBean> getCategory(String cid, String name, String page) {
        return api().getCategory(Constants.URL_CATEGORY,Constants.SMSSDK_KEY,cid,page,"20");
    }
}
