package jockie.site.personalproject.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import butterknife.ButterKnife;
import jockie.site.personalproject.istatic.Constants;

/**
 * Created by jockie on 2016/7/8.
 */
public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = BaseApp.getContext();
        View view = inflater(inflater);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
    }

    protected abstract View inflater(LayoutInflater inflater);

    protected abstract void initData();

    protected boolean isUseButterKnife(){
        return true;
    }

    @Override
    public void onDestroy() {
        if(isUseButterKnife())
            ButterKnife.unbind(this);
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.DONUT)
    protected boolean isDoubleClick(View v){
        Object tag = v.getTag(v.getId());
        long beforeTimemiles = tag != null ? (long) tag : 0;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        v.setTag(v.getId(),timeInMillis);
        return timeInMillis - beforeTimemiles < Constants.NO_DOUBLE_CLICK_TIME;
    }
}
