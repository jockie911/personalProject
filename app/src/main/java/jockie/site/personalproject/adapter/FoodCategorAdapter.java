package jockie.site.personalproject.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.test_list_item, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTv.setText(mData.get(position).getName());
        holder.itemTv.setTag(position);
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
        mOnItemClickListener.onItemClickListener(v,(int)v.getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemTv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTv = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(View view,int position);
    }
}
