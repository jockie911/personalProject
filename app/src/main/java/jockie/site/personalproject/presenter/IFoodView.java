package jockie.site.personalproject.presenter;

import jockie.site.personalproject.base.IBaseView;
import jockie.site.personalproject.bean.ALlBean;
import jockie.site.personalproject.bean.CategoryBean;

/**
 * Created by yc on 2017/6/20.
 */

public interface IFoodView extends IBaseView{

    void setData(ALlBean.ResultBean result);

    void setItemData(CategoryBean.ResultBean result);

    void requestItemData(String cid,String name,String page);

}
