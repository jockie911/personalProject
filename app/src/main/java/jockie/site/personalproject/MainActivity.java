package jockie.site.personalproject;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import jockie.site.personalproject.base.BaseActivity;
import jockie.site.personalproject.base.BasePresenter;
import jockie.site.personalproject.fragment.EFragment;
import jockie.site.personalproject.fragment.IFragment;
import jockie.site.personalproject.fragment.LFragment;
import jockie.site.personalproject.fragment.MFragment;
import jockie.site.personalproject.fragment.YFragment;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */
public class MainActivity extends BaseActivity{
    @Bind(R.id.tv_msg)
    TextView tvMsg;
    @Bind(R.id.tv_online)
    TextView tvOnline;
    @Bind(R.id.tv_index)
    TextView tvIndex;
    @Bind(R.id.tv_activi)
    TextView tvActivi;
    @Bind(R.id.tv_info)
    TextView tvInfo;
    @Bind(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;

    List<TextView> tvLists = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        tvTitle.setText("首页");
        ivLeftTitleBar.setVisibility(View.GONE);
        currentSelectedPosition = 2;
        dealWithFragment();

        tvLists.add(tvMsg);
        tvLists.add(tvOnline);
        tvLists.add(tvIndex);
        tvLists.add(tvActivi);
        tvLists.add(tvInfo);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    @OnClick({R.id.fl_msg, R.id.fl_index, R.id.fl_info, R.id.fl_online, R.id.fl_activi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_msg:
                setImageViewBg(0);
                break;
            case R.id.fl_online:
                setImageViewBg(1);
                break;
            case R.id.fl_index:
                setImageViewBg(2);
                break;
            case R.id.fl_activi:
                setImageViewBg(3);
                break;
            case R.id.fl_info:
                setImageViewBg(4);
        }
    }

    private void setImageViewBg(int position) {
        for (int i = 0; i < tvLists.size(); i++) {
            tvLists.get(i).setBackgroundResource(i == position? R.drawable.heart_burn : R.drawable.heart_blue);
            tvLists.get(i).setTextColor(getResources().getColor(i == position ? R.color.bg_color : R.color.textColor));
        }
        beforeSelectedPosition = currentSelectedPosition;
        currentSelectedPosition = position;
        dealWithFragment();
    }

    @Override
    protected List<Fragment> getFragment() {
        mFragment.add(new EFragment());
        mFragment.add(new MFragment());
        mFragment.add(new IFragment());
        mFragment.add(new LFragment());
        mFragment.add(new YFragment());
        return mFragment;
    }
}
