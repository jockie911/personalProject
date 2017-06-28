package jockie.site.personalproject.net.api;


import java.util.Map;

import jockie.site.personalproject.bean.ALlBean;
import jockie.site.personalproject.bean.CategoryBean;
import jockie.site.personalproject.bean.DreamBean;
import jockie.site.personalproject.bean.OilBean;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by yc on 2017/5/25.
 */

public interface Api<T> {

//    @GET()
//    Observable<BaseHttpBean<T>> get(@Url String url, @QueryMap Map<String, String> params/*, @Header("Cache-Time") String time*/);
//
//    @FormUrlEncoded
//    @POST()
//    Observable<BaseHttpBean<T>> post(@Url String url, @FieldMap Map<String, String> params/*, @Header("Cache-Time") String time*/);

    @GET()
    Observable get(@Url String url, @QueryMap Map<String, String> params);

    @FormUrlEncoded
    @POST()
    Observable post(@Url String url, @FieldMap Map<String, String> params);

    @GET()
    Observable<ALlBean> getAll(@Url String url, @Query("key") String key);

    @GET()
    Observable<CategoryBean> getCategory(@Url String url, @Query("key") String key,
                                                            @Query("cid") String cid,
//                                                            @Query("name") String name,
                                                            @Query("page") String page,
                                                            @Query("size") String size);

    @GET
    Observable<DreamBean> getDreamDetail(@Url String url,@Query("key") String key, @Query("name") String name);

    @GET
    Observable<OilBean> getOil(@Url String url,@Query("key") String smssdkKey);
}
