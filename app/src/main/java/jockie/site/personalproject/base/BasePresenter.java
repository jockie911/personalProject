package jockie.site.personalproject.base;

import android.content.Context;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by yc on 2017/5/31.
 */

public abstract class BasePresenter<T extends IBaseView,K extends BaseActivity> {

    protected T mBaseView;
    protected WeakReference<K> mActivity;
    protected CompositeSubscription mCompositeSubscription;

    public void attachView(T mBaseView) {
        this.mBaseView = mBaseView;
        mActivity = new WeakReference((K)mBaseView);
        onViewAttach();
        mCompositeSubscription = new CompositeSubscription();
        initSubscription();
    }

    protected void onViewAttach() {
    }

    protected void initSubscription() {

    }

//    protected void addSubscription(@RxEvent.EventType int type, Action1<RxEvent> action) {
//        mCompositeSubscription.add(RxBus.getInstance().toObservable(type).subscribe(action));
//    }

    public void detachView() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
        mBaseView = null;
    }

    public Context getApplication() {
        return BaseApp.getContext();
    }

}
