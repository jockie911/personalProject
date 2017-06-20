package jockie.site.personalproject.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by yc on 2017/6/20.
 */

public class GlideUtils {

    /** 用来检测网络变化 */
    public static boolean isLoadPic = true;

    public static void load(Context cxt,String url,ImageView target){
        if(isLoadPic){
            Glide.with(cxt).load(url).into(target);
        }else{

        }
    }
}
