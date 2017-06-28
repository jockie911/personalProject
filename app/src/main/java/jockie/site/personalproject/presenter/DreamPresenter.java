package jockie.site.personalproject.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BaseApp;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.bean.DreamBean;
import jockie.site.personalproject.net.RestClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yc on 2017/6/28.
 */

public class DreamPresenter<T extends BaseActivity> extends BasePresenter<IDreamView,T>{

    public void search() {
        if(TextUtils.isEmpty(mBaseView.getContent())){
            Toast.makeText(BaseApp.getContext(),"请输入关键字",Toast.LENGTH_SHORT).show();
            return;
        }

        RestClient.instance().getDreamDetail(mBaseView.getContent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DreamBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DreamBean dreamBean) {
                        if(TextUtils.equals("200",dreamBean.getRetCode())){
                            StringBuilder sb = new StringBuilder();
                            DreamBean.ResultBean result = dreamBean.getResult();
                            if(result != null && result.getList() != null && result.getList().size() > 0){
                                for (DreamBean.ResultBean.ListBean listBean : result.getList()) {
                                    sb.append(listBean.getName()).append("\n").append(listBean.getDetail()).append("\n").append("\n");
                                }
                            }else{
                                sb.append("请求网络失败");
                            }
                            mBaseView.setResult(sb.toString());
                        }else{
                            mBaseView.setResult(dreamBean.getMsg());
                        }
                    }
                });
    }
}
