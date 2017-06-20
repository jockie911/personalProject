package jockie.site.personalproject.presenter;

import android.widget.Toast;

import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BaseApp;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.bean.ALlBean;
import jockie.site.personalproject.bean.CategoryBean;
import jockie.site.personalproject.istatic.Constants;
import jockie.site.personalproject.net.RestClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yc on 2017/6/20.
 */

public class FoodPresenter<T extends BaseActivity> extends BasePresenter<IFoodView,T> {

    public void getNetData(){
        RestClient.instance().getAll(Constants.URL_ALL,Constants.SMSSDK_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ALlBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(BaseApp.getContext(),"onCompleted 1",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(BaseApp.getContext(),"onError 1",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ALlBean aLlBean) {
                        mBaseView.setData(aLlBean.getResult());
                    }
                });
    }

    public void getItemData(String cid, String name, String page) {
        RestClient.instance().getCategory(cid,name,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(BaseApp.getContext(),"onCompleted 2",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(BaseApp.getContext(),"onError 2" + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        mBaseView.setItemData(categoryBean.getResult());
                    }
                });
    }
}
