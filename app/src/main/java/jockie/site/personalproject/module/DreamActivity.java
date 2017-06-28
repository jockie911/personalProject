package jockie.site.personalproject.module;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import jockie.site.personalproject.R;
import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.presenter.DreamPresenter;
import jockie.site.personalproject.presenter.IDreamView;

public class DreamActivity extends BaseActivity implements IDreamView {

    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.tv_result)
    TextView tvResult;
    private DreamPresenter mPresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dream;
    }

    @Override
    protected void initData() {
        tvTitle.setText("周公解梦");
    }

    @Override
    protected BasePresenter initPresenter() {
        mPresenter = new DreamPresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    @OnClick({R.id.tv_search})
    public void onClick(View view){
        if(isDoubleClick(view)) return;
        switch (view.getId()){
            case R.id.tv_search:
                mPresenter.search();
                break;
        }
    }

    @Override
    public String getContent() {
        return etContent.getText().toString().trim();
    }

    @Override
    public void setResult(String result) {
        tvResult.setText(result);
    }
}
