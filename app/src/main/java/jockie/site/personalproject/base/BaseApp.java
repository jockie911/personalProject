package jockie.site.personalproject.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

public class BaseApp extends Application {

	private static Context context;

	public static int mScreenWidth = 0;
	public static int mScreenHeight = 0;


	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		DisplayMetrics mDisplayMetrics = getApplicationContext().getResources().getDisplayMetrics();
		BaseApp.mScreenWidth = mDisplayMetrics.widthPixels;
		BaseApp.mScreenHeight = mDisplayMetrics.heightPixels;

//		LeakCanary.install(this);
	}


	public static Context getContext() {
		return context;
	}
}
