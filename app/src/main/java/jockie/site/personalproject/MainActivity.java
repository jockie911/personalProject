package jockie.site.personalproject;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BasePresenter;

public class MainActivity extends BaseActivity{

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        tvTitle.setText("首页");

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.btn})
    @Override
    public void onClick(View view){
        if(isDoubleClick(view)) return;

        startActivity(new Intent(this,FoodActivity.class));
    }
}
