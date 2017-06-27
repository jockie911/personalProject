package jockie.site.personalproject.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;

import butterknife.Bind;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import jockie.site.personalproject.R;
import jockie.site.personalproject.base.BaseFragment;

/**
 * Created by yc on 2017/6/26.
 */

public class DateFragment extends BaseFragment {

    @Bind(R.id.datepicker)
    DatePicker datepicker;
    @Override
    protected View inflater(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_date,null);
    }

    @Override
    protected void initData() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        datepicker.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
        datepicker.setMode(DPMode.SINGLE);
        datepicker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(getActivity(),date,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
