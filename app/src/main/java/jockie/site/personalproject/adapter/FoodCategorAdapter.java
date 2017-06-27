package jockie.site.personalproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jockie.site.personalproject.R;
import jockie.site.personalproject.Utils.GlideUtils;
import jockie.site.personalproject.base.BaseApp;
import jockie.site.personalproject.bean.ALlBean;
import jockie.site.personalproject.bean.CategoryBean;

/**
 * Created by yc on 2017/6/20.
 */

public class FoodCategorAdapter extends RecyclerView.Adapter<FoodCategorAdapter.ViewHolder> implements View.OnClickListener {

    List<CategoryBean.ResultBean.ListBean> mData = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_adapter, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTv.setText(mData.get(position).getName());
        GlideUtils.load(BaseApp.getContext(),mData.get(position).getThumbnail(),holder.itemIvPic);
        holder.itemRootView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<CategoryBean.ResultBean.ListBean> childs, boolean isClear) {
        if(childs != null && childs.size() != 0){
            if(isClear)
                mData.clear();
            mData.addAll(childs);
            notifyDataSetChanged();
        }
    }

    public CategoryBean.ResultBean.ListBean getItemData(int position){
        return mData.get(position);
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClickListener(v,(int)v.findViewById(R.id.item_fm_root_view).getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTv;
        private ImageView itemIvPic;
        private FrameLayout itemRootView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTv = (TextView) itemView.findViewById(R.id.item_tv_title);
            itemIvPic = (ImageView) itemView.findViewById(R.id.item_iv_pic);
            itemRootView = (FrameLayout) itemView.findViewById(R.id.item_fm_root_view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(View view,int position);
    }
}
