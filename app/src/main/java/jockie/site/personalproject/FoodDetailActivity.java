package jockie.site.personalproject;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    @Bind(R.id.tv_ingredient)
    TextView tvIngredient;
    @Bind(R.id.lv)
    ListView lv;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_food_detail;
    }

    @Override
    protected void initData() {
        CategoryBean.ResultBean.ListBean itemdata = getIntent().getParcelableExtra("ITEMDATA");
        tvTitle.setText(itemdata.getName());

        tvSubTitle.setText(itemdata.getRecipe().getTitle());
        GlideUtils.load(this,itemdata.getRecipe().getImg(),ivThumbnail);

        tvIngredient.setText(itemdata.getRecipe().getIngredients());


        CookFoodAdapter cookFoodAdapter = new CookFoodAdapter();
        lv.setAdapter(cookFoodAdapter);
        try {
            JSONArray jsonArray = new JSONArray(itemdata.getRecipe().getMethod());
            cookFoodAdapter.addData(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    private class CookFoodAdapter extends BaseAdapter{


        private JSONArray jsonArray;

        @Override
        public int getCount() {
            return jsonArray == null ? 0 : jsonArray.length();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null)
                convertView = View.inflate(parent.getContext(),R.layout.item_cook_detail_food,null);
            ImageView itemIv = (ImageView) convertView.findViewById(R.id.item_iv_cook);
            TextView itemTv = (TextView) convertView.findViewById(R.id.item_tv_cook);

            try {
                String string = jsonArray.getString(position);
                JSONObject jsonObject = new JSONObject(string);

                if(jsonObject.has("img")){
                    GlideUtils.load(parent.getContext(),jsonObject.getString("img"),itemIv);
                }
                if(jsonObject.has("step")){
                    itemTv.setText("this is a test");

                    Log.d("TAG",jsonObject.getString("step"));
                }

            } catch (JSONException e) {

            }
            return convertView;
        }

        public void addData(JSONArray jsonArray) {
            this.jsonArray = jsonArray;
        }
    }
}
