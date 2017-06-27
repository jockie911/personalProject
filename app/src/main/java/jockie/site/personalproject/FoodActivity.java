package jockie.site.personalproject;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xiaosu.pulllayout.SimplePullLayout;
import com.xiaosu.pulllayout.base.BasePullLayout;

import butterknife.Bind;
import jockie.site.personalproject.adapter.FoodCategorAdapter;
import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BaseApp;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.bean.ALlBean;
import jockie.site.personalproject.bean.CategoryBean;
import jockie.site.personalproject.presenter.FoodPresenter;
import jockie.site.personalproject.presenter.IFoodView;

public class FoodActivity extends BaseActivity implements IFoodView, FoodCategorAdapter.OnItemClickListener, BasePullLayout.OnPullCallBackListener {

    @Bind(R.id.recycleview)
    RecyclerView recyclerView;
    @Bind(R.id.pulllayout)
    SimplePullLayout pullLayout;
    private FoodPresenter presenter;
    private FoodCategorAdapter foodCategorAdapter;
    private int mCurrentPage = 1;
    private boolean isRefreshData = true;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_food;
    }

    @Override
    protected void initData() {
        tvTitle.setText("美食中心");
        presenter.getNetData();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        foodCategorAdapter = new FoodCategorAdapter();
        recyclerView.setAdapter(foodCategorAdapter);
        foodCategorAdapter.setOnItemClickListener(this);
        pullLayout.setOnPullListener(this);
        pullLayout.autoRefresh();
    }

    @Override
    protected BasePresenter initPresenter() {
        presenter = new FoodPresenter();
        presenter.attachView(this);
        return presenter;
    }

    @Override
    public void setData(ALlBean.ResultBean result) {
        ALlBean.ResultBean.ChildsBeanX.ChildsBean.CategoryInfoBeanXX categoryInfo =
                result.getChilds().get(0).getChilds().get(0).getCategoryInfo();

        Log.d("TAG",categoryInfo.getCtgId());
        requestItemData("0010001009",categoryInfo.getName(),mCurrentPage);
    }

    /**
     * item请求网络成功，加载某个模块的数据
     * @param result
     */
    @Override
    public void setItemData(CategoryBean.ResultBean result) {
        pullLayout.finishPull(isRefreshData?"刷新成功" : "加载更过成功",true);
        Toast.makeText(BaseApp.getContext(),result.getTotal() + "",Toast.LENGTH_SHORT).show();
        foodCategorAdapter.addData(result.getList(),isRefreshData);
    }

    /**
     * 请求某个模块的数据
     * @param cid
     * @param name
     * @param page
     */
    @Override
    public void requestItemData(String cid,String name,int page) {
        Log.d("TAG",cid + "  / " + name);
        presenter.getItemData(cid,name,page);
    }

    @Override
    public String getCurrentCid(String cid) {
        return null;
    }

    @Override
    public String getCurrentName(String name) {
        return null;
    }

    @Override
    public void onItemClickListener(View view, int position) {
        CategoryBean.ResultBean.ListBean itemData = foodCategorAdapter.getItemData(position);
        Intent intent = new Intent(this,FoodDetailActivity.class);
        intent.putExtra("ITEMDATA",itemData);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mCurrentPage = 1;
        isRefreshData = true;
        requestItemData("0010001009","荤菜",mCurrentPage);
        pullLayout.finishPull("onRefresh success",true);
    }

    @Override
    public void onLoad() {
        mCurrentPage ++;
        isRefreshData = false;
        requestItemData("0010001009","荤菜",mCurrentPage);
        pullLayout.finishPull("onLoad success",true);
    }
}
