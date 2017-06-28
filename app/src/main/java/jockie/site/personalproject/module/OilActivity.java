package jockie.site.personalproject.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import jockie.site.personalproject.R;
import jockie.site.personalproject.adapter.OilAdapter;
import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.bean.OilBean;
import jockie.site.personalproject.presenter.IOilView;
import jockie.site.personalproject.presenter.OilPresenter;

public class OilActivity extends BaseActivity implements IOilView{

    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    private OilPresenter mPresenter;
    private OilAdapter oilAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_oil;
    }

    @Override
    protected void initData() {
        tvTitle.setText("全国油价");
        oilAdapter = new OilAdapter();
        recyclerView.setAdapter(oilAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mPresenter.requestData();
    }

    @Override
    protected BasePresenter initPresenter() {
        mPresenter = new OilPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @Override
    public void setData(List<OilBean.Data> mData) {
        oilAdapter.addData(mData);
    }
}
