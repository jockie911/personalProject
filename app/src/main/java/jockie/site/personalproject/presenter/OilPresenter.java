package jockie.site.personalproject.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.bean.OilBean;
import jockie.site.personalproject.net.RestClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yc on 2017/6/28.
 */

public class OilPresenter<T extends BaseActivity> extends BasePresenter<IOilView,T> {

    public void requestData() {
        RestClient.instance().getOil()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OilBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(OilBean oilBean) {
                        if(TextUtils.equals("200",oilBean.getRetCode())){
                            addData(oilBean.getResult());
                        }else{
                            Toast.makeText(mActivity.get(),oilBean.getMsg(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void addData(OilBean.ResultBean result) {
        List<OilBean.Data> mData = new ArrayList();

        OilBean.Data data = new OilBean.Data();
        data.setDieselOil0(result.get上海().getDieselOil0());
        data.setGasoline90(result.get上海().getGasoline90());
        data.setGasoline93(result.get上海().getGasoline93());
        data.setGasoline97(result.get上海().getGasoline97());
        data.setProvince(result.get上海().getProvince());
        mData.add(data);

        OilBean.Data data1 = new OilBean.Data();
        data1.setDieselOil0(result.get云南().getDieselOil0());
        data1.setGasoline90(result.get云南().getGasoline90());
        data1.setGasoline93(result.get云南().getGasoline93());
        data1.setGasoline97(result.get云南().getGasoline97());
        data1.setProvince(result.get云南().getProvince());
        mData.add(data1);

        OilBean.Data data2 = new OilBean.Data();
        data2.setDieselOil0(result.get内蒙古().getDieselOil0());
        data2.setGasoline90(result.get内蒙古().getGasoline90());
        data2.setGasoline93(result.get内蒙古().getGasoline93());
        data2.setGasoline97(result.get内蒙古().getGasoline97());
        data2.setProvince(result.get内蒙古().getProvince());
        mData.add(data2);

        OilBean.Data data3 = new OilBean.Data();
        data3.setDieselOil0(result.get北京().getDieselOil0());
        data3.setGasoline90(result.get北京().getGasoline90());
        data3.setGasoline93(result.get北京().getGasoline93());
        data3.setGasoline97(result.get北京().getGasoline97());
        data3.setProvince(result.get北京().getProvince());
        mData.add(data3);

        mBaseView.setData(mData);
    }
}
