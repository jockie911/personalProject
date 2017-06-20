package jockie.site.personalproject;

import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import jockie.site.personalproject.Utils.GlideUtils;
import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.bean.CategoryBean;

public class FoodDetailActivity extends BaseActivity {

    @Bind(R.id.tv_sub_title)
    TextView tvSubTitle;
    @Bind(R.id.iv_thumbnail)
    ImageView ivThumbnail;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_food_detail;
    }

    @Override
    protected void initData() {
        CategoryBean.ResultBean.ListBean itemdata = getIntent().getParcelableExtra("ITEMDATA");
        tvTitle.setText(itemdata.getName());

        tvSubTitle.setText(itemdata.getRecipe().getTitle());
        GlideUtils.load(this,itemdata.getThumbnail(),ivThumbnail);

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
