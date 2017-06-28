package jockie.site.personalproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.Bind;
import butterknife.OnClick;
import jockie.site.personalproject.module.DreamActivity;
import jockie.site.personalproject.module.FoodActivity;
import jockie.site.personalproject.R;
import jockie.site.personalproject.base.BaseApp;
import jockie.site.personalproject.base.BaseFragment;
import jockie.site.personalproject.module.OilActivity;


public class YFragment extends BaseFragment {

    @Override
    protected View inflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_y, null);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_food,R.id.tv_erwei_code,R.id.tv_sleep,R.id.tv_oil})
    public void onClick(View view){
        if(isDoubleClick(view)) return;
        switch (view.getId()){
            case R.id.tv_food:
                startActivity(new Intent(getActivity(), FoodActivity.class));
                break;
            case R.id.tv_erwei_code:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_sleep:
                startActivity(new Intent(getActivity(),DreamActivity.class));
                break;
            case R.id.tv_oil:
                startActivity(new Intent(getActivity(),OilActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                Toast.makeText(BaseApp.getContext(),"解析结果 = " + result,Toast.LENGTH_SHORT).show();
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                Toast.makeText(BaseApp.getContext(),"解析二维码失败" ,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
