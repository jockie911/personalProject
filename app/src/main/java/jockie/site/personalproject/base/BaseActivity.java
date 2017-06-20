package jockie.site.personalproject.base;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.swipbackhelper.SwipeBackHelper;

import java.util.Calendar;

import butterknife.ButterKnife;
import jockie.site.personalproject.R;
import jockie.site.personalproject.istatic.Constants;

public abstract class BaseActivity extends AppCompatActivity implements OnClickListener{

	protected View rootView;
	protected TextView tvRightTitle;
	protected TextView tvTitle;
	private BasePresenter mPresenter;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStatusBar();
		setPhoneOrientation();
		rootView = LayoutInflater.from(this).inflate(getLayoutRes(), null);
		setContentView(rootView);
		initBaseTitleBar();
		ButterKnife.bind(this);

		mPresenter = initPresenter();
		initData();

		initSwipeBack();
	}

	protected void initBaseTitleBar(){
		if(!isHavaBaseTitleBar()) return;
		rootView.findViewById(R.id.iv_left_title_bar).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvRightTitle = (TextView) rootView.findViewById(R.id.tv_right_title_bar);
	};

	/**
	 * init activity滑动退出
	 */
	protected void initSwipeBack() {
		if(isSwipeBack()){
			SwipeBackHelper.onCreate(this);
			SwipeBackHelper.getCurrentPage(this)
					.setSwipeBackEnable(true)
					.setSwipeSensitivity(0.5f)
					.setSwipeRelateEnable(true)
					.setSwipeEdgePercent(0.15f)
					.setSwipeRelateOffset(500)
					.setClosePercent(0.5f);
		}
	}

	protected boolean isHavaBaseTitleBar(){
		return true;
	}

	/**
	 * 控制activity是否可以滑动退出
	 * @return
	 */
	protected boolean isSwipeBack(){
		return false;
	}

	/**
	 * int resID
	 */
	protected abstract int getLayoutRes();

	protected abstract void initData();


	@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
	@Override
	protected void onDestroy() {
		if(isSwipeBack())
			SwipeBackHelper.onDestroy(this);
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		if(imm != null)
			imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//		SoftInputUtils.closeSoftInput(this);
		if (mPresenter != null) {
			mPresenter.detachView();
		}
		super.onDestroy();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		if(isSwipeBack())
			SwipeBackHelper.onPostCreate(this);
	}
	
	/** 设置屏幕竖屏*/
	private void setPhoneOrientation() {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
	protected void setStatusBar() {
//		getWindow().setFlags(
//				WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
		}
	}

	@Override
	public void onClick(View v) {

	}

	/**
	 * 避免快速操作造成连续点击
	 * @param v
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.DONUT)
	protected boolean isDoubleClick(View v){
		Object tag = v.getTag(v.getId());
		long beforeTimemiles = tag != null ? (long) tag : 0;
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		v.setTag(v.getId(),timeInMillis);
	    return timeInMillis - beforeTimemiles < Constants.NO_DOUBLE_CLICK_TIME;
	}


	@Override
	protected void onStart() {
		Glide.with(this).resumeRequestsRecursive();
		super.onStart();
	}

	@Override
	protected void onPause() {
		Glide.with(this).pauseRequestsRecursive();
		super.onPause();
	}

	protected abstract BasePresenter initPresenter() ;
}
