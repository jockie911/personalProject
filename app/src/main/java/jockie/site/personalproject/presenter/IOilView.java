package jockie.site.personalproject.presenter;

import java.util.List;

import jockie.site.personalproject.base.IBaseView;
import jockie.site.personalproject.bean.OilBean;

/**
 * Created by yc on 2017/6/28.
 */

public interface IOilView extends IBaseView{

    void setData(List<OilBean.Data> mData);
}
